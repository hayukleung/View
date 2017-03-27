package com.hayukleung.view.CollapsibleView;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 多级列表结点 </br> 类型： </br> TYPE_ORG 组织 可以拥有孩子结点 </br> TYPE_USR 人员 不可以拥有孩子结点 </br>
 *
 * @author HayukLeung
 */
public class Element implements IElement, Comparable<IElement> {

  public static final Creator<Element> CREATOR = new Creator<Element>() {
    public Element createFromParcel(Parcel source) {
      return new Element(source);
    }

    public Element[] newArray(int size) {
      return new Element[size];
    }
  };
  /**
   * 异常：ID
   */
  private static final String EXCEPTION_ID = "本元素ID不能为空";
  /**
   * 异常：孩子结点数目
   */
  private static final String EXCEPTION_CHILDREN_SIZE = "人员元素孩子结点数目不应该大于0";
  /**
   * 异常：遍历孩子结点监听
   */
  private static final String EXCEPTION_TRAVERSE_CHILDREN = "遍历孩子结点监听未设置";
  /**
   * 文字内容，可以替换为一个Java对象，比如UserInfo
   */
  private String name;
  /**
   * 在tree中的层级
   */
  private int level = LEVEL_ROOT;
  /**
   * 元素的ID
   */
  private String id = "";
  /**
   * 父元素ID
   */
  private String parentId = "";
  /**
   * 孩子元素
   */
  private List<Element> children = new ArrayList<Element>();
  /**
   * 访问孩子结点集计数
   */
  private int accessChildrenNth = 1;
  /**
   * 访问孩子结点计数
   */
  private int accessChildNth = 1;
  /**
   * item是否展开
   */
  private boolean isExpanded = false;
  /**
   * 结点类型，默认人员结点
   */
  private Integer type = TYPE_USR;
  /**
   * 优先级，数值越小，优先级越高
   */
  private Integer priority = 0;

  /**
   * 构造函数
   *
   * @param id
   * @param name
   * @param isOrg
   */
  public Element(String id, String name, boolean isOrg) {
    if (TextUtils.isEmpty(id)) {
      throw new RuntimeException(EXCEPTION_ID);
    }
    this.id = id;
    this.name = name;
    this.type = isOrg ? TYPE_ORG : TYPE_USR;
  }

  protected Element(Parcel in) {
    this.name = in.readString();
    this.level = in.readInt();
    this.id = in.readString();
    this.parentId = in.readString();
    this.children = in.createTypedArrayList(Element.CREATOR);
    this.accessChildrenNth = in.readInt();
    this.accessChildNth = in.readInt();
    this.isExpanded = in.readByte() != 0;
    this.type = (Integer) in.readValue(Integer.class.getClassLoader());
    this.priority = (Integer) in.readValue(Integer.class.getClassLoader());
    // this.EXCEPTION_ID = in.readString();
    // this.EXCEPTION_CHILDREN_SIZE = in.readString();
    // this.EXCEPTION_TRAVERSE_CHILDREN = in.readString();
  }

  @Override public void onElementClick() {
  }

  @Override public String getName() {
    return name;
  }

  @Override public void setName(String name) {
    this.name = name;
  }

  @Override public int getLevel() {
    return level;
  }

  @Override public String getId() {
    return id;
  }

  @Override public void setId(String id) {
    this.id = id;
  }

  @Override public String getParentId() {
    return parentId;
  }

  @Override public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  @Override public void setChildren(List<Element> children) {
    if (TYPE_USR == this.type) {
      return;
    }
    if (TextUtils.isEmpty(this.id)) {
      throw new RuntimeException(EXCEPTION_ID);
    }
    if (null != children) {
      this.children.clear();
      this.children.addAll(children);
    }
    List<Element> tempElements = new ArrayList<Element>();
    // 遍历children
    for (Element child : this.children) {
      // 递归
      child.parentId = this.id;
      child.level = this.level + 1;
      if (child.hasChildren()) {
        tempElements.clear();
        tempElements.addAll(child.children);
        child.children.clear();
        child.setChildren(tempElements);
      }
    }
  }

