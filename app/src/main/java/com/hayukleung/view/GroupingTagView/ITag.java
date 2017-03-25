package com.hayukleung.view.GroupingTagView;

import java.io.Serializable;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * ITag.java
 *
 * by hayukleung
 * at 2017-03-23 16:49
 */

public interface ITag<T extends ITag> extends Serializable, Comparable<T> {

  String getContent();

  int getLength();

  int getWidthWithMargin();
}
