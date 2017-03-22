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

public class GroupingTag implements Serializable, Comparable<GroupingTag> {

  /**
   * 长度
   */
  private int length;

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @Override public int compareTo(@NonNull GroupingTag o) {
    return this.length - o.length;
  }
}
