package com.hayukleung.view.SonarInfiniteLoadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.hayukleung.view.BaseView;
import java.util.LinkedList;
import java.util.Queue;

/**
 * View
 * com.hayukleung.view
 * SonarInfiniteLoadingView.java
 *
 * by hayukleung
 * at 2016-12-23 16:30
 */

/**
 * 声呐加载
 */
public class SonarInfiniteLoadingView extends BaseView {

  private Paint mPaintForCircle;

  private Queue<Float> mFloatQueue;
  private Runnable mRunnable = new Runnable() {
    @Override public void run() {
      invalidate();
    }
  };

  public SonarInfiniteLoadingView(Context context) {
    super(context);
  }

  public SonarInfiniteLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SonarInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public SonarInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    mFloatQueue = new LinkedList<>();

    // frame 1
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);

    // frame 2
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);

    // frame 3
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);

    // frame 4
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);

    // frame 5
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);

    // frame 6
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);

    // frame 7
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);

    // frame 8
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);
    mFloatQueue.add(0.4f);

    // frame 9
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);
    mFloatQueue.add(0.3f);

    // frame 10
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);
    mFloatQueue.add(0.2f);

    // frame 11
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.1f);

    // frame 12
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);

    // frame 13
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);

    // frame 14
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
    mFloatQueue.add(0.0f);

    // frame 15
    mFloatQueue.add(0.4f);
    mFloatQueue.add(0.5f);
    mFloatQueue.add(0.6f);
    mFloatQueue.add(0.7f);
    mFloatQueue.add(0.8f);
    mFloatQueue.add(0.9f);
    mFloatQueue.add(0.95f);
    mFloatQueue.add(0.98f);
    mFloatQueue.add(0.0f);
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.TRANSPARENT);

    int cx, cy;
    cx = cy = getWidth() / 2;

    int radius = getWidth() / 2;

    int strokeWidth = getWidth() / 100;

    float ratio1 = 1 - getRatio();
    float ratio2 = 1 - getRatio();
    float ratio3 = 1 - getRatio();
    float ratio4 = 1 - getRatio();
    float ratio5 = 1 - getRatio();
    float ratio6 = 1 - getRatio();
    float ratio7 = 1 - getRatio();
    float ratio8 = 1 - getRatio();
    float ratio9 = 1 - getRatio();

    int radius1 = (int) (radius * ratio1);
    int radius2 = (int) (radius * ratio2);
    int radius3 = (int) (radius * ratio3);
    int radius4 = (int) (radius * ratio4);
    int radius5 = (int) (radius * ratio5);
    int radius6 = (int) (radius * ratio6);
    int radius7 = (int) (radius * ratio7);
    int radius8 = (int) (radius * ratio8);
    int radius9 = (int) (radius * ratio9);

    if (null == mPaintForCircle) {
      mPaintForCircle = new Paint();
      mPaintForCircle.setAntiAlias(true);
      mPaintForCircle.setStyle(Paint.Style.STROKE);
    }

    drawCircle(canvas, cx, cy, strokeWidth, radius1, ratio1);
    drawCircle(canvas, cx, cy, strokeWidth, radius2, ratio2);
    drawCircle(canvas, cx, cy, strokeWidth, radius3, ratio3);
    drawCircle(canvas, cx, cy, strokeWidth, radius4, ratio4);
    drawCircle(canvas, cx, cy, strokeWidth, radius5, ratio5);
    drawCircle(canvas, cx, cy, strokeWidth, radius6, ratio6);
    drawCircle(canvas, cx, cy, strokeWidth, radius7, ratio7);
    drawCircle(canvas, cx, cy, strokeWidth, radius8, ratio8);
    drawCircle(canvas, cx, cy, strokeWidth, radius9, ratio9);

    postDelayed(mRunnable, 50);
  }

  private float getRatio() {
    float ratio = mFloatQueue.poll();
    mFloatQueue.offer(ratio);

    return ratio;
  }

  private void drawCircle(Canvas canvas, int cx, int cy, int strokeWidth, int radius, float ratio) {
    mPaintForCircle.setARGB((int) (255 * (1 - ratio)), 0, 0, 0);
    mPaintForCircle.setStrokeWidth(strokeWidth * (1 - ratio));
    canvas.drawCircle(cx, cy, radius, mPaintForCircle);
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
