package com.hayukleung.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.hayukleung.Utils.Screen;
import com.hayukleung.view.BaseView;

public class PorterDuffXfermodeView extends BaseView {

  /**
   * PorterDuff模式常量
   *
   * [0, 0]
   * CLEAR        (0),
   *
   * [Sa, Sc]
   * SRC          (1),
   *
   * [Da, Dc]
   * DST          (2),
   *
   * [Sa + (1 - Sa) * Da, Rc = Sc + (1 - Sa) * Dc]
   * SRC_OVER     (3),
   *
   * [Sa + (1 - Sa) * Da, Rc = Dc + (1 - Da) * Sc]
   * DST_OVER     (4),
   *
   * [Sa * Da, Sc * Da]
   * SRC_IN       (5),
   *
   * [Sa * Da, Sa * Dc]
   * DST_IN       (6),
   *
   * [Sa * (1 - Da), Sc * (1 - Da)]
   * SRC_OUT      (7),
   *
   * [Da * (1 - Sa), Dc * (1 - Sa)]
   * DST_OUT      (8),
   *
   * [Da, Sc * Da + (1 - Sa) * Dc]
   * SRC_ATOP     (9),
   *
   * [Sa, Sa * Dc + Sc * (1 - Da)]
   * DST_ATOP    (10),
   *
   * [Sa + Da - 2 * Sa * Da, Sc * (1 - Da) + (1 - Sa) * Dc]
   * XOR         (11),
   *
   * [Sa + Da - Sa * Da, Sc * (1 - Da) + Dc * (1 - Sa) + min(Sc, Dc)]
   * DARKEN      (16),
   *
   * [Sa + Da - Sa * Da, Sc * (1 - Da) + Dc * (1 - Sa) + max(Sc, Dc)]
   * LIGHTEN     (17),
   *
   * [Sa * Da, Sc * Dc]
   * MULTIPLY    (13),
   *
   * [Sa + Da - Sa * Da, Sc + Dc - Sc * Dc]
   * SCREEN      (14),
   *
   * Saturate(S + D)
   * ADD         (12),
   *
   * OVERLAY     (15);
   */

  // 源图和目标图宽高
  // private static final int SIZE_W = 120;
  // private static final int SIZE_H = 120;

  private PorterDuffXfermode mPorterDuffXfermodeClear;
  private PorterDuffXfermode mPorterDuffXfermodeSrc;
  private PorterDuffXfermode mPorterDuffXfermodeDst;
  private PorterDuffXfermode mPorterDuffXfermodeSrcOver;
  private PorterDuffXfermode mPorterDuffXfermodeDstOver;
  private PorterDuffXfermode mPorterDuffXfermodeSrcIn;
  private PorterDuffXfermode mPorterDuffXfermodeDstIn;
  private PorterDuffXfermode mPorterDuffXfermodeSrcOut;
  private PorterDuffXfermode mPorterDuffXfermodeDstOut;
  private PorterDuffXfermode mPorterDuffXfermodeSrcAtop;
  private PorterDuffXfermode mPorterDuffXfermodeDstAtop;
  private PorterDuffXfermode mPorterDuffXfermodeXOR;
  private PorterDuffXfermode mPorterDuffXfermodeDarken;
  private PorterDuffXfermode mPorterDuffXfermodeLighten;
  private PorterDuffXfermode mPorterDuffXfermodeMultiply;
  private PorterDuffXfermode mPorterDuffXfermodeScreen;
  private PorterDuffXfermode mPorterDuffXfermodeAdd;
  private PorterDuffXfermode mPorterDuffXfermodeOverlay;
  private Bitmap mSrcBitmap, mDstBitmap;
  private Paint mPaint;
  private Paint mPaintForText;

  public PorterDuffXfermodeView(Context context) {
    super(context);
  }

