package com.hayukleung.view.GroupingTagView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.hayukleung.utils.Screen;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTagView.java
 *
 * by hayukleung
 * at 2017-03-23 09:12
 */

public class GroupingTagView extends TagView {

  private Tag mTag;
  private Paint mPaint;
  private int mTextSize;
  private int mStrokeWidth;
  private RectF mRectF;

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
    mTag = new Tag();
    mTag.setContent(a.getString(R.styleable.GroupingTagView_GTVContent));
    a.recycle();

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mTextSize = Screen.getInstance(getContext()).sp2px(14);
    // 字体大小必须在初始化时就确定好，不然后面步骤计算标签宽度时会得出不正确的大小
    mPaint.setTextSize(mTextSize);
    mStrokeWidth = Screen.getInstance(getContext()).dp2px(1);
    mRectF = new RectF();
  }

  @Override protected void onDraw(Canvas canvas) {

    mPaint.setColor(getResources().getColor(R.color.colorAccent));

    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setTextAlign(Paint.Align.CENTER);
    canvas.drawText(getContent(), ((float) getWidth()) / 2f, ((float) getHeight()) / 10f * 7f,
        mPaint);

    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(mStrokeWidth);
    mRectF.set(mStrokeWidth, mStrokeWidth, getWidth() - mStrokeWidth, getHeight() - mStrokeWidth);
    canvas.drawRoundRect(mRectF, getHeight() / 2, getHeight() / 2, mPaint);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int hSize = (int) (((float) mTextSize) * 1.8f);
    int hSizeMeasureSpec = MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.EXACTLY);
    int wSize = Math.max(getLength(), hSize);
    int wSizeMeasureSpec = MeasureSpec.makeMeasureSpec(wSize, MeasureSpec.EXACTLY);
    super.onMeasure(wSizeMeasureSpec, hSizeMeasureSpec);
  }

  @Override public String getContent() {
    return (null == this.mTag || TextUtils.isEmpty(this.mTag.getContent())) ? ""
        : this.mTag.getContent();
  }

  @Override public int getLength() {
    String content = getContent();
    float textLength = mPaint.measureText(content);
    Log.e(GroupingTagView.class.getSimpleName(),
        "measureText --> " + hashCode() + " - " + textLength);
    int after = (int) (textLength * 1.5f);
    if (after - textLength > 40 || after - textLength < 20) {
      after = (int) (textLength + 40f);
    }
    return after;
  }

  /**
   * 标签内容
   *
   * @param tag
   */
  public void setTag(Tag tag) {
    this.mTag.setContent(tag.getContent());
    invalidate();
  }
}
