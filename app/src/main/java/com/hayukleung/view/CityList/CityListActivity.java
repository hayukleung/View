package com.hayukleung.view.CityList;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.hayukleung.view.CollapsibleView.UsingCollapsibleView.PausedHandler;
import com.hayukleung.view.CollapsibleView.UsingCollapsibleView.PausedHandlerScheduler;
import com.hayukleung.view.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * View
 * com.hayukleung.view.CityList
 * CityListActivity.java
 *
 * by hayukleung
 * at 2017-05-27 20:47
 */

public class CityListActivity extends AppCompatActivity {

  final Map<String, Integer> mIndexMap = new HashMap<>(26);
  private PausedHandler mHandler = new Handler(this);
  private RecyclerView mRecyclerView;
  private IndexBar mIndexBar;

  private List<City> mCityList = new ArrayList<>(10);

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_city_list);

    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    final LinearLayoutManager linearLayoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mRecyclerView.setAdapter(new CityListAdapter(mCityList));

    {
      final HanziToPinyin hanziToPinyin = HanziToPinyin.getInstance();
      JsonCity.getCityList()
          .subscribeOn(Schedulers.io())
          .observeOn(PausedHandlerScheduler.from(this.mHandler))
          .subscribe(new Action1<List<City>>() {
            @Override public void call(List<City> cities) {

              for (City city : cities) {
                List<HanziToPinyin.Token> pinyin = hanziToPinyin.get(city.getName());
                String pym = "";
                for (HanziToPinyin.Token token : pinyin) {
                  pym += token.target;
                }
                city.setPinyin(pym);
              }

              Collections.sort(cities);

              Map<String, City> map = new HashMap<>(cities.size());
              // A-Z
              for (City city : cities) {
                String key = city.getPinyin().substring(0, 1);
                if (map.containsKey(key)) {
                  city.setIndex(false);
                } else {
                  city.setIndex(true);
                  map.put(key, city);
                  mIndexMap.put(key, cities.indexOf(city));
                }
              }
              mCityList.clear();
              mCityList.addAll(cities);
              mRecyclerView.getAdapter().notifyDataSetChanged();
            }
          });
    }

    mIndexBar = (IndexBar) findViewById(R.id.index_bar);
    mIndexBar.setIndex(new String[] {
        "A", "B", "C", "D", "E", "F", "G", //
        "H", "I", "J", "K", "L", "M", "N", //
        "O", "P", "Q", "R", "S", "T", //
        "U", "V", "W", "X", "Y", "Z" //
    });
    mIndexBar.setOnIndexTouchListener(new IndexBar.OnIndexTouchListener() {
      @Override public void onTouch(String strIndex) {
        Log.e("index --> ", strIndex);
        Integer position = mIndexMap.get(strIndex);
        if (null != position) {
          // mRecyclerView.scrollToPosition(position);
          linearLayoutManager.scrollToPositionWithOffset(position, 0);
        }
      }
    });
  }

  @Override protected void onPause() {
    super.onPause();
    mHandler.pause();
  }

  @Override protected void onResume() {
    super.onResume();
    mHandler.resume();
  }

  private static class Handler extends PausedHandler {

    private WeakReference<CityListActivity> mCityListActivityWeakReference;

    public Handler(CityListActivity cityListActivity) {
      this.mCityListActivityWeakReference = new WeakReference(cityListActivity);
    }

    @Override protected void processMessage(Message message) {

    }
  }
}
