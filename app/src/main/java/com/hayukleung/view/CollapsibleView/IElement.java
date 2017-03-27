package com.hayukleung.view.CollapsibleView;

import android.os.Parcelable;
import java.util.List;

/**
 * 结点模型接口
 *
 * @author HayukLeung
 */
public interface IElement extends Parcelable {

  /**
   * 结点类型：人员
   */
  int TYPE_USR = 0x0001;
  /**
   * 结点类型：组织
   */
  int TYPE_ORG = 0x0002;
  /**
   * 表示该元素位于最顶层的层级
   */
  int LEVEL_ROOT = 0;

  /**
   * 结点点击
   */
  void onElementClick();

  /**
   * 获取结点名称
   *
   * @return
   */
  String getName();

  /**
   * 设置结点名称
   *
   * @param name
   */
  void setName(String name);

  /**
   * 获取结点在树中的深度
   *
   * @return
   */
  int getLevel();

  /**
   * 获取结点ID
   *
   * @return
   */
  String getId();

  /**
   * 设置结点ID
   *
   * @param id
   */
  void setId(String id);

  /**
   * 获取结点父结点的ID
   *
   * @return
   */
  String getParentId();

  /**
   * 设置结点父结点的ID
   *
   * @param parentId
   */
  void setParentId(String parentId);

  /**
   * 设置当前结点孩子
   *
   * @param children
   */
  void setChildren(List<Element> children);

  /**
   * 为当前结点增加一个孩子结点
   *
   * @param child
   */
  void addChild(Element child);

  /**
   * 为当前结点增加多个孩子结点
   *
   * @param children
   */
  void addChildren(List<Element> children);

  /**
   * 获取当前结点的孩子副本
   *
   * @return
   */
  List<IElement> getChildrenCopy();

  /**
   * 获取孩子结点数目
   *
   * @return
   */
  int getChildrenSize();

  /**
   * 是否拥有孩子结点
   *
   * @return
   */
  boolean hasChildren();

  /**
   * 遍历孩子结点，不包括孩子的孩子
   *
   * @param listener
   */
  void accessChildren(TraverseChildrenListener listener);

  /**
   * 递归遍历孩子结点，包括孩子的孩子
   *
   * @param listener
   */
  void accessChildrenRecursively(TraverseChildrenListener listener);

  /**
   * 当前结点是否展开孩子结点
   *
   * @return
   */
  boolean isExpanded();

  /**
   * 设置当前结点是否展开孩子结点
   *
   * @param isExpanded
   */
  void setExpanded(boolean isExpanded);

  /**
   * 获取当前结点的类型
   *
   * @return
   */
  int getType();

  /**
   * 获取当前结点的优先级
   *
   * @return
   */
  Integer getPriority();

  /**
   * 设置当前结点的优先级
   *
   * @param priority
   */
  void setPriority(int priority);

  /**
   * 遍历孩子结点监听
   *
   * @author HayukLeung
   */
  interface TraverseChildrenListener {

    /**
     * 对所有孩子结点进行操作，不包括孩子的孩子
     *
     * @param children
     * @param nth 第n次访问
     */
    void doInTraverseChildren(List<Element> children, int nth);

    /**
     * 对单个孩子结点进行操作
     *
     * @param child
     * @param nth 第n次访问
     */
    void doInTraverseChild(Element child, int nth);
  }
}
