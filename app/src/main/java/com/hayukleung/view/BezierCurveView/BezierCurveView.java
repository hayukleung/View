package com.hayukleung.view.BezierCurveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view.BezierCurveView
 * BezierCurveView.java
 *
 * by hayukleung
 * at 2016-12-29 10:40
 */

/**
 * 贝塞尔曲线
 */
public class BezierCurveView extends BaseView {

  private Paint mPaint;
  private Path mPath;
  private PathEffect mPathEffect;

  /** 辅助点 */
  private float mSupX = -1f, mSupY = -1f;

  public BezierCurveView(Context context) {
    super(context);
  }

  public BezierCurveView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BezierCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public BezierCurveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPath = new Path();
    // 虚线相对于起点偏移0px
    // 2px实线与4px虚线交替
    mPathEffect = new DashPathEffect(new float[] { 2, 4, 2, 4 }, 0);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_UP: {
        mSupX = getWidth() / 2;
        mSupY = getHeight() / 2;
        invalidate();
        return true;
      }
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE: {
        mSupX = event.getX();
        mSupY = event.getY();
        invalidate();
        return true;
      }
      default: {
        return true;
      }
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    float startX = getWidth() / 8;
    float startY = getHeight() / 2;
    float endX = getWidth() - getWidth() / 8;
    float endY = getHeight() / 2;

    // 赛贝尔曲线
    mPath.reset();
    mPath.moveTo(startX, startY);
    // 辅助点 & 终点
    if (0 > mSupX || 0 > mSupY) {
      mSupX = getWidth() / 2;
      mSupY = getHeight() / 2;
    }
    mPath.quadTo(mSupX, mSupY, endX, endY);
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(10);
    mPaint.setPathEffect(null);
    canvas.drawPath(mPath, mPaint);

    // 辅助线
    mPath.reset();
    mPath.moveTo(startX, startY);
    mPath.lineTo(mSupX, mSupY);
    mPath.lineTo(endX, endY);
    mPaint.setStrokeWidth(4);
    mPaint.setPathEffect(mPathEffect);
    canvas.drawPath(mPath, mPaint);

    // 辅助点
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.FILL);
    canvas.drawCircle(mSupX, mSupY, getWidth() / 20, mPaint);
  }
}
