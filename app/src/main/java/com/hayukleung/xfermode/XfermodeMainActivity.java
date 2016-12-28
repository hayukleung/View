package com.hayukleung.xfermode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.hayukleung.BaseEntranceActivity;
import com.hayukleung.Entrance;

/**
 * View
 * com.hayukleung.xfermode
 * XfermodeMainActivity.java
 *
 * by hayukleung
 * at 2016-12-28 10:10
 */

public class XfermodeMainActivity extends BaseEntranceActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addEntrance(new Entrance("UsingColorMatrixColorFilterView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(
            new Intent(XfermodeMainActivity.this, UsingColorMatrixColorFilterViewActivity.class));
      }
    }));

    addEntrance(new Entrance("UsingPorterDuffColorFilterView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(
            new Intent(XfermodeMainActivity.this, UsingPorterDuffColorFilterViewActivity.class));
      }
    }));

    addEntrance(new Entrance("XfermodeView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(XfermodeMainActivity.this, XfermodeViewActivity.class));
      }
    }));
  }
}
