package com.hayukleung.view.PatternView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.hayukleung.view.BaseView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * View
 * com.hayukleung.view.PatternView
 * PatternView.java
 *
 * by hayukleung
 * at 2017-02-03 14:21
 */

/**
 * 手势图案 DEMO
 *
 * 0 1 2
 * 3 4 5
 * 6 7 8
 */
public class PatternView extends BaseView {

  private static final int COMPARABLE_TYPE_IDX = 0;
  private static final int COMPARABLE_TYPE_SEQ = 1;
  private static int sComparableType = COMPARABLE_TYPE_IDX;
  private Paint mPaint;
  private Path mPath;
  private List<Pattern> mPatterns;

  public PatternView(Context context) {
    super(context);
  }

  public PatternView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public PatternView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public PatternView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPath = new Path();
    mPatterns = new ArrayList<>(9);
    mPatterns.add(new Pattern(0));
    mPatterns.add(new Pattern(1));
    mPatterns.add(new Pattern(2));
    mPatterns.add(new Pattern(3));
    mPatterns.add(new Pattern(4));
    mPatterns.add(new Pattern(5));
    mPatterns.add(new Pattern(6));
    mPatterns.add(new Pattern(7));
    mPatterns.add(new Pattern(8));
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        clear();
        int index = index(event.getX(), event.getY());
        if (index >= 0 && index <= 8) {
          mPatterns.get(index).status = true;
          mPatterns.get(index).seq = 0;
        }
        invalidate();
        return true;
      }
      case MotionEvent.ACTION_MOVE: {
        int index = indexAccurately(event.getX(), event.getY());
        if (index >= 0 && index <= 8) {
          if (mPatterns.get(index).status) {
            return false;
          }
          mPatterns.get(index).status = true;
          mPatterns.get(index).seq = getSelectedCount() - 1;
        }
        invalidate();
        return true;
      }
      case MotionEvent.ACTION_UP: {
        break;
      }
    }
    return false;
  }

  @Override protected void onDraw(Canvas canvas) {
    int radius = getWidth() / 80;

    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.argb(255, 0, 0, 0));
    int index = 9;
    while (--index >= 0) {
      canvas.drawCircle(getXByIndex(index), getYByIndex(index), radius, mPaint);
    }

    mPaint.setStyle(Paint.Style.STROKE);
    index = 9;
    while (--index >= 0) {
      if (mPatterns.get(index).status) {
        canvas.drawCircle(getXByIndex(index), getYByIndex(index), radius * 6, mPaint);
      }
    }

    // 绘制手势路径
    mPaint.setStyle(Paint.Style.STROKE);
    sortBySeq();
    Pattern pattern0 = mPatterns.get(0);
    if (9 == pattern0.seq) {
      return;
    }
    mPath.reset();
    mPath.moveTo(getXByIndex(pattern0.index), getYByIndex(pattern0.index));
    index = -1;
    for (Pattern pattern : mPatterns) {
      index++;
      if (0 == index) {
        // do nothing
      } else {
        if (9 != pattern.seq) {
          mPath.lineTo(getXByIndex(pattern.index), getYByIndex(pattern.index));
        }
      }
    }
    canvas.drawPath(mPath, mPaint);
    sortByIdx();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);
    int size = wSize < hSize ? wSize : hSize;
    int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
  }

  private void sortBySeq() {
    sort(COMPARABLE_TYPE_SEQ);
  }

  private void sortByIdx() {
    sort(COMPARABLE_TYPE_IDX);
  }

  private void sort(int type) {
    sComparableType = type;
    Collections.sort(mPatterns);
  }

  /**
   * 清除手势图案
   */
  public void clear() {
    int i = 9;
    while (--i >= 0) {
      mPatterns.get(i).status = false;
      mPatterns.get(i).seq = 9;
    }
    invalidate();
  }

  /**
   * @param x x
   * @param y y
   * @return 0 1 2 3 4 5 6 7 8
   */
  private int index(float x, float y) {
    int size = getWidth() / 6;
    int xIndex = (int) x / (size * 2);
    int yIndex = (int) y / (size * 2);
    int index = xIndex + yIndex * 3;
    Log.e(PatternView.class.getSimpleName(), index + " --> (" + x + ", " + y + ")");
    return index;
  }

  /**
   * @param x x
   * @param y y
   * @return 0 1 2 3 4 5 6 7 8
   */
  private int indexAccurately(float x, float y) {
    double radius = getWidth() / 12;
    double distance;
    int index = index(x, y);
    switch (index) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8: {
        distance = calculateDistance(x, y, getXByIndex(index), getYByIndex(index));
        break;
      }
      default: {
        distance = radius;
        break;
      }
    }
    return distance < radius ? index : -1;
  }

  private int getSelectedCount() {
    int count = 0;
    for (Pattern pattern : mPatterns) {
      if (pattern.status) {
        count++;
      }
    }
    return count;
  }

  /**
   * 计算(xs, ys)与(xd, yd)的距离
   *
   * @param xs
   * @param ys
   * @param xd
   * @param yd
   * @return
   */
  private double calculateDistance(float xs, float ys, float xd, float yd) {
    return Math.sqrt(Math.pow(Math.abs(xs - xd), 2) + Math.pow(Math.abs(ys - yd), 2));
  }

  private float getXByIndex(int index) {
    int size = getWidth() / 6;
    switch (index) {
      case 0:
      case 3:
      case 6: {
        return size;
      }
      case 1:
      case 4:
      case 7: {
        return size * 3;
      }
      case 2:
      case 5:
      case 8: {
        return size * 5;
      }
      default: {
        return -1;
      }
    }
  }

  private float getYByIndex(int index) {
    int size = getWidth() / 6;
    switch (index) {
      case 0:
      case 1:
      case 2: {
        return size;
      }
      case 3:
      case 4:
      case 5: {
        return size * 3;
      }
      case 6:
      case 7:
      case 8: {
        return size * 5;
      }
      default: {
        return -1;
      }
    }
  }

  private static class Pattern implements Comparable<Pattern> {
    private int index;
    private boolean status = false;
    /**
     * 0 1 2 3 4 5 6 7 8
     */
    private int seq = 9;

    public Pattern(int index) {
      this.index = index;
    }

    @Override public int hashCode() {
      return index;
    }

    @Override public boolean equals(Object obj) {
      if (null == obj || !(obj instanceof Pattern)) {
        return false;
      }
      return index == ((Pattern) obj).index;
    }

    @Override public int compareTo(Pattern o) {
      return COMPARABLE_TYPE_SEQ == sComparableType ? seq - o.seq : index - o.index;
    }
  }
}
