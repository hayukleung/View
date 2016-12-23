package com.hayukleung.view.HandleView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.HandleView
 * HandleViewActivity.java
 *
 * by hayukleung
 * at 2016-12-21 18:29
 */

public class HandleViewActivity extends AppCompatActivity {

  private HandleView mHandleView;

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_handle_view);

    // 操纵区域在HandleView内
    mHandleView = (HandleView) findViewById(R.id.handle_view);
    mHandleView.setOnTouchListener(new HandleViewTouchListener(mHandleView));

    // 操纵区域在HandleView的父布局内
    View touchZoom = findViewById(R.id.touch_zoom);
    touchZoom.setOnTouchListener(new HandleViewTouchListener(mHandleView));

    // 最后一次调用HandleViewTouchListener构造函数的生效
  }

  @Override protected void onResume() {
    super.onResume();
    // 回中心位置
    mHandleView.invalidate();
  }
}
