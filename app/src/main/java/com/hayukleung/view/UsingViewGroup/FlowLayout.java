package com.hayukleung.view.UsingViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hayukleung.view.BaseViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * FlowLayout.java
 *
 * by hayukleung
 * at 2017-03-23 15:44
 */

/**
 * 流式布局
 */
public class FlowLayout extends BaseViewGroup {

  /**
   * 存储所有的View，按行记录
   */
  private List<List<FlowView>> mAllViews = new ArrayList<>();
  /**
   * 记录每一行的最大高度
   */
  private List<Integer> mLineHeight = new ArrayList<>();

  public FlowLayout(Context context) {
    super(context);
  }

  public FlowLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    // 获得它的父容器为它设置的测量模式和大小
    int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
    int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
    int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
    int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

    // 如果是wrap_content情况下，记录宽和高
    int width = 0;
    int height = 0;
    // 记录每一行的宽度，width不断取最大宽度
    int lineWidth = 0;
    // 每一行的高度，累加至height
    int lineHeight = 0;

    int cCount = getChildCount();

    // 遍历每个子元素
    for (int i = 0; i < cCount; i++) {
      View child = getChildAt(i);
      // 测量每一个child的宽和高
      measureChild(child, widthMeasureSpec, heightMeasureSpec);
      // 得到child的lp
      MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
      // 当前子空间实际占据的宽度
      int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
      // 当前子空间实际占据的高度
      int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

      // 如果加入当前child，则超出最大宽度，则得到目前最大宽度给width，累加height 然后开启新行
      if (lineWidth + childWidth > sizeWidth) {
        width = Math.max(lineWidth, childWidth); // 取最大的
        lineWidth = childWidth; // 重新开启新行，开始记录
        // 叠加当前高度
        height += lineHeight;
        // 开启记录下一行的高度
        lineHeight = childHeight;
      } else
      // 否则累加值lineWidth lineHeight取最大高度
      {
        lineWidth += childWidth;
        lineHeight = Math.max(lineHeight, childHeight);
      }
      // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
      if (i == cCount - 1) {
        width = Math.max(width, lineWidth);
        height += lineHeight;
      }
    }
    setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
        (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    mAllViews.clear();
    mLineHeight.clear();

    int width = getWidth();

    int lineWidth = 0;
    int lineHeight = 0;
    // 存储每一行所有的childView
    List<FlowView> lineViews = new ArrayList<>();
    int cCount = getChildCount();
    // 遍历所有的孩子
    for (int i = 0; i < cCount; i++) {
      FlowView child = (FlowView) getChildAt(i);
      MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
      int childWidth = child.getMeasuredWidth();
      int childHeight = child.getMeasuredHeight();

      // 如果已经需要换行
      if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width) {
        // 记录这一行所有的View以及最大高度
        mLineHeight.add(lineHeight);
        // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
        mAllViews.add(lineViews);
        lineWidth = 0;// 重置行宽
        lineViews = new ArrayList<>();
      }
      // 如果不需要换行，则累加
      lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
      lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
      lineViews.add(child);
    }
    // 记录最后一行
    mLineHeight.add(lineHeight);
    mAllViews.add(lineViews);

