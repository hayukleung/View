package com.hayukleung.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.hayukleung.BaseEntranceActivity;
import com.hayukleung.Entrance;
import com.hayukleung.view.BezierCurveView.BezierCurveViewActivity;
import com.hayukleung.view.BezierCurveView.BezierInfiniteLoadingViewActivity;
import com.hayukleung.view.CLInfiniteLoadingView.CLInfiniteLoadingViewActivity;
import com.hayukleung.view.HandleView.HandleViewActivity;
import com.hayukleung.view.IQiYiInfiniteLoadingView.IQiYiInfiniteLoadingViewActivity;
import com.hayukleung.view.RadarInfiniteLoadingView.RadarInfiniteLoadingViewActivity;
import com.hayukleung.view.SonarInfiniteLoadingView.SonarInfiniteLoadingViewActivity;
import com.hayukleung.view.StampView.StampViewActivity;

/**
 * View
 * com.hayukleung.view
 * ViewMainActivity.java
 *
 * by hayukleung
 * at 2016-12-26 16:54
 */

public class ViewMainActivity extends BaseEntranceActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    addEntrance(new Entrance("BezierCurveView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, BezierCurveViewActivity.class));
      }
    }));
    addEntrance(new Entrance("BezierInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, BezierInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("CLInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, CLInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("HandleView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, HandleViewActivity.class));
      }
    }));
    addEntrance(new Entrance("IQiYiInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, IQiYiInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("RadarInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, RadarInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("SonarInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, SonarInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("StampView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, StampViewActivity.class));
      }
    }));
  }
}
