package com.hayukleung.view.GroupingTagView;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * FlowView.java
 *
 * by hayukleung
 * at 2017-03-23 16:55
 */

public abstract class TagView extends BaseView implements ITag<TagView> {

  public TagView(Context context) {
    super(context);
  }

  public TagView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public TagView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public final int compareTo(@NonNull TagView o) {
    return getWidthWithMargin() - o.getWidthWithMargin();
  }

  @Override public final int getWidthWithMargin() {
    ViewGroup.MarginLayoutParams marginLayoutParams =
        (ViewGroup.MarginLayoutParams) getLayoutParams();
    return getWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
  }
}
