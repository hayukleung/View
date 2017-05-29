package com.hayukleung.view.CityList;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hayukleung.App;
import com.hayukleung.view.CollapsibleView.UsingCollapsibleView.AndroidUtils;
import com.hayukleung.view.CollapsibleView.UsingCollapsibleView.Ln;
import de.greenrobot.common.io.IoUtils;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * chargerlink_v3
 * com.chargerlink.app.utils
 * JsonCity.java
 *
 * by hayukleung
 * at 2016-11-25 15:43
 */

/**
 * assets/cityMap.json
 */
public class JsonCity {

  public static Observable<Map<String, City>> getCityMap() {
    return Observable.create(new Observable.OnSubscribe<Map<String, City>>() {

      @Override public void call(Subscriber<? super Map<String, City>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadMap());
        subscriber.onCompleted();
      }
    });
  }

  public static Observable<List<City>> getCityList() {
    return Observable.create(new Observable.OnSubscribe<List<City>>() {
      @Override public void call(Subscriber<? super List<City>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadList());
        subscriber.onCompleted();
      }
    });
  }

  /**
   * 加载全国行政区信息
   */
  public static List<City> loadList() {
    List<City> cityList = null;
    Reader reader = null;
    try {
      // 从 assets 读取
      reader = new InputStreamReader(App.getInstance().getAssets().open("zone.json"));
      Type type = new TypeToken<List<City>>() {
      }.getType();
      cityList = new Gson().fromJson(reader, type);
    } catch (Exception ignored) {
      Ln.d(ignored);
    } finally {
      IoUtils.safeClose(reader);
    }

    return cityList;
  }

  public static City query(String cityCode) {
    if (TextUtils.isEmpty(cityCode) || 6 != cityCode.length()) {
      return null;
    }
    List<City> cityList = loadList();
    for (City city : cityList) {
      if (cityCode.equals(city.getCode())) {
        return city;
      }
    }
    return null;
  }

  /**
   * 根据行政区编码查询行政区信息
   *
   * @param codeList 行政区编码列表
   */
  public static Observable<List<City>> query(final List<String> codeList) {

    return Observable.create(new Observable.OnSubscribe<List<City>>() {
      @Override public void call(Subscriber<? super List<City>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadList());
        subscriber.onCompleted();
      }
    }).flatMap(new Func1<List<City>, Observable<List<City>>>() {

      @Override public Observable<List<City>> call(final List<City> cityList) {

        return Observable.create(new Observable.OnSubscribe<List<City>>() {

          @Override public void call(Subscriber<? super List<City>> subscriber) {

            AndroidUtils.checkNotMain();

            List<City> resultList = new ArrayList<>(codeList.size());
            for (String code : codeList) {
              City result = query(cityList, code);
              resultList.add(result);
            }
            subscriber.onNext(resultList);
            subscriber.onCompleted();
          }
        });
      }
    });
  }

  /**
   * 根据code查询行政区信息
   *
   * @param all
   * @param code
   */
  private static City query(List<City> all, String code) {

    if (null == all || 0 == all.size()) {
      return null;
    }
    for (City sub : all) {
      City result = queryPrivate(sub, code);
      if (null != result) {
        return result;
      }
    }
    return null;
  }

  /**
   * 递归查询行政区信息
   *
   * @param source
   * @param code
   */
  private static City queryPrivate(City source, String code) {
    if (code.equals(source.getCode())) {
      return source;
    } else {
      List<City> sub = source.getSub();
      if (null == sub || 0 == sub.size()) {
        return null;
      }
      for (City city : sub) {
        City result = queryPrivate(city, code);
        if (null != result) {
          return result;
        }
      }
    }
    return null;
  }

  /**
   * list 2 map
   */
  public static Map<String, City> loadMap() {
    List<City> cityList = loadList();
    Map<String, City> cityMap = new HashMap<>();
    loadMapPrivate(cityMap, cityList);
    return cityMap;
  }

  /**
   * 递归加载map
   *
   * @param map
   * @param list
   */
  private static void loadMapPrivate(Map<String, City> map, List<City> list) {
    if (null != list && 0 < list.size()) {
      for (City city : list) {
        map.put(city.getCode(), city);
        loadMapPrivate(map, city.getSub());
      }
    }
  }

  private static void loadListPrivate(List<City> result, List<City> list) {
    if (null != list && 0 < list.size()) {
      if (null == result) {
        result = new ArrayList<>();
      }
      for (City city : list) {
        result.add(city);
        loadListPrivate(result, city.getSub());
      }
    }
  }

  public static String getProvinceCode(String cityCode) {
    if (TextUtils.isEmpty(cityCode) || 6 != cityCode.length()) {
      return "000000";
    }
    return cityCode.substring(0, 2) + "0000";
  }

  public static String getCityCode(String cityCode) {
    if (TextUtils.isEmpty(cityCode) || 6 != cityCode.length()) {
      return "000000";
    }
    return cityCode.substring(0, 4) + "00";
  }

  public static String getDistrictCode(String cityCode) {
    if (TextUtils.isEmpty(cityCode) || 6 != cityCode.length()) {
      return "000000";
    }
    return cityCode;
  }

  /**
   * 是否是直辖市|自治区
   *
   * @param code
   * @return
   */
  public static boolean isProvinceCity(String code) {

    return code.startsWith("11") // 北京 110000
        || code.startsWith("31") // 上海 310000
        || code.startsWith("12") // 天津 120000
        || code.startsWith("50") // 重庆 500000
        || code.startsWith("81") // 香港 810000
        || code.startsWith("82"); // 澳门 820000
  }
}
