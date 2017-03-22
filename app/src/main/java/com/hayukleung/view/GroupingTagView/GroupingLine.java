package com.hayukleung.view.GroupingTagView;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingLine.java
 *
 * by hayukleung
 * at 2017-03-22 17:18
 */

public class GroupingLine implements Serializable, Comparable<GroupingLine> {

  private final List<GroupingTag> mGroupingTagList = new ArrayList<>();

  public List<GroupingTag> getGroupingTagList() {
    return mGroupingTagList;
  }

  public void addGroupingTag(GroupingTag groupingTag) {
    mGroupingTagList.add(groupingTag);
  }

  public void addGroupingTagList(List<GroupingTag> groupingTagList) {
    mGroupingTagList.addAll(groupingTagList);
  }

  @Override public int compareTo(@NonNull GroupingLine o) {
    return this.getLength() - o.getLength();
  }

  public int getLength() {
    int length = 0;
    for (GroupingTag groupingTag : mGroupingTagList) {
      length += groupingTag.getLength();
    }
    return length;
  }

  @Override public String toString() {
    String line = "";
    for (GroupingTag groupingTag : mGroupingTagList) {
      for (int i = 0; i < groupingTag.getLength(); i++) {
        line += "*";
      }
      line += " ";
    }
    return line;
  }
}
