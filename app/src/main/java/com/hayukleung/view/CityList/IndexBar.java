package com.hayukleung.view.CityList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.hayukleung.utils.Screen;
import com.hayukleung.view.BaseView;

/**
 * 快速索引栏
 */
public class IndexBar extends BaseView {

  private Paint mPaint;
  private RectF mRectF;
  private Rect mRect;

  private int mX, mY;

  private String[] mIndex = new String[] {
      "A", "B", "C", "D", "E", "F", "G", //
      "H", "I", "J", "K", "L", "M", "N", //
      "O", "P", "Q", "R", "S", "T", //
      "U", "V", "W", "X", "Y", "Z" //
  };

  private OnIndexTouchListener mOnIndexTouchListener;

  public IndexBar(Context context) {
    super(context);
  }

  public IndexBar(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public IndexBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public void setOnIndexTouchListener(OnIndexTouchListener onIndexTouchListener) {
    mOnIndexTouchListener = onIndexTouchListener;
  }

  public void setIndex(String[] index) {
    mIndex = index;
    postInvalidate();
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mRectF = new RectF();
    mRect = new Rect();
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE: {
        mX = (int) event.getX();
        mY = (int) event.getY();
        // Log.e("y", mY + "");
        if (mY > getWidth() / 2 && mY < (getHeight() - getWidth() / 2)) {

          int count = null == mIndex ? 0 : mIndex.length;
          if (0 == count) {
            break;
          }
          int size = (getHeight() - getWidth()) / count;
          int iIndex = (mY - getWidth() / 2) / size;
          if (null != mOnIndexTouchListener && iIndex < count) {
            mOnIndexTouchListener.onTouch(mIndex[iIndex]);
          }
          invalidate();
        }
        break;
      }
      case MotionEvent.ACTION_UP: {
        mY = 0;
        invalidate();
        break;
      }
      default: {
        break;
      }
    }
    return true;
  }

  @Override protected void onDraw(Canvas canvas) {
    // draw background
    mPaint.setColor(Color.parseColor("#22222222"));
    mRectF.set(0, 0, getWidth(), getHeight());
    canvas.drawRoundRect(mRectF, getWidth() / 2, getWidth() / 2, mPaint);

    int count = null == mIndex ? 0 : mIndex.length;
    if (0 == count) {
      return;
    }
    int size = (getHeight() - getWidth()) / count;

    int iIndex = (mY - getWidth() / 2) / size;

    mPaint.setColor(Color.parseColor("#FF000000"));
    mPaint.setStrokeWidth(3);
    mPaint.setTextAlign(Paint.Align.LEFT);

    for (int i = 0; i < count; i++) {
      String idx = mIndex[i];
      mPaint.setTextSize(i == iIndex ? 60 : 40);
      mPaint.getTextBounds(idx, 0, idx.length(), mRect);
      canvas.drawText(idx, 0, 1, getWidth() / 2 - mRect.width() / 2,
          getWidth() / 2 + i * size + size / 2 + mRect.height() / 2, mPaint);
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // int modeW = MeasureSpec.getMode(widthMeasureSpec);
    // int sizeW = MeasureSpec.getSize(widthMeasureSpec);
    // int modeH = MeasureSpec.getMode(heightMeasureSpec);
    // int sizeH = MeasureSpec.getSize(heightMeasureSpec);

    int modeW = MeasureSpec.getMode(widthMeasureSpec);
    int sizeW = Screen.getInstance(getContext()).dp2px(40);
    setMeasuredDimension(MeasureSpec.makeMeasureSpec(sizeW, modeW), heightMeasureSpec);

    // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public interface OnIndexTouchListener {
    void onTouch(String strIndex);
  }
}
