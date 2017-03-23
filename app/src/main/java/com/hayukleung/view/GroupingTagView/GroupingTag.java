package com.hayukleung.view.GroupingTagView;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTag.java
 *
 * by hayukleung
 * at 2017-03-22 15:44
 */

class GroupingTag implements Serializable, Comparable<GroupingTag> {

  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override public int compareTo(@NonNull GroupingTag o) {
    return this.getLength() - o.getLength();
  }

  int getLength() {
    return null == content ? 0 : content.length();
  }
}
