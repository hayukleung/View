package com.hayukleung.view.GroupingTagView;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * ITagLine.java
 *
 * by hayukleung
 * at 2017-03-22 17:18
 */

public class ITagLine implements Serializable, Comparable<ITagLine> {

  private final List<ITag> mTagList = new ArrayList<>();

  public List<ITag> getTagList() {
    return mTagList;
  }

  public void addTag(ITag tag) {
    mTagList.add(tag);
  }

  public void addGroupingTagList(List<TagView> groupingTagList) {
    mTagList.addAll(groupingTagList);
  }

  @Override public int compareTo(@NonNull ITagLine o) {
    return this.getLength() - o.getLength();
  }

  public int getLength() {
    int length = 0;
    for (ITag groupingTag : mTagList) {
      length += groupingTag.getWidthWithMargin();
    }
    return length;
  }

  @Override public String toString() {
    String line = "";
    for (ITag groupingTag : mTagList) {
      // for (int i = 0; i < groupingTag.getLength(); i++) {
      // line += "*";
      // }
      line += groupingTag.getContent();
      line += " ";
    }
    return line;
  }
}
