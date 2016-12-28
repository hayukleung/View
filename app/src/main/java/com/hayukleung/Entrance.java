package com.hayukleung;

import android.view.View;

/**
 * View
 * com.hayukleung
 * Entrance.java
 *
 * by hayukleung
 * at 2016-12-28 09:54
 */

public class Entrance {

  private String name;
  private View.OnClickListener listener;

  public Entrance(String name, View.OnClickListener listener) {
    this.name = name;
    this.listener = listener;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public View.OnClickListener getListener() {
    return listener;
  }

  public void setListener(View.OnClickListener listener) {
    this.listener = listener;
  }
}
