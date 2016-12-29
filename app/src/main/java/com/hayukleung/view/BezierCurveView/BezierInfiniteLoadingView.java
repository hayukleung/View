package com.hayukleung.view.BezierCurveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import com.hayukleung.Utils.Screen;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view.BezierCurveView
 * BezierInfiniteLoadingView.java
 *
 * by hayukleung
 * at 2016-12-29 13:41
 */

public class BezierInfiniteLoadingView extends BaseView {

  /** 帧数 */
  private static final int FRAME_COUNT = 20;

  private Paint mPaint;
  private Path mPath;
  private int mRatio;
  private int mDelta;

  public BezierInfiniteLoadingView(Context context) {
    super(context);
  }

  public BezierInfiniteLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BezierInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public BezierInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPath = new Path();
    mRatio = FRAME_COUNT;
    mDelta = 1;
  }

  @Override protected void onDraw(Canvas canvas) {

    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.RED);

    float wRatio = 3.5f;

    // 中心
    float mx = (float) getWidth() / 2f;
    float my = (float) getHeight() / 2f;

    // 画圆
    // 左圆半径
    float radiusL = my * (float) mRatio / (float) FRAME_COUNT;
    float cxL = (float) getWidth() / wRatio;
    float cyL = my;
    // 右圆半径
    float radiusR = my * (float) (FRAME_COUNT - mRatio) / (float) FRAME_COUNT;
    float cxR = (float) getWidth() - (float) getWidth() / wRatio;
    float cyR = my;
    canvas.drawCircle(cxL, cyL, radiusL, mPaint);
    canvas.drawCircle(cxR, cyR, radiusR, mPaint);

    // 画贝塞尔曲线

    // 左上x
    // float ltx = (float) getWidth() / wRatio;
    float ltx = cxL + radiusL * radiusL / (mx - cxL);
    // 左上y
    // float lty = my * (float) (FRAME_COUNT - mRatio) / (float) FRAME_COUNT;
    float lty = (float) ((double) cyL - Math.sqrt(radiusL * radiusL - (ltx - cxL) * (ltx - cxL)));
    // Log.e(BezierInfiniteLoadingView.class.getSimpleName(), String.format("(%f, %f)", ltx, lty));
    // 右上x
    // float rtx = (float) getWidth() - (float) getWidth() / wRatio;
    float rtx = cxR - radiusR * radiusR / (cxR - mx);
    // 右上y
    // float rty = my * (float) mRatio / (float) FRAME_COUNT;
    float rty = (float) ((double) my - Math.sqrt(radiusR * radiusR - (cxR - rtx) * (cxR - rtx)));
    // 右下x
    // float rbx = (float) getWidth() - (float) getWidth() / wRatio;
    float rbx = rtx;
    // 右下y
    // float rby = my * (float) mRatio / (float) FRAME_COUNT + (float) getHeight() * (float) (FRAME_COUNT - mRatio) / (float) FRAME_COUNT;
    float rby = my + my - rty;
    // 左下x
    // float lbx = (float) getWidth() / wRatio;
    float lbx = ltx;
    // 左下y
    // float lby = my * (float) (FRAME_COUNT - mRatio) / (float) FRAME_COUNT + (float) getHeight() * (float) mRatio / (float) FRAME_COUNT;
    float lby = my + my - lty;

    mPath.reset();
    // 左上
    mPath.moveTo(ltx, lty);
    // 右上
    mPath.quadTo(mx, my, rtx, rty);
    // 右下
    mPath.lineTo(rbx, rby);
    // 左下
    mPath.quadTo(mx, my, lbx, lby);
    mPath.close();

    mPaint.setColor(Color.RED);
    canvas.drawPath(mPath, mPaint);

    mRatio -= mDelta;
    Log.e(BezierInfiniteLoadingView.class.getSimpleName(), mRatio + "");
    mDelta = (0 == mRatio || FRAME_COUNT == mRatio) ? -mDelta : mDelta;
    postInvalidateDelayed(50);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int width = Screen.getInstance(getContext()).widthPx;
    wSize = wSize < width ? wSize : width;

    // 宽高比2:1
    setMeasuredDimension(MeasureSpec.makeMeasureSpec(wSize, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(wSize / 2, MeasureSpec.EXACTLY));
  }
}