    int left = 0;
    int top = 0;
    // 得到总行数
    int lineNums = mAllViews.size();
    for (int i = 0; i < lineNums; i++) {
      // 每一行的所有的views
      lineViews = mAllViews.get(i);
      // 当前行的最大高度
      lineHeight = mLineHeight.get(i);

      // 遍历当前行所有的View
      for (int j = 0; j < lineViews.size(); j++) {
        View child = lineViews.get(j);
        if (child.getVisibility() == View.GONE) {
          continue;
        }
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        // 计算childView的left top right bottom
        int lc = left + lp.leftMargin;
        int tc = top + lp.topMargin;
        int rc = lc + child.getMeasuredWidth();
        int bc = tc + child.getMeasuredHeight();

        child.layout(lc, tc, rc, bc);

        left += child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
      }
      left = 0;
      top += lineHeight;
    }
  }

  //  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
  //    mAllViews.clear();
  //    mLineHeight.clear();
  //
  //    int width = getWidth();
  //
  //    // 分组
  //    int lineWidth = 0;
  //    int lineHeight = 0;
  //    // 存储每一行所有的childView
  //    List<FlowView> lineViews = new ArrayList<>();
  //    int cCount = getChildCount();
  //
  //    // 植入算法
  //    List<FlowView> flowViewList = new ArrayList<>(cCount);
  //    for (int i = 0; i < cCount; i++) {
  //      flowViewList.add((FlowView) getChildAt(i));
  //    }
  //
  //    // 按长度从小到大排序
  //    Collections.sort(flowViewList);
  //
  //    int totalLength = 0;
  //    for (int i = 0; i < cCount; i++) {
  //      FlowView child = (FlowView) getChildAt(i);
  //      MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
  //      int childWidth = child.getMeasuredWidth();
  //      int childHeight = child.getMeasuredHeight();
  //
  //      int viewWidth = childWidth + lp.leftMargin + lp.rightMargin;
  //      child.setViewWidth(viewWidth);
  //      totalLength += viewWidth;
  //    }
  //    // 预计的最小行数
  //    int lineCount = (int) Math.ceil((float) totalLength / (float) width);
  //    // 顺序 - 从小到大
  //    // 逆序 - 从大到小
  //    List<FlowLine> group = new ArrayList<>(lineCount);
  //    for (int i = 0; i < lineCount; i++) {
  //      group.add(new FlowLine());
  //    }
  //
  //    // 不等数分组 - 根据所在行剩余容量顺序逆序交替分组
  //    Collections.reverse(flowViewList);
  //    int flag = 0;
  //    do {
  //      int size = flowViewList.size();
  //      if (0 < size) {
  //        group.get(0).addGroupingTag(flowViewList.remove((0 == flag % 2 ? 0 : size - 1)));
  //        Collections.sort(group);
  //      }
  //      flag++;
  //    } while (0 < flowViewList.size());
  //
  //    for (FlowLine line : group) {
  //      lineHeight = 0;
  //      List<FlowView> fvs = line.getGroupingTagList();
  //      for (FlowView fv : fvs) {
  //        MarginLayoutParams lp = (MarginLayoutParams) fv.getLayoutParams();
  //        lineHeight = Math.max(lineHeight, fv.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
  //      }
  //      mAllViews.add(fvs);
  //      mLineHeight.add(lineHeight);
  //    }
  //
  //    if (false) {
  //      List<FlowView> temp = new ArrayList<>();
  //      for (FlowLine line : group) {
  //        temp.addAll(line.getGroupingTagList());
  //      }
  //
  //      // 分组
  //      for (FlowView child : temp) {
  //        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
  //        int childWidth = child.getMeasuredWidth();
  //        int childHeight = child.getMeasuredHeight();
  //
  //        // 如果已经需要换行
  //        if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width) {
  //          // 记录这一行所有的View以及最大高度
  //          mLineHeight.add(lineHeight);
  //          // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
  //          mAllViews.add(lineViews);
  //          lineWidth = 0; // 重置行宽
  //          lineViews = new ArrayList<>();
  //        }
  //        // 如果不需要换行，则累加
  //        lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
  //        lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
  //        lineViews.add(child);
  //      }
  //      // 记录最后一行
  //      mLineHeight.add(lineHeight);
  //      mAllViews.add(lineViews);
  //    }
  //
  //    // 1. 布局
  //    int left = 0;
  //    int top = 0;
  //    // 得到总行数
  //    int lineNums = mAllViews.size();
  //    for (int i = 0; i < lineNums; i++) {
  //      // 每一行的所有的views
  //      lineViews = mAllViews.get(i);
  //      // 当前行的最大高度
  //      lineHeight = mLineHeight.get(i);
  //
  //      // 遍历当前行所有的View
  //      for (int j = 0; j < lineViews.size(); j++) {
  //        View child = lineViews.get(j);
  //        if (child.getVisibility() == View.GONE) {
  //          continue;
  //        }
  //        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
  //
  //        // 计算childView的left top right bottom
  //        int lc = left + lp.leftMargin;
  //        int tc = top + lp.topMargin;
  //        int rc = lc + child.getMeasuredWidth();
  //        int bc = tc + child.getMeasuredHeight();
  //
  //        child.layout(lc, tc, rc, bc);
  //
  //        left += child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
  //      }
  //      left = 0;
  //      top += lineHeight;
  //    }
  //  }

  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new MarginLayoutParams(getContext(), attrs);
  }
}
