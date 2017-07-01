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
import com.hayukleung.view.CircleView.CircleViewActivity;
import com.hayukleung.view.CityList.CityListActivity;
import com.hayukleung.view.CollapsibleView.UsingCollapsibleView.UsingCollapsibleViewActivity;
import com.hayukleung.view.GroupingTagView.GroupingTagViewActivity;
import com.hayukleung.view.HandleView.HandleViewActivity;
import com.hayukleung.view.IQiYiInfiniteLoadingView.IQiYiInfiniteLoadingViewActivity;
import com.hayukleung.view.MDLikeLinkTextView.MDLikeLinkTextViewActivity;
import com.hayukleung.view.PatternView.PatternViewActivity;
import com.hayukleung.view.Percentage.PercentageCircleViewActivity;
import com.hayukleung.view.ProgressBar.ProgressBarActivity;
import com.hayukleung.view.RadarInfiniteLoadingView.RadarInfiniteLoadingViewActivity;
import com.hayukleung.view.ShyaringanView.ShyaringanViewActivity;
import com.hayukleung.view.SonarInfiniteLoadingView.SonarInfiniteLoadingViewActivity;
import com.hayukleung.view.StampView.StampViewActivity;
import com.hayukleung.view.UsingViewGroup.AutoSizingFlowLayoutActivity;
import com.hayukleung.view.UsingViewGroup.FlowLayoutActivity;
import com.hayukleung.view.UsingViewGroup.UVGViewActivity;
import com.hayukleung.view.WaveView.WaveViewActivity;

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
    addEntrance(new Entrance("CircleView", new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, CircleViewActivity.class));
      }
    }));
    addEntrance(new Entrance("CityList", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, CityListActivity.class));
      }
    }));
    addEntrance(new Entrance("CLInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, CLInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("UsingCollapsibleView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, UsingCollapsibleViewActivity.class));
      }
    }));
    addEntrance(new Entrance("GroupingTagView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, GroupingTagViewActivity.class));
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
    addEntrance(new Entrance("MDLikeLinkTextView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, MDLikeLinkTextViewActivity.class));
      }
    }));
    addEntrance(new Entrance("PatternView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, PatternViewActivity.class));
      }
    }));
    addEntrance(new Entrance("PercentageCircleView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, PercentageCircleViewActivity.class));
      }
    }));
    addEntrance(new Entrance("ProgressBar", new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, ProgressBarActivity.class));
      }
    }));
    addEntrance(new Entrance("RadarInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, RadarInfiniteLoadingViewActivity.class));
      }
    }));
    addEntrance(new Entrance("ShyaringanView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, ShyaringanViewActivity.class));
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
    addEntrance(new Entrance("WaveView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, WaveViewActivity.class));
      }
    }));
    addEntrance(new Entrance("AutoSizingFlowLayout", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, AutoSizingFlowLayoutActivity.class));
      }
    }));
    addEntrance(new Entrance("FlowLayout", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, FlowLayoutActivity.class));
      }
    }));
    addEntrance(new Entrance("UVGView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, UVGViewActivity.class));
      }
    }));
  }
}