  @Override public void addChild(Element child) {
    if (TYPE_USR == this.type) {
      // 如果本结点为人员类型结点，不应该拥有孩子结点
      return;
    }
    if (TextUtils.isEmpty(this.id)) {
      throw new RuntimeException(EXCEPTION_ID);
    }
    if (null != child && !children.contains(child)) {
      this.children.add(child);
      List<Element> tempElements = new ArrayList<Element>();
      child.parentId = this.id;
      child.level = this.level + 1;
      if (child.hasChildren()) {
        tempElements.clear();
        tempElements.addAll(child.children);
        child.children.clear();
        child.setChildren(tempElements);
      }
    }
  }

  @Override public void addChildren(List<Element> children) {
    if (TYPE_USR == this.type) {
      return;
    }
    if (null != children) {
      for (Element child : children) {
        addChild(child);
      }
    }
  }

  @Override public List<IElement> getChildrenCopy() {
    List<IElement> children = new ArrayList<IElement>();
    children.addAll(this.children);
    return children;
  }

  @Override public int getChildrenSize() {
    return children.size();
  }

  @Override public boolean hasChildren() {
    if (TYPE_USR == this.type && this.children.size() > 0) {
      throw new RuntimeException(EXCEPTION_CHILDREN_SIZE);
    }
    return children.size() > 0;
  }

  @Override public void accessChildren(TraverseChildrenListener listener) {
    this.accessChildNth = 1;
    this.accessChildrenNth = 1;
    accessChildrenPrivate(listener);
  }

  /**
   * 遍历孩子结点，不包括孩子的孩子
   *
   * @param listener
   */
  private void accessChildrenPrivate(TraverseChildrenListener listener) {
    if (null == listener) {
      throw new RuntimeException(EXCEPTION_TRAVERSE_CHILDREN);
    }
    listener.doInTraverseChildren(this.children, this.accessChildrenNth++);
    for (Element child : this.children) {
      listener.doInTraverseChild(child, this.accessChildNth++);
    }
  }

  @Override public void accessChildrenRecursively(TraverseChildrenListener listener) {
    this.accessChildNth = 1;
    this.accessChildrenNth = 1;
    accessChildrenRecursivelyPrivate(listener);
  }

  @Override public boolean isExpanded() {
    return isExpanded;
  }

  @Override public void setExpanded(boolean isExpanded) {
    this.isExpanded = isExpanded;
  }

  @Override public int getType() {
    return type;
  }

  @Override public Integer getPriority() {
    return priority;
  }

  @Override public void setPriority(int priority) {
    this.priority = priority;
  }

  /**
   * 递归遍历孩子结点，包括孩子的孩子
   *
   * @param listener
   */
  private void accessChildrenRecursivelyPrivate(TraverseChildrenListener listener) {
    if (null == listener) {
      throw new RuntimeException(EXCEPTION_TRAVERSE_CHILDREN);
    }
    listener.doInTraverseChildren(this.children, this.accessChildrenNth++);
    for (Element child : this.children) {
      listener.doInTraverseChild(child, this.accessChildNth++);
      if (child.hasChildren()) {
        child.accessChildrenRecursivelyPrivate(listener);
      }
    }
  }

  @Override public boolean equals(Object o) {
    if (null == o || !(o instanceof IElement)) {
      return false;
    }
    // 由类型及ID唯一确定Element对象
    return this.id.equals(((IElement) o).getId()) && this.type == ((IElement) o).getType();
  }

  @Override public int compareTo(IElement another) {
    int result = 0;
    result = this.type.compareTo(another.getType());
    if (0 == result) {
      result = this.priority.compareTo(another.getPriority());
      if (0 == result) {
        result = this.name.compareTo(another.getName());
      }
    }
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeInt(this.level);
    dest.writeString(this.id);
    dest.writeString(this.parentId);
    dest.writeTypedList(children);
    dest.writeInt(this.accessChildrenNth);
    dest.writeInt(this.accessChildNth);
    dest.writeByte(isExpanded ? (byte) 1 : (byte) 0);
    dest.writeValue(this.type);
    dest.writeValue(this.priority);
    // dest.writeString(this.EXCEPTION_ID);
    // dest.writeString(this.EXCEPTION_CHILDREN_SIZE);
    // dest.writeString(this.EXCEPTION_TRAVERSE_CHILDREN);
  }
}
