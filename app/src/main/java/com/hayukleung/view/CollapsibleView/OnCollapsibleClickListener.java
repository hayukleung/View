package com.hayukleung.view.CollapsibleView;

/**
 * 树点击监听
 *
 * Created by hayukleung on 15/9/1.
 */
public interface OnCollapsibleClickListener {

  /**
   * 人员结点点击
   *
   * @param usr
   * @param position
   */
  void onUsrClick(Element usr, int position);

  /**
   * 组织结点点击
   *
   * @param org
   * @param position
   * @return true: 锁定本次点击，不再执行额外操作 false: 额外执行toggle
   */
  boolean onOrgClick(Element org, int position);
}
