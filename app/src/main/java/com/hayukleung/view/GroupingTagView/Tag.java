package com.hayukleung.view.GroupingTagView;

import android.support.annotation.NonNull;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * Tag.java
 *
 * by hayukleung
 * at 2017-03-22 15:44
 */

public class Tag implements ITag<Tag> {

  private String content;

  public Tag() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override public int getLength() {
    return null == content ? 0 : content.length();
  }

  @Override public int getWidthWithMargin() {
    return getLength();
  }

  @Override public final int compareTo(@NonNull Tag o) {
    return this.getWidthWithMargin() - o.getWidthWithMargin();
  }

  @Override public int hashCode() {
    return this.content.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (null == obj) return false;
    if (!(obj instanceof Tag)) return false;
    if (!this.content.equals(((Tag) obj).content)) return false;
    return true;
  }
}
