package com.hayukleung.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import com.hayukleung.Utils.Screen;
import com.hayukleung.view.BaseView;
import com.hayukleung.view.R;

public class UsingPorterDuffColorFilterView extends BaseView {

  private Paint mPaint;
  private Bitmap mBitmap;
  private int mX, mY;

  public UsingPorterDuffColorFilterView(Context context) {
    super(context);
  }

  public UsingPorterDuffColorFilterView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public UsingPorterDuffColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public UsingPorterDuffColorFilterView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    // 获取图片
    mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image);
    // 获取图片显示起始位置
    Screen screen = Screen.getInstance(context);
    mX = screen.widthPx / 2 - mBitmap.getWidth() / 2;
    mY = screen.heightPx / 2 - mBitmap.getHeight() / 2;

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    PorterDuffColorFilter colorFilter =
        new PorterDuffColorFilter(0XFFFF0000, PorterDuff.Mode.DARKEN);
    mPaint.setColorFilter(colorFilter);
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(mBitmap, mX, mY, mPaint);
  }
}