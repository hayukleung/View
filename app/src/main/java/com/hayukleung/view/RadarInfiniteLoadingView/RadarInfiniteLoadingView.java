package com.hayukleung.view.RadarInfiniteLoadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
