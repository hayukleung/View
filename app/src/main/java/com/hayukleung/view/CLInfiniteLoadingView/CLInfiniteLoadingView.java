package com.hayukleung.view.CLInfiniteLoadingView;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import com.hayukleung.view.BaseView;
import com.hayukleung.view.R;

public class CLInfiniteLoadingView extends BaseView {

  private Paint mPaint;

  private Bitmap mBgBitmap;
  private Bitmap mLightingBitmap;

  private Bitmap mScaledBgBitmap;
  private Bitmap mScaledLightingBitmap;

  // 此控件的宽高
  private int mWidth, mHeight;

  private PorterDuffXfermode mXfermode;

  private int mRotateDegree = 0;
  private ValueAnimator mAnimator;

  private Context mContext;

  public CLInfiniteLoadingView(Context context) {
    super(context);
  }

  public CLInfiniteLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CLInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public CLInfiniteLoadingView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mContext = context;
    mPaint = new Paint();
    mPaint.setColor(Color.argb(255, 224, 30, 60));
    mPaint.setAntiAlias(true);
    mPaint.setDither(true);
    mPaint.setStyle(Paint.Style.FILL);

    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_loading_path, options);
    mLightingBitmap =
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_loading_lightning, options);

    mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    this.mWidth = w;
    this.mHeight = h;
    if (w != oldw || h != oldh) {
      if (null != mScaledLightingBitmap) {
        mScaledLightingBitmap.recycle();
        mScaledLightingBitmap = null;
      }
      if (null != mScaledBgBitmap) {
        mScaledBgBitmap.recycle();
        mScaledBgBitmap = null;
      }
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    drawCircle(canvas);
  }

  private void drawCircle(Canvas canvas) {

    if (null == mScaledBgBitmap || null == mScaledLightingBitmap) {

      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inPreferredConfig = Bitmap.Config.ARGB_8888;

      int wBg = mBgBitmap.getWidth();
      int hBg = mBgBitmap.getHeight();

      float ratioW = (float) wBg / (float) mWidth;
      float ratioH = (float) hBg / (float) mHeight;

      if (ratioW < ratioH) {
        mScaledBgBitmap = Bitmap.createScaledBitmap(mBgBitmap,
            (int) ((float) mBgBitmap.getWidth() / (float) mBgBitmap.getHeight() * (float) mHeight),
            mHeight, true);
        mScaledLightingBitmap = Bitmap.createScaledBitmap(mLightingBitmap,
            (int) ((float) mHeight / (float) mBgBitmap.getHeight()
                * (float) mLightingBitmap.getWidth()),
            (int) ((float) mHeight / (float) mBgBitmap.getHeight()
                * (float) mLightingBitmap.getHeight()), true);
      } else {
        mScaledBgBitmap = Bitmap.createScaledBitmap(mBgBitmap, mWidth,
            (int) ((float) mBgBitmap.getHeight() / (float) mBgBitmap.getWidth() * (float) mWidth),
            true);
        mScaledLightingBitmap = Bitmap.createScaledBitmap(mLightingBitmap,
            (int) ((float) mWidth / (float) mBgBitmap.getWidth()
                * (float) mLightingBitmap.getWidth()),
            (int) ((float) mWidth / (float) mBgBitmap.getWidth()
                * (float) mLightingBitmap.getHeight()), true);
      }
    }

    // 闪电坐标
    float x = (mWidth - mScaledLightingBitmap.getWidth()) / 2f;
    float y = (mHeight - mScaledLightingBitmap.getHeight()) / 2f - dp2px(mContext, 5);
    // 绘制闪电
    canvas.drawBitmap(mScaledLightingBitmap, x, y, mPaint);
    // 存为新图层--在此图层使用setXfermode其中一种策略
    int saveLayerCount = canvas.saveLayer(0, 0, mWidth, mHeight, mPaint, Canvas.ALL_SAVE_FLAG);
    // 水滴坐标
    x = (mWidth - mScaledBgBitmap.getWidth()) / 2f;
    y = (mHeight - mScaledBgBitmap.getHeight()) / 2f;
    // 绘制水滴
    canvas.drawBitmap(mScaledBgBitmap, x, y, null);
    // 准备画红色实心圆与水滴混合
    // 红圆中心点为背景图的左侧中间位置
    float cx = (mWidth - mScaledBgBitmap.getWidth()) / 2f;
    float cy = mHeight / 2;
    // 保留相交部分及底层图片
    mPaint.setXfermode(mXfermode);
    canvas.rotate(mRotateDegree, mWidth / 2, mHeight / 2f);
    float radius = Math.max(mScaledBgBitmap.getWidth(), mScaledBgBitmap.getHeight()) / 3.0f;
    canvas.drawCircle(cx, cy, radius, mPaint);
    // 恢复保存的图层
    canvas.restoreToCount(saveLayerCount);
  }

  public int dp2px(Context context, float dp) {
    return Math.round(dp * context.getResources().getDisplayMetrics().density);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    startAnim();
  }

  public void startAnim() {

    if (mAnimator == null) {
      RotateDegreeEvaluator evaluator = new RotateDegreeEvaluator();
      mAnimator = ValueAnimator.ofObject(evaluator, 0.0f, 0.0f);
      mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator animation) {
          Float point = (Float) animation.getAnimatedValue();
          if (point != null) {
            mRotateDegree = (int) (360 * point);
            invalidate();
          }
        }
      });
      mAnimator.setDuration(1000);
      mAnimator.setInterpolator(new LinearInterpolator());
      mAnimator.setRepeatCount(ValueAnimator.INFINITE);
      mAnimator.setRepeatMode(ValueAnimator.RESTART);
      mAnimator.start();
    }
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    stopAnim();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = measureWidth(widthMeasureSpec);
    int height = measureHeight(heightMeasureSpec);
    setMeasuredDimension(width, height);
  }

  private int measureWidth(int widthMeasureSpec) {
    int mode = MeasureSpec.getMode(widthMeasureSpec);
    int size = MeasureSpec.getSize(widthMeasureSpec);
    int width;
    if (mode == MeasureSpec.EXACTLY) {
      width = size;
    } else {
      width = mBgBitmap.getWidth() + getPaddingRight() + getPaddingLeft();
      if (mode == MeasureSpec.AT_MOST) {
        width = Math.min(size, width);
      }
    }
    int minSize = mBgBitmap.getWidth() + getPaddingRight() + getPaddingLeft();
    if (width < minSize) {
      width = minSize;
    }
    return width;
  }

  private int measureHeight(int heightMeasureSpec) {
    int mode = MeasureSpec.getMode(heightMeasureSpec);
    int size = MeasureSpec.getSize(heightMeasureSpec);
    int height;
    if (mode == MeasureSpec.EXACTLY) {
      height = size;
    } else {
      height = mBgBitmap.getHeight() + getPaddingBottom() + getPaddingTop();
      if (mode == MeasureSpec.AT_MOST) {
        height = Math.min(height, size);
      }
    }
    int minSize = mBgBitmap.getHeight() + getPaddingBottom() + getPaddingTop();
    if (height < minSize) {
      height = minSize;
    }
    return height;
  }

  public void stopAnim() {
    if (mAnimator != null) {
      mAnimator.cancel();
      mAnimator = null;
    }
  }

  private class RotateDegreeEvaluator implements TypeEvaluator<Float> {

    @Override public Float evaluate(float fraction, Float startValue, Float endValue) {
      return fraction;
    }
  }
}
