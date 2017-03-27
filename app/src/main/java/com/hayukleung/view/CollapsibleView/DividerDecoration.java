package com.hayukleung.view.CollapsibleView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * DividerDecoration.java
 * <p>
 * Created by hayukleung on 1/8/16.
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

  public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
  public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
  private static final int[] ATTRS = new int[] {
      android.R.attr.listDivider
  };
  private Drawable mDivider;

  public DividerDecoration(Context context) {
    final TypedArray a = context.obtainStyledAttributes(ATTRS);
    mDivider = a.getDrawable(0);
    a.recycle();
  }

  public DividerDecoration(Drawable drawable) {
    mDivider = drawable;
  }

  private int getOrientation(RecyclerView parent) {
    LinearLayoutManager layoutManager;
    try {
      layoutManager = (LinearLayoutManager) parent.getLayoutManager();
    } catch (ClassCastException e) {
      throw new IllegalStateException(
          "DividerDecoration can only be used with a " + "LinearLayoutManager.", e);
    }
    return layoutManager.getOrientation();
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    if (getOrientation(parent) == VERTICAL_LIST) {
      drawVertical(c, parent);
    } else {
      drawHorizontal(c, parent);
    }
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(c, parent, state);
    onDraw(c, parent, state);
  }

  public void drawVertical(Canvas canvas, RecyclerView parent) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getWidth() - parent.getPaddingRight();

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top = child.getBottom() + params.bottomMargin;
      final int bottom = top + mDivider.getIntrinsicHeight();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(canvas);
      mDivider.setAlpha(0x00);
    }
  }

  public void drawHorizontal(Canvas c, RecyclerView parent) {
    final int top = parent.getPaddingTop();
    final int bottom = parent.getHeight() - parent.getPaddingBottom();

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int left = child.getRight() + params.rightMargin;
      final int right = left + mDivider.getIntrinsicHeight();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(c);
      mDivider.setAlpha(0x00);
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (getOrientation(parent) == VERTICAL_LIST) {
      outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    } else {
      outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
    }
  }
}