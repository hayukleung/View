package com.hayukleung.view.StampView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.hayukleung.view.BaseView;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * View
 * com.hayukleung.view.StampView
 * StampView.java
 *
 * by hayukleung
 * at 2016-12-27 16:30
 */

/**
 * 邮票锯齿效果
 */
public class StampView extends BaseView {

  private Paint mPaint;
  private RectF mRectFForTooth;

  private int mRadius;

  private PorterDuffXfermode mPorterDuffXfermode;

  public StampView(Context context) {
    super(context);
  }

  public StampView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public StampView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public StampView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setStrokeWidth(0);

    mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    // 禁止GPU加速
    // 参见 http://blog.csdn.net/iispring/article/details/49835061
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
  }

  @Override protected void onDraw(Canvas canvas) {

    canvas.drawColor(Color.TRANSPARENT);

    int width = getWidth();
    int height = getHeight();

    int padding = mRadius;

    int rectW = width - 2 * padding;
    int rectH = height - 2 * padding;

    int diameter = mRadius * 2;

    mPaint.setColor(Color.parseColor("#FF000000"));

    canvas.drawRect(padding, padding, width - padding, height - padding, mPaint);

    mPaint.setColor(Color.parseColor("#FFFF0000"));

    // 绘制上下锯齿
    for (int i = 0; i < rectW / (mRadius * 2); i++) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        canvas.drawArc(padding + diameter * i, padding - mRadius, padding + diameter * (i + 1),
            padding + mRadius, 180, 180, true, mPaint);
        canvas.drawArc(padding + diameter * i, padding - mRadius + rectH,
            padding + diameter * (i + 1), padding + mRadius + rectH, 0, 180, true, mPaint);
      } else {
        if (null == mRectFForTooth) {
          mRectFForTooth = new RectF();
        }
        mRectFForTooth.set(padding + diameter * i, padding - mRadius, padding + diameter * (i + 1),
            padding + mRadius);
        canvas.drawArc(mRectFForTooth, 180, 180, false, mPaint);
        mRectFForTooth.set(padding + diameter * i, padding - mRadius + rectH,
            padding + diameter * (i + 1), padding + mRadius + rectH);
        canvas.drawArc(mRectFForTooth, 0, 180, false, mPaint);
      }
    }

    mPaint.setXfermode(mPorterDuffXfermode);

    // 绘制左右锯齿
    for (int i = 0; i < rectH / (mRadius * 2); i++) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        canvas.drawArc(padding - mRadius, padding + diameter * i, padding + mRadius,
            padding + diameter * (i + 1), 270, 180, true, mPaint);
        canvas.drawArc(padding - mRadius + rectW, padding + diameter * i, padding + mRadius + rectW,
            padding + diameter * (i + 1), 90, 180, true, mPaint);
      } else {
        if (null == mRectFForTooth) {
          mRectFForTooth = new RectF();
        }
        mRectFForTooth.set(padding - mRadius, padding + diameter * i, padding + mRadius,
            padding + diameter * (i + 1));
        canvas.drawArc(mRectFForTooth, 270, 180, false, mPaint);
        mRectFForTooth.set(padding - mRadius + rectW, padding + diameter * i,
            padding + mRadius + rectW, padding + diameter * (i + 1));
        canvas.drawArc(mRectFForTooth, 90, 180, false, mPaint);
      }
    }
    mPaint.setXfermode(null);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // w : h = 5 : 6
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    mRadius = size / 20;
    int wMeasureSpec = MeasureSpec.makeMeasureSpec(mRadius * 20, EXACTLY);
    int hMeasureSpec = MeasureSpec.makeMeasureSpec(mRadius * 24, MeasureSpec.EXACTLY);
    setMeasuredDimension(wMeasureSpec, hMeasureSpec);
  }
}
