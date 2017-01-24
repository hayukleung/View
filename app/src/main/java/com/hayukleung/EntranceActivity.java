package com.hayukleung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.hayukleung.using.UsingMainActivity;
import com.hayukleung.view.ViewMainActivity;
import com.hayukleung.xfermode.XfermodeMainActivity;

/**
 * View
 * com.hayukleung.view
 * ViewMainActivity.java
 *
 * by hayukleung
 * at 2016-12-26 16:54
 */

public class EntranceActivity extends BaseEntranceActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addEntrance(new Entrance("UsingMain", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(EntranceActivity.this, UsingMainActivity.class));
      }
    }));
    addEntrance(new Entrance("ViewMain", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(EntranceActivity.this, ViewMainActivity.class));
      }
    }));
    addEntrance(new Entrance("XfermodeMain", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(EntranceActivity.this, XfermodeMainActivity.class));
      }
    }));
  }
}
