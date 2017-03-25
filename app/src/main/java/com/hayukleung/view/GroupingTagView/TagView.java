package com.hayukleung.view.GroupingTagView;

import android.content.Context;
import android.util.AttributeSet;
import com.hayukleung.view.BaseView;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * FlowView.java
 *
 * by hayukleung
 * at 2017-03-23 16:55
 */

public abstract class TagView extends BaseView implements ITag {

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
}
