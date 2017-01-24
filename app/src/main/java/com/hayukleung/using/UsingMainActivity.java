package com.hayukleung.using;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.hayukleung.BaseEntranceActivity;
import com.hayukleung.Entrance;

/**
 * View
 * com.hayukleung.using
 * UsingMainActivity.java
 *
 * by hayukleung
 * at 2016-12-30 09:59
 */

public class UsingMainActivity extends BaseEntranceActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addEntrance(new Entrance("UsingCoordinatorLayout", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(UsingMainActivity.this, UsingCoordinatorLayoutActivity.class));
      }
    }));
  }
}
