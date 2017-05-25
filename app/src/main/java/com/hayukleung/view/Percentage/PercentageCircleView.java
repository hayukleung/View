package com.hayukleung.view.Percentage;

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
 * com.hayukleung.view.Percentage
 * PercentageCircleView.java
 *
 * by hayukleung
 * at 2017-05-25 09:53
 */

public class PercentageCircleView extends BaseView {

  private Path mPath;
  private RectF mRectF;
  private Paint mPaint;

  private int mTotal;
  private int mCurrent;

  public PercentageCircleView(Context context) {
    super(context);
  }

  public PercentageCircleView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public PercentageCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public PercentageCircleView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public void setTotal(int total) {
    mTotal = total;
  }

  public void setCurrent(int current) {
    mCurrent = current;
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPath = new Path();
    mRectF = new RectF();
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  }

  @Override protected void onDraw(Canvas canvas) {

    float percentage = (float) mCurrent / (float) mTotal;
    float sweepAngle = percentage * 360f;
    float sweepAngleNum = (float) (percentage * Math.PI * 2f);

    int cx = getWidth() / 2;
    int cy = getHeight() / 2;

    int r = getWidth() / 10 * 9 / 2;
    int deltaRadius = r / 10;
    int r3 = r;
    int r2 = r3 - deltaRadius;
    int r1 = r2 - deltaRadius;

    mPath.reset();
    mPath.moveTo(cx + r3, cy);
    mRectF.set(cx - r3, cy - r3, cx + r3, cy + r3);
    mPath.arcTo(mRectF, 0, sweepAngle);

    // TODO
    // mPath.lineTo((int) (cx + Math.cos(sweepAngleNum) * r1), (int) (cy + Math.sin(sweepAngleNum) * r1));

    mRectF.set(cx - r1, cy - r1, cx + r1, cy + r1);
    mPath.arcTo(mRectF, sweepAngle, -sweepAngle);
    mPath.close();

    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.FILL);
    canvas.drawPath(mPath, mPaint);

    int circleX = cx + r2;
    int circleY = cy;
    canvas.drawCircle(circleX, circleY, deltaRadius, mPaint);

    circleX = cx + (int) (Math.cos(sweepAngleNum) * (float) r2);
    circleY = cy + (int) (Math.sin(sweepAngleNum) * (float) r2);
    canvas.drawCircle(circleX, circleY, deltaRadius, mPaint);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
  }
}
