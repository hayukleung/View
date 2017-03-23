package com.hayukleung.view.UsingViewGroup;

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

public class FlowLine implements Serializable, Comparable<FlowLine> {

  private final List<FlowView> mGroupingTagList = new ArrayList<>();

  public List<FlowView> getGroupingTagList() {
    return mGroupingTagList;
  }

  public void addGroupingTag(FlowView groupingTag) {
    mGroupingTagList.add(groupingTag);
  }

  public void addGroupingTagList(List<FlowView> groupingTagList) {
    mGroupingTagList.addAll(groupingTagList);
  }

  @Override public int compareTo(@NonNull FlowLine o) {
    return this.getLength() - o.getLength();
  }

  public int getLength() {
    int length = 0;
    for (IFlow groupingTag : mGroupingTagList) {
      length += groupingTag.getLength();
    }
    return length;
  }

  @Override public String toString() {
    String line = "";
    for (IFlow groupingTag : mGroupingTagList) {
      // for (int i = 0; i < groupingTag.getLength(); i++) {
      // line += "*";
      // }
      line += groupingTag.getContent();
      line += " ";
    }
    return line;
  }
}
