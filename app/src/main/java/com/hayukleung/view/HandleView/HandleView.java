package com.hayukleung.view.HandleView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view.HandleView
 * HandleView.java
 *
 * by hayukleung
 * at 2016-12-21 18:27
 */

/**
 * 轮盘操纵
 */
public class HandleView extends BaseView {

  private Paint mPaintForCircle;
  private float mX, mY;

  public HandleView(Context context) {
    super(context);
  }

  public HandleView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HandleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public HandleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    // do nothing
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.TRANSPARENT);

    int radiusOuter = getWidth() / 2;
    int radiusInner = getWidth() / 5;
    float cx = getWidth() / 2;
    float cy = getHeight() / 2;

    if (null == mPaintForCircle) {
      mPaintForCircle = new Paint();
      mPaintForCircle.setAntiAlias(true);
      mPaintForCircle.setStyle(Paint.Style.FILL);
    }

    mPaintForCircle.setColor(Color.argb(0x7f, 0x11, 0x11, 0x11));
    canvas.drawCircle(cx, cy, radiusOuter, mPaintForCircle);

    if (0 > this.mX || 0 > this.mY) {
      // 回中心位置
      mPaintForCircle.setColor(Color.argb(0xff, 0x11, 0x11, 0x11));
      canvas.drawCircle(cx, cy, radiusInner, mPaintForCircle);
      canvas.save();
      return;
    }

    int modifyLeft = mCoordinatorModify ? getLeft() : 0;
    int modifyTop = mCoordinatorModify ? getTop() : 0;

    double ratio = (radiusOuter - radiusInner) / Math.sqrt(
        Math.pow(this.mX - cx - modifyLeft, 2) + Math.pow(this.mY - cy - modifyTop, 2));
    float cx2 = (float) (ratio * (this.mX - cx - modifyLeft) + cx);
    float cy2 = (float) (ratio * (this.mY - cy - modifyTop) + cy);

    mPaintForCircle.setColor(Color.argb(0xff, 0x11, 0x11, 0x11));
    canvas.drawCircle(cx2, cy2, radiusInner, mPaintForCircle);
    canvas.save();
  }

  /**
   * 是否需要调整内圆圆心坐标
   */
  private boolean mCoordinatorModify = true;

  @Override public void setOnTouchListener(OnTouchListener listener) {
    // 如果HandleViewTouch监听是直接设置给HandleView，就不需要进行内圆圆心坐标调整
    mCoordinatorModify = !(listener instanceof HandleViewTouchListener);
    super.setOnTouchListener(listener);
  }

  @Override public void invalidate() {
    invalidate(-1f, -1f);
  }

  /**
   * 替代invalidate()
   *
   * @param x
   * @param y
   */
  public void invalidate(float x, float y) {
    this.mX = x;
    this.mY = y;
    super.invalidate();
  }
}
