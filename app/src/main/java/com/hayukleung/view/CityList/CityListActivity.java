package com.hayukleung.view.CityList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.CityList
 * CityListActivity.java
 *
 * by hayukleung
 * at 2017-05-27 20:47
 */

public class CityListActivity extends AppCompatActivity {

  private IndexBar mIndexBar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_city_list);

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
      }
    });
  }
}
