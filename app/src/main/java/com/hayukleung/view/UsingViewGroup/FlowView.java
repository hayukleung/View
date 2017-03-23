package com.hayukleung.view.UsingViewGroup;

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

public abstract class FlowView extends BaseView implements IFlow {

  public FlowView(Context context) {
    super(context);
  }

  public FlowView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public FlowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }
}
