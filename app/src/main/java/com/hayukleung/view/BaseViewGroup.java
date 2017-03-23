package com.hayukleung.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * View
 * com.hayukleung.view
 * BaseViewGroup.java
 *
 * by hayukleung
 * at 2017-03-23 11:04
 */

public abstract class BaseViewGroup extends ViewGroup {

  public BaseViewGroup(Context context) {
    this(context, null);
  }

  public BaseViewGroup(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr, 0);
  }

  /**
   * 构造函数里需要处理的都在这里进行
   *
   * @param context
   * @param attrs
   * @param defStyleAttr
   * @param defStyleRes
   */
  protected abstract void init(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes);

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs, defStyleAttr, defStyleRes);
  }
}
