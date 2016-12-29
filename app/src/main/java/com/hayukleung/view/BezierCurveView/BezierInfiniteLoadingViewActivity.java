package com.hayukleung.view.BezierCurveView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * View
 * com.hayukleung.view.BezierCurveView
 * BezierInfiniteLoadingViewActivity.java
 *
 * by hayukleung
 * at 2016-12-29 14:05
 */

public class BezierInfiniteLoadingViewActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new BezierInfiniteLoadingView(this));
  }
}
