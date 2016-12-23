package com.hayukleung.view.HandleView;

import android.view.MotionEvent;
import android.view.View;

/**
 * View
 * com.hayukleung.view.HandleView
 * HandleViewTouchListener.java
 *
 * by hayukleung
 * at 2016-12-22 13:54
 */

public class HandleViewTouchListener implements View.OnTouchListener {

  private HandleView mHandleView;

  public HandleViewTouchListener(HandleView handleView) {
    this.mHandleView = handleView;
    // 这里需要清除HandleView的Touch监听
    this.mHandleView.setOnTouchListener(null);
  }

  @Override public boolean onTouch(View v, MotionEvent motionEvent) {
    switch (motionEvent.getAction()) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE: {
        mHandleView.invalidate(motionEvent.getX(), motionEvent.getY());
        return true;
      }
      case MotionEvent.ACTION_UP: {
        mHandleView.invalidate(-1f, -1f);
        return true;
      }
    }
    return false;
  }
}
