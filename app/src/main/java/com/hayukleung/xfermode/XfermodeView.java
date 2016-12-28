package com.hayukleung.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.hayukleung.Utils.Screen;
import com.hayukleung.view.BaseView;

public class XfermodeView extends BaseView {

  /**
   * PorterDuff模式常量
   * 可以在此更改不同的模式测试
   */
  private static final PorterDuff.Mode MODE = PorterDuff.Mode.OVERLAY;
  // 源图和目标图宽高
  private static final int SIZE_W = 120;
  private static final int SIZE_H = 120;
  private PorterDuffXfermode mPorterDuffXfermode;
  private int mScreenW, mScreenH;
  private Bitmap mSrcBitmap, mDstBitmap;
  private Paint mPaint;

  public XfermodeView(Context context) {
    super(context);
  }

  public XfermodeView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    Screen screen = Screen.getInstance(context);
    mScreenW = screen.widthPx;
    mScreenH = screen.heightPx;

    // 创建一个PorterDuffXfermode对象
    mPorterDuffXfermode = new PorterDuffXfermode(MODE);

    // 创建原图和目标图
    mSrcBitmap = makeSrc(SIZE_W, SIZE_H);
    mDstBitmap = makeDst(SIZE_W, SIZE_H);

    mPaint = new Paint();
  }

  /**
   * src
   *
   * @param w
   * @param h
   * @return
   */
  private Bitmap makeSrc(int w, int h) {
    Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(bm);
    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    p.setColor(0xFF66AAFF);
    c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
    return bm;
  }

  /**
   * dst
   *
   * @param w
   * @param h
   * @return
   */
  private Bitmap makeDst(int w, int h) {
    Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(bm);
    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    p.setColor(0xFFFFCC44);
    c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
    return bm;
  }

  @Override protected void onDraw(Canvas canvas) {
    mPaint.setFilterBitmap(false);
    mPaint.setStyle(Paint.Style.FILL);

    // 绘制src
    canvas.drawBitmap(mSrcBitmap, mScreenW / 8 - SIZE_W / 4, mScreenH / 12 - SIZE_H / 4, mPaint);

    // 绘制dst
    canvas.drawBitmap(mDstBitmap, mScreenW / 2, mScreenH / 12, mPaint);

    // 创建一个图层，在图层上演示图形混合后的效果
    int sc = canvas.saveLayer(0, 0, mScreenW, mScreenH, null, Canvas.MATRIX_SAVE_FLAG |
        Canvas.CLIP_SAVE_FLAG |
        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
        Canvas.CLIP_TO_LAYER_SAVE_FLAG);

    // 先绘制dst
    canvas.drawBitmap(mDstBitmap, mScreenW / 4, mScreenH / 3, mPaint);
    // 设置Paint的Xfermode
    mPaint.setXfermode(mPorterDuffXfermode);
    canvas.drawBitmap(mSrcBitmap, mScreenW / 4, mScreenH / 3, mPaint);
    mPaint.setXfermode(null);
    // 还原画布
    canvas.restoreToCount(sc);
  }
}