package com.hayukleung.view.UsingViewGroup;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * IFlow.java
 *
 * by hayukleung
 * at 2017-03-23 16:49
 */

public interface IFlow extends Comparable<IFlow> {

  int getViewWidth();

  void setViewWidth(int viewWidth);

  String getContent();

  int getLength();
}
