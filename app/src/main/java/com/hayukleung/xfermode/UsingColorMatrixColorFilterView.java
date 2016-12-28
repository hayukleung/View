package com.hayukleung.xfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.hayukleung.Utils.Screen;
import com.hayukleung.view.BaseView;

/**
 * http://www.cnblogs.com/plokmju/p/android_ColorMatrix.html
 */
public class UsingColorMatrixColorFilterView extends BaseView {

  private Paint mPaint;

  private int mScreenW, mScreenH;

  public UsingColorMatrixColorFilterView(Context context) {
    super(context);
  }

  public UsingColorMatrixColorFilterView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public UsingColorMatrixColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public UsingColorMatrixColorFilterView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    // 初始化Paint，并且设置消除锯齿。
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 设置画笔样式为描边
    mPaint.setStyle(Paint.Style.FILL);
    // 设置描边的粗细，单位：像素px 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
    mPaint.setStrokeWidth(20);
    // 设置画笔颜色为自定义颜色
    mPaint.setColor(Color.argb(255, 255, 255, 255));
    ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(new float[] {
        0.5F, 0, 0, 0, 0, // R
        0, 0.5F, 0, 0, 0, // G
        0, 0, 0.5F, 0, 0, // B
        0, 0, 0, 1, 0     // A
    });
    mPaint.setColorFilter(colorFilter);

    Screen screen = Screen.getInstance(context);
    mScreenW = screen.widthPx;
    mScreenH = screen.heightPx;
  }

  @Override protected void onDraw(Canvas canvas) {
    // 画一个圆形，取屏幕中心点为圆心
    canvas.drawCircle(mScreenW / 2, mScreenH / 2, 100, mPaint);
  }
}














