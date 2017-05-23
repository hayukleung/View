package com.hayukleung.view.UsingViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.Scroller;
import com.hayukleung.view.BaseViewGroup;

/**
 * TODO
 *
 * from 《Android 开发艺术探索》滑动冲突示例
 */
public class HorizontalScrollViewEx extends BaseViewGroup {

  private static final String TAG = HorizontalScrollViewEx.class.getSimpleName();

  private int mChildrenSize;
  private int mChildWidth;
  private int mChildIndex;
  private int mLastX = 0;
  private int mLastY = 0;
  private int mLastXIntercept = 0;
  private int mLastYIntercept = 0;
  private Scroller mScroller;
  private VelocityTracker mVelocityTracker;

  public HorizontalScrollViewEx(Context context) {
    super(context);
  }

  public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mScroller = new Scroller(context);
    mVelocityTracker = VelocityTracker.obtain();
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent event) {
    boolean intercepted = false;
    int x = (int) event.getX();
    int y = (int) event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        intercepted = false;
        if (!mScroller.isFinished()) {
          mScroller.abortAnimation();
          intercepted = true;
        }
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        int deltaX = x - mLastXIntercept;
        int deltaY = y - mLastYIntercept;
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
          intercepted = true;
        } else {
          intercepted = false;
        }
        break;
      }
      case MotionEvent.ACTION_UP: {
        intercepted = false;
        break;
      }
      default: {
        break;
      }
    }
    mLastX = x;
    mLastY = y;
    mLastXIntercept = x;
    mLastYIntercept = y;
    return intercepted;
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    mVelocityTracker.addMovement(event);
    int x = (int) event.getX();
    int y = (int) event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        if (!mScroller.isFinished()) {
          mScroller.abortAnimation();
        }
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;
        scrollBy(-deltaX, 0);
        break;
      }
      case MotionEvent.ACTION_UP: {
        int scrollX = getScrollX();
        int scrollToChildIndex = scrollX / mChildWidth;
        mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) >= 50) {
          mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
        } else {
          mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
        }
        mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
        int dx = mChildIndex * mChildWidth - scrollX;
        smoothScrollBy(dx, 0);
        mVelocityTracker.clear();
        break;
      }
      default: {
        break;
      }
    }
    mLastX = x;
    mLastY = y;
    return true;
  }

  @Override public void computeScroll() {
    if (mScroller.computeScrollOffset()) {
      scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
      postInvalidate();
    }
  }

  private void smoothScrollBy(int dx, int dy) {
    mScroller.startScroll(getScrollX(), 0, dy, 0, 500);
    invalidate();
  }
}
