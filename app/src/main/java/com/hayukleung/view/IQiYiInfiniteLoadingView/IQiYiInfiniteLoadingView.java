package com.hayukleung.view.IQiYiInfiniteLoadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view
 * IQiYiInfiniteLoadingView.java
 *
 * by hayukleung
 * at 2016-12-23 16:30
 */

/**
 * 仿爱奇艺客户端加载动画
 */
public class IQiYiInfiniteLoadingView extends BaseView {

  private Paint mPaintForCircle;
  private RectF mRectFForCircle;
  private Paint mPaintForTriangle;
  private Path mPathForTriangle;

  private int mAngleForCanvasTriangle = -10;
  private int mAngleForCanvasCircle = -5;
  private int mAngleForCircle = -2;

  private Runnable mRunnable = new Runnable() {
    @Override public void run() {
      invalidate();
    }
  };

  public IQiYiInfiniteLoadingView(Context context) {
    super(context);
  }

  public IQiYiInfiniteLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public IQiYiInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public IQiYiInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
  }

  @Override protected void onDraw(Canvas canvas) {

    canvas.drawColor(Color.TRANSPARENT);

    int cx = getWidth() / 2;
    int cy = getWidth() / 2;

    if (null == mPaintForCircle) {
      mPaintForCircle = new Paint();
      mPaintForCircle.setAntiAlias(true);
      mPaintForCircle.setStyle(Paint.Style.STROKE);
      mPaintForCircle.setStrokeWidth(getWidth() / 50);
      mPaintForCircle.setColor(Color.argb(255, 119, 166, 38));
    }

    if (null == mPaintForTriangle) {
      mPaintForTriangle = new Paint();
      mPaintForTriangle.setAntiAlias(true);
      mPaintForTriangle.setColor(Color.argb(255, 119, 166, 38));
    }

    if (null == mRectFForCircle) {
      int padding = getWidth() / 25;
      mRectFForCircle = new RectF(padding, padding, getWidth() - padding, getHeight() - padding);
    }

    mPaintForCircle.setColor(Color.argb(255, 119, 166, 38));

    canvas.rotate(-getAngleForCanvasCircle(), cx, cy);
    canvas.drawArc(mRectFForCircle, 0, -getAngleForCircle(), false, mPaintForCircle);
    canvas.save();
    canvas.restore();

    canvas.rotate(getAngleForCanvasTriangle(), cx, cy);
    float size = getWidth() / 4;
    canvas.drawPath(getTrianglePath(size, cx, cy), mPaintForTriangle);
    canvas.restore();

    postDelayed(mRunnable, 50);
  }

  private int getAngleForCanvasCircle() {
    mAngleForCanvasCircle += 5;
    if (mAngleForCanvasCircle > 360) {
      mAngleForCanvasCircle %= 360;
    }
    return mAngleForCanvasCircle;
  }

  private int getAngleForCircle() {
    mAngleForCircle += 2;
    if (mAngleForCircle > 360) {
      mAngleForCircle %= 360;
    }
    return mAngleForCircle;
  }

  private int getAngleForCanvasTriangle() {
    mAngleForCanvasTriangle += 10;
    if (mAngleForCanvasTriangle > 360) {
      mAngleForCanvasTriangle %= 360;
    }
    return mAngleForCanvasTriangle;
  }

  /**
   * 获取等边三角形路径
   *
   * @param size
   * @param cx
   * @param cy
   * @return
   */
  private Path getTrianglePath(float size, int cx, int cy) {
    if (null == mPathForTriangle) {
      mPathForTriangle = new Path();
      mPathForTriangle.moveTo(cx, cy - size);
      mPathForTriangle.lineTo((int) (cx + size * Math.sqrt(3) / 2), cy + size / 2);
      mPathForTriangle.lineTo((int) (cx - size * Math.sqrt(3) / 2), cy + size / 2);
      mPathForTriangle.close();
    }
    return mPathForTriangle;
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
  }
}
