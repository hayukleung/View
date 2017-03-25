package com.hayukleung.view.UsingViewGroup;

import com.hayukleung.view.GroupingTagView.GroupingTest;
import com.hayukleung.view.GroupingTagView.ITag;
import com.hayukleung.view.GroupingTagView.ITagLine;
import com.hayukleung.view.GroupingTagView.TagView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * AutoSizingUtil.java
 *
 * by hayukleung
 * at 2017-03-25 19:06
 */

public class AutoSizingUtil {

  /**
   * 分组 - 实验中
   *
   * @param autoSizingFlowLayout
   * @see GroupingTest#main(String[])
   */
  public static void group(AutoSizingFlowLayout autoSizingFlowLayout) {

    int cCount = autoSizingFlowLayout.getChildCount();
    List<ITag> mChildList = new ArrayList<>(cCount);
    List<ITagLine> mITagLine = new ArrayList<>();

    mChildList.clear();
    for (int i = 0; i < cCount; i++) {
      TagView child = (TagView) autoSizingFlowLayout.getChildAt(i);
      mChildList.add(child);
    }
    // 按长度从小到大排序
    Collections.sort(mChildList);
    // 计算所有 child 的总宽度
    int totalLength = 0;
    for (ITag child : mChildList) {
      totalLength += child.getWidthWithMargin();
    }
    // 计算行数，向上取整
    final int lineCount = (int) Math.ceil(
        (double) totalLength / (double) (autoSizingFlowLayout.getWidth()
            - autoSizingFlowLayout.getPaddingLeft()
            - autoSizingFlowLayout.getPaddingRight()));
    for (int i = 0; i < lineCount; i++) {
      mITagLine.add(new ITagLine());
    }

    // 不等数分组 - 根据所在行剩余容量顺序逆序交替分组
    Collections.reverse(mChildList);
    int flag = 0;
    do {
      int size = mChildList.size();
      if (0 < size) {
        mITagLine.get(0).addTag(mChildList.remove((0 == flag % 2 ? 0 : size - 1)));
        Collections.sort(mITagLine);
      }
      flag++;
    } while (0 < mChildList.size());

    for (ITagLine ITagLine : mITagLine) {
      mChildList.addAll(ITagLine.getTagList());
    }

    autoSizingFlowLayout.removeAllViews();

    for (ITag child : mChildList) {
      autoSizingFlowLayout.addView((TagView) child);
    }
  }
}
