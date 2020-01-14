package com.hayukleung.view.BezierCurveView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * View
 * com.hayukleung.view.BezierCurveView
 * BezierCurveViewActivity.java
 *
 * by hayukleung
 * at 2016-12-29 10:52
 */

public class BezierCurveViewActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    BezierCurveView bezierCurveView = new BezierCurveView(this);
    setContentView(bezierCurveView);
  }
}
