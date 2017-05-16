package com.hayukleung.view.ShyaringanView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.RotateAnimation;
import com.hayukleung.view.BaseView;

/**
 * 写轮眼
 */
public class ShyaringanView extends BaseView {

  private static final int GOGOK_COUNT = 3;
  private static final int DELTA_ANGLE = 6;

  /** 写轮眼背景 */
  private Paint mPaintBackground;
  /** 写轮眼勾玉 */
  private Paint mPaintGogok;

  private Paint mPaintBitmap;

  private RectF mRectFGogok;
  private Path mPathGogok;
  private int mAngle = -DELTA_ANGLE;

  private Canvas mCanvasBuffer;
  private Bitmap mBitmapBufferBackground;
  private Bitmap mBitmapBufferGogok;
  private Matrix mMatrix;

  private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener =
      new ValueAnimator.AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator animation) {
          setRotation((Float) animation.getAnimatedValue());
        }
      };

  private RotateAnimation mRotateAnimation;

  public ShyaringanView(Context context) {
    super(context);
  }

  public ShyaringanView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ShyaringanView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public ShyaringanView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPathGogok = new Path();
    mMatrix = new Matrix();
  }

  @Override protected void onDraw(Canvas canvas) {

    // drawShyaringan(canvas);

    if (null == mBitmapBufferBackground) {
      bufferBackground();
    }

    if (null == mBitmapBufferGogok) {
      bufferGogok();
    }

    if (null == mPaintBitmap) {
      mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    mMatrix.reset();
    canvas.drawBitmap(mBitmapBufferBackground, mMatrix, mPaintBitmap);

    mMatrix.reset();
    // 旋转
    mMatrix.postRotate(getAngle(), getWidth() / 2, getHeight() / 2);
    canvas.drawBitmap(mBitmapBufferGogok, mMatrix, mPaintBitmap);

    // 1. 视图动画方式旋转
    // if (null == mRotateAnimation) {
    // mRotateAnimation = new RotateAnimation(0, 360, getWidth() / 2, getHeight() / 2);
    // }
    // mRotateAnimation.setDuration(3000);
    // mRotateAnimation.setRepeatMode(Animation.RESTART);
    // mRotateAnimation.setRepeatCount(-1);
    // startAnimation(mRotateAnimation);

    // 2. 属性动画方式旋转
    // ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 720).setDuration(6000);
    // valueAnimator.setRepeatCount(-1);
    // valueAnimator.setRepeatMode(ValueAnimator.RESTART);
    // valueAnimator.addUpdateListener(mAnimatorUpdateListener);
    // valueAnimator.start();

    // 3. 定时重绘方式旋转
    postInvalidateDelayed(15);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
  }

  /**
   * 预先绘制背景
   */
  private void bufferBackground() {
    if (null == mBitmapBufferBackground) {
      mBitmapBufferBackground =
          Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
      mCanvasBuffer = new Canvas(mBitmapBufferBackground);
    }
    drawShyaringanBackground(mCanvasBuffer);
  }

  /**
   * 预先绘制勾玉
   */
  private void bufferGogok() {
    if (null == mBitmapBufferGogok) {
      mBitmapBufferGogok = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
      mCanvasBuffer = new Canvas(mBitmapBufferGogok);
    }
    drawShyaringanGogok(mCanvasBuffer);
  }

  private int getAngle() {
    mAngle += DELTA_ANGLE;
    mAngle %= 360;
    return mAngle;
  }

  /**
   * 绘制写轮眼背景
   *
   * @param canvas
   */
  private void drawShyaringanBackground(Canvas canvas) {

    int cx = getWidth() / 2;
    int cy = getHeight() / 2;
    int radius = getWidth() / 2 / 10 * 9;
    int strokeWidthUnit = getWidth() / 60;

    if (null == mPaintBackground) {
      mPaintBackground = new Paint();
      mPaintBackground.setAntiAlias(true);
    }
    // 绘制背景颜色
    {
      mPaintBackground.setStyle(Paint.Style.FILL);
      mPaintBackground.setColor(Color.argb(255, 200, 59, 68));
      canvas.drawCircle(cx, cy, radius, mPaintBackground);
    }
    // 绘制黑环
    {
      mPaintBackground.setStyle(Paint.Style.STROKE);
      mPaintBackground.setColor(Color.argb(255, 0, 0, 0));
      mPaintBackground.setStrokeWidth(strokeWidthUnit * 2);
      canvas.drawCircle(cx, cy, radius, mPaintBackground);
      mPaintBackground.setStrokeWidth(strokeWidthUnit);
      canvas.drawCircle(cx, cy, radius / 5 * 3, mPaintBackground);
    }
    // 绘制眼珠
    {
      mPaintBackground.setStyle(Paint.Style.FILL);
      mPaintBackground.setColor(Color.argb(255, 0, 0, 0));
      canvas.drawCircle(cx, cy, radius / 5, mPaintBackground);
    }
    // canvas.rotate(getAngle(), cx, cy);
  }

  /**
   * 绘制写轮眼勾玉
   *
   * @param canvas
   */
  private void drawShyaringanGogok(Canvas canvas) {

    int cx = getWidth() / 2;
    int cy = getHeight() / 2;
    int radius = getWidth() / 2 / 10 * 9;
    int strokeWidthUnit = getWidth() / 60;

    int count = GOGOK_COUNT;
    while (count-- > 0) {

      if (null == mPaintGogok) {
        mPaintGogok = new Paint();
        mPaintGogok.setAntiAlias(true);
        mPaintGogok.setStyle(Paint.Style.FILL);
        mPaintGogok.setColor(Color.argb(255, 0, 0, 0));
        mPaintGogok.setStrokeWidth(strokeWidthUnit);
      }
      float rGogokUnit = (float) getWidth() / 1000f;
      if (null == mRectFGogok) {
        mRectFGogok = new RectF();
      }
      mPathGogok.reset();
      mRectFGogok.set(
          // l
          cx - rGogokUnit * 50,
          // t
          cy - radius / 5 * 3 - rGogokUnit * 50,
          // r
          cx + rGogokUnit * 50,
          // b
          cy - radius / 5 * 3 + rGogokUnit * 50);
      mPathGogok.arcTo(mRectFGogok, 90, 180);
      mRectFGogok.set(
          // l
          cx - rGogokUnit * 15,
          // t
          cy - radius / 5 * 3 - rGogokUnit * 50,
          // r
          cx + rGogokUnit * 15,
          // b
          cy - radius / 5 * 3 - rGogokUnit * 20);
      mPathGogok.arcTo(mRectFGogok, 270, -180);
      mRectFGogok.set(
          // l
          cx - rGogokUnit * 35,
          // t
          cy - radius / 5 * 3 - rGogokUnit * 20,
          // r
          cx + rGogokUnit * 35,
          // b
          cy - radius / 5 * 3 + rGogokUnit * 50);
      mPathGogok.arcTo(mRectFGogok, 270, 180);
      mPathGogok.close();

      canvas.save();
      canvas.rotate(360 / GOGOK_COUNT * count, cx, cy);
      canvas.rotate(30, cx, cy - radius / 5 * 3);
      canvas.drawPath(mPathGogok, mPaintGogok);
      canvas.restore();
    }
  }

  /**
   * 资源释放
   */
  public void recycle() {
    mCanvasBuffer = null;

    if (null != mBitmapBufferBackground && !mBitmapBufferBackground.isRecycled()) {
      mBitmapBufferBackground.recycle();
      mBitmapBufferBackground = null;
    }

    if (null != mBitmapBufferGogok && !mBitmapBufferGogok.isRecycled()) {
      mBitmapBufferGogok.recycle();
      mBitmapBufferGogok = null;
    }
  }

  /**
   * 绘制写轮眼
   *
   * @param canvas
   */
  private void drawShyaringan(Canvas canvas) {

    drawShyaringanBackground(canvas);
    drawShyaringanGogok(canvas);
  }
}
