package com.hayukleung.view.BezierCurveView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