  public PorterDuffXfermodeView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public PorterDuffXfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public PorterDuffXfermodeView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    // 创建PorterDuffXfermode对象
    mPorterDuffXfermodeClear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    mPorterDuffXfermodeSrc = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    mPorterDuffXfermodeDst = new PorterDuffXfermode(PorterDuff.Mode.DST);
    mPorterDuffXfermodeSrcOver = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
    mPorterDuffXfermodeDstOver = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
    mPorterDuffXfermodeSrcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    mPorterDuffXfermodeDstIn = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    mPorterDuffXfermodeSrcOut = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    mPorterDuffXfermodeDstOut = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    mPorterDuffXfermodeSrcAtop = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    mPorterDuffXfermodeDstAtop = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
    mPorterDuffXfermodeXOR = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    mPorterDuffXfermodeDarken = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
    mPorterDuffXfermodeLighten = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
    mPorterDuffXfermodeMultiply = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
    mPorterDuffXfermodeScreen = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
    mPorterDuffXfermodeAdd = new PorterDuffXfermode(PorterDuff.Mode.ADD);
    mPorterDuffXfermodeOverlay = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);

    mPaint = new Paint();
    mPaintForText = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintForText.setColor(Color.WHITE);
    mPaintForText.setTextAlign(Paint.Align.CENTER);

    // 禁止GPU加速
    // 参见 http://blog.csdn.net/iispring/article/details/49835061
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
  }

  @Override protected void onDraw(Canvas canvas) {

    canvas.drawColor(Color.BLACK);

    mPaint.setFilterBitmap(false);
    mPaint.setStyle(Paint.Style.FILL);

    int size = getWidth() / 9;

    mPaintForText.setTextSize(Screen.getInstance(getContext()).sp2px(16));

    // 绘制src
    canvas.drawBitmap(genSrc(size, size), size, size, mPaint);
    canvas.drawText("src", size / 2 + size, (int) (size * (2 + 0.3f)), mPaintForText);

    // 绘制dst
    canvas.drawBitmap(genDst(size, size), getWidth() - size * 2, size, mPaint);
    canvas.drawText("dst", size / 2 + size * 7, (int) (size * (2 + 0.3f)), mPaintForText);

    // 创建一个图层，在图层上演示图形混合后的效果
    int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.MATRIX_SAVE_FLAG |
        Canvas.CLIP_SAVE_FLAG |
        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
        Canvas.CLIP_TO_LAYER_SAVE_FLAG);

    mPaintForText.setTextSize(Screen.getInstance(getContext()).sp2px(12));

    // 第一行

    canvas.drawBitmap(mDstBitmap, size / 8 * 7, size / 8 * 7 + size * 2, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeClear);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9, size / 8 * 9 + size * 2, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("CLEAR", size / 2 + size, (int) (size * (4 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 2, size / 8 * 7 + size * 2, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeSrc);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 2, size / 8 * 9 + size * 2, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SRC", size / 2 + size * 3, (int) (size * (4 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 4, size / 8 * 7 + size * 2, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDst);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 4, size / 8 * 9 + size * 2, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DST", size / 2 + size * 5, (int) (size * (4 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 6, size / 8 * 7 + size * 2, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeSrcOver);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 6, size / 8 * 9 + size * 2, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SRC_OVER", size / 2 + size * 7, (int) (size * (4 + 0.3f)), mPaintForText);

    // 第二行

    canvas.drawBitmap(mDstBitmap, size / 8 * 7, size / 8 * 7 + size * 4, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDstOver);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9, size / 8 * 9 + size * 4, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DST_OVER", size / 2 + size, (int) (size * (6 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 2, size / 8 * 7 + size * 4, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeSrcIn);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 2, size / 8 * 9 + size * 4, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SRC_IN", size / 2 + size * 3, (int) (size * (6 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 4, size / 8 * 7 + size * 4, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDstIn);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 4, size / 8 * 9 + size * 4, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DST_IN", size / 2 + size * 5, (int) (size * (6 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 6, size / 8 * 7 + size * 4, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeSrcOut);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 6, size / 8 * 9 + size * 4, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SRC_OUT", size / 2 + size * 7, (int) (size * (6 + 0.3f)), mPaintForText);

    // 第三行

    canvas.drawBitmap(mDstBitmap, size / 8 * 7, size / 8 * 7 + size * 6, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDstOut);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9, size / 8 * 9 + size * 6, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DST_OUT", size / 2 + size, (int) (size * (8 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 2, size / 8 * 7 + size * 6, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeSrcAtop);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 2, size / 8 * 9 + size * 6, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SRC_ATOP", size / 2 + size * 3, (int) (size * (8 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 4, size / 8 * 7 + size * 6, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDstAtop);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 4, size / 8 * 9 + size * 6, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DST_ATOP", size / 2 + size * 5, (int) (size * (8 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 6, size / 8 * 7 + size * 6, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeXOR);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 6, size / 8 * 9 + size * 6, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("XOR", size / 2 + size * 7, (int) (size * (8 + 0.3f)), mPaintForText);

    // 第四行

    canvas.drawBitmap(mDstBitmap, size / 8 * 7, size / 8 * 7 + size * 8, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeDarken);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9, size / 8 * 9 + size * 8, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("DARKEN", size / 2 + size, (int) (size * (10 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 2, size / 8 * 7 + size * 8, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeLighten);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 2, size / 8 * 9 + size * 8, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("LIGHTEN", size / 2 + size * 3, (int) (size * (10 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 4, size / 8 * 7 + size * 8, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeMultiply);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 4, size / 8 * 9 + size * 8, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("MULTIPLY", size / 2 + size * 5, (int) (size * (10 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 6, size / 8 * 7 + size * 8, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeScreen);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 6, size / 8 * 9 + size * 8, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("SCREEN", size / 2 + size * 7, (int) (size * (10 + 0.3f)), mPaintForText);

    // 第五行

    canvas.drawBitmap(mDstBitmap, size / 8 * 7, size / 8 * 7 + size * 10, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeAdd);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9, size / 8 * 9 + size * 10, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("ADD", size / 2 + size, (int) (size * (12 + 0.3f)), mPaintForText);

    canvas.drawBitmap(mDstBitmap, size / 8 * 7 + size * 2, size / 8 * 7 + size * 10, mPaint);
    mPaint.setXfermode(mPorterDuffXfermodeOverlay);
    canvas.drawBitmap(mSrcBitmap, size / 8 * 9 + size * 2, size / 8 * 9 + size * 10, mPaint);
    mPaint.setXfermode(null);
    canvas.drawText("OVERLAY", size / 2 + size * 3, (int) (size * (12 + 0.3f)), mPaintForText);

    // 还原画布
    canvas.restoreToCount(layer);
  }

  /**
   * src
   *
   * @param w 宽
   * @param h 高
   * @return
   */
  private Bitmap genSrc(int w, int h) {
    if (null == mSrcBitmap) {
      mSrcBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
      Canvas c = new Canvas(mSrcBitmap);
      Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
      p.setColor(0xFF66AAFF);
      c.drawRect(w / 4, h / 4, w / 4 * 3, h / 4 * 3, p);
    }
    return mSrcBitmap;
  }

  /**
   * dst
   *
   * @param w 宽
   * @param h 高
   * @return
   */
  private Bitmap genDst(int w, int h) {
    if (null == mDstBitmap) {
      mDstBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
      Canvas c = new Canvas(mDstBitmap);
      Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
      p.setColor(0xFFFFCC44);
      c.drawOval(new RectF(w / 4, h / 4, w / 4 * 3, h / 4 * 3), p);
    }
    return mDstBitmap;
  }
}