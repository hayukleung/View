package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hayukleung.App;
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
public class JsonZone {

  public static Observable<Map<String, Zone>> getZoneMap() {
    return Observable.create(new Observable.OnSubscribe<Map<String, Zone>>() {

      @Override public void call(Subscriber<? super Map<String, Zone>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadMap());
        subscriber.onCompleted();
      }
    });
  }

  public static Observable<List<Zone>> getZoneList() {
    return Observable.create(new Observable.OnSubscribe<List<Zone>>() {
      @Override public void call(Subscriber<? super List<Zone>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadList());
        subscriber.onCompleted();
      }
    });
  }

  /**
   * 加载全国行政区信息
   */
  public static List<Zone> loadList() {
    List<Zone> zoneList = null;
    Reader reader = null;
    try {
      // 从 assets 读取
      reader = new InputStreamReader(App.getInstance().getAssets().open("zone.json"));
      Type type = new TypeToken<List<Zone>>() {
      }.getType();
      zoneList = new Gson().fromJson(reader, type);
    } catch (Exception ignored) {
      Ln.d(ignored);
    } finally {
      IoUtils.safeClose(reader);
    }

    return zoneList;
  }

  public static Zone query(String cityCode) {
    if (TextUtils.isEmpty(cityCode) || 6 != cityCode.length()) {
      return null;
    }
    List<Zone> zoneList = loadList();
    for (Zone zone : zoneList) {
      if (cityCode.equals(zone.getCode())) {
        return zone;
      }
    }
    return null;
  }

  /**
   * 根据行政区编码查询行政区信息
   *
   * @param codeList 行政区编码列表
   */
  public static Observable<List<Zone>> query(final List<String> codeList) {

    return Observable.create(new Observable.OnSubscribe<List<Zone>>() {
      @Override public void call(Subscriber<? super List<Zone>> subscriber) {
        AndroidUtils.checkNotMain();
        subscriber.onNext(loadList());
        subscriber.onCompleted();
      }
    }).flatMap(new Func1<List<Zone>, Observable<List<Zone>>>() {

      @Override public Observable<List<Zone>> call(final List<Zone> zoneList) {

        return Observable.create(new Observable.OnSubscribe<List<Zone>>() {

          @Override public void call(Subscriber<? super List<Zone>> subscriber) {

            AndroidUtils.checkNotMain();

            List<Zone> resultList = new ArrayList<>(codeList.size());
            for (String code : codeList) {
              Zone result = query(zoneList, code);
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
  private static Zone query(List<Zone> all, String code) {

    if (null == all || 0 == all.size()) {
      return null;
    }
    for (Zone sub : all) {
      Zone result = queryPrivate(sub, code);
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
  private static Zone queryPrivate(Zone source, String code) {
    if (code.equals(source.getCode())) {
      return source;
    } else {
      List<Zone> sub = source.getSub();
      if (null == sub || 0 == sub.size()) {
        return null;
      }
      for (Zone zone : sub) {
        Zone result = queryPrivate(zone, code);
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
  public static Map<String, Zone> loadMap() {
    List<Zone> zoneList = loadList();
    Map<String, Zone> zoneMap = new HashMap<>();
    loadMapPrivate(zoneMap, zoneList);
    return zoneMap;
  }

  /**
   * 递归加载map
   *
   * @param map
   * @param list
   */
  private static void loadMapPrivate(Map<String, Zone> map, List<Zone> list) {
    if (null != list && 0 < list.size()) {
      for (Zone zone : list) {
        map.put(zone.getCode(), zone);
        loadMapPrivate(map, zone.getSub());
      }
    }
  }

  private static void loadListPrivate(List<Zone> result, List<Zone> list) {
    if (null != list && 0 < list.size()) {
      if (null == result) {
        result = new ArrayList<>();
      }
      for (Zone zone : list) {
        result.add(zone);
        loadListPrivate(result, zone.getSub());
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
