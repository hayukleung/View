package com.hayukleung.view.RadarInfiniteLoadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view
 * RadarInfiniteLoadingView.java
 *
 * by hayukleung
 * at 2016-12-23 16:30
 */

/**
 * 雷达加载
 */
public class RadarInfiniteLoadingView extends BaseView {

  private Paint mPaintForCircle;

  private int mAngle = 0;
  private int mCx, mCy;
  private SweepGradient mSweepGradient;
  private Matrix mMatrix;
  private Runnable mRunnable = new Runnable() {
    @Override public void run() {
      mMatrix.postRotate(10, mCx, mCy);
      invalidate();
    }
  };

  public RadarInfiniteLoadingView(Context context) {
    super(context);
  }

  public RadarInfiniteLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RadarInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public RadarInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mMatrix = new Matrix();
  }

  @Override protected void onDraw(Canvas canvas) {

    canvas.drawColor(Color.TRANSPARENT);

    if (null == mPaintForCircle) {
      mPaintForCircle = new Paint();
      mPaintForCircle.setAntiAlias(true);
      mPaintForCircle.setStyle(Paint.Style.FILL);
    }

    mCx = mCy = getWidth() / 2;

    // canvas.setMatrix(mMatrix);

    if (null == mSweepGradient) {
      mSweepGradient = new SweepGradient(mCx, mCy, new int[] {
          0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x01000000, 0x02000000,
          0x04000000, 0x08000000, 0x11000000, 0x22000000, 0x44000000, 0x77000000, 0xBB000000
      }, null);
    }
    mPaintForCircle.setShader(mSweepGradient);

    canvas.rotate(getAngle(), mCx, mCy);
    canvas.drawCircle(mCx, mCy, getWidth() / 2, mPaintForCircle);
    canvas.restore();

    postDelayed(mRunnable, 16);
  }

  private int getAngle() {
    mAngle += 10;
    mAngle %= 360;
    return mAngle;
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
