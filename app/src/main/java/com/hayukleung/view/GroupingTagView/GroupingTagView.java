package com.hayukleung.view.GroupingTagView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.hayukleung.utils.Screen;
import com.hayukleung.view.R;
import com.hayukleung.view.UsingViewGroup.FlowView;
import com.hayukleung.view.UsingViewGroup.IFlow;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTagView.java
 *
 * by hayukleung
 * at 2017-03-23 09:12
 */

public class GroupingTagView extends FlowView {

  private GroupingTag mGroupingTag;
  private Paint mPaint;
  private int mTextSize;
  private RectF mRectF;
  private int mViewWidth;

  public GroupingTagView(Context context) {
    super(context);
  }

  public GroupingTagView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public GroupingTagView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public GroupingTagView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GroupingTagView);
    mGroupingTag = new GroupingTag();
    mGroupingTag.setContent(a.getString(R.styleable.GroupingTagView_GTVContent));
    a.recycle();

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mTextSize = Screen.getInstance(getContext()).sp2px(16);
    mRectF = new RectF();
  }

  @Override protected void onDraw(Canvas canvas) {
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setTextSize(mTextSize);
    mPaint.setTextAlign(Paint.Align.CENTER);
    canvas.drawText(getContent(), getWidth() / 2, getHeight() / 10 * 9, mPaint);

    mPaint.setStyle(Paint.Style.STROKE);
    mRectF.set(0, 0, getWidth(), getHeight());
    canvas.drawRoundRect(mRectF, getHeight() / 2, getHeight() / 2, mPaint);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int hSize = mTextSize + (int) (((float) mTextSize) * 0.2f);
    int hSizeMeasureSpec = MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.EXACTLY);
    int wSizeMeasureSpec = MeasureSpec.makeMeasureSpec(getLength(), MeasureSpec.EXACTLY);
    setMeasuredDimension(wSizeMeasureSpec, hSizeMeasureSpec);
  }

  @Override public int compareTo(@NonNull IFlow o) {
    return getViewWidth() - o.getViewWidth();
  }

  /**
   * 标签内容
   *
   * @param groupingTag
   */
  public void setGroupingTag(GroupingTag groupingTag) {
    this.mGroupingTag = groupingTag;
    invalidate();
  }

  public void setViewWidth(int viewWidth) {
    mViewWidth = viewWidth;
  }

  @Override public int getViewWidth() {
    return mViewWidth;
  }

  @Override public String getContent() {
    return (null == this.mGroupingTag || TextUtils.isEmpty(this.mGroupingTag.getContent())) ? "null"
        : this.mGroupingTag.getContent();
  }

  @Override public int getLength() {
    float textLength = mPaint.measureText(getContent());
    return (int) (textLength / (float) getContent().length() * ((float) getContent().length()
        + 2f));
  }
}
