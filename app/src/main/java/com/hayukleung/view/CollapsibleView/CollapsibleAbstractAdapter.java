package com.hayukleung.view.CollapsibleView;

import android.support.v7.widget.RecyclerView;
import java.util.List;

/**
 * CollapsibleAbstractAdapter.java
 * <p>
 * Created by hayukleung on 1/8/16.
 */
public abstract class CollapsibleAbstractAdapter<M, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  /**
   * 建树
   */
  abstract void buildTree();

  /**
   * 排序
   *
   * @param tops
   */
  abstract void sortTree(List<M> tops);

  /**
   * 展开或闭合，展开后孩子结点默认闭合
   *
   * @param element
   * @param position
   */
  abstract void toggle(M element, int position);

  /**
   * 递归地展开或闭合，展开后孩子结点保留之前的开闭状态
   *
   * @param element
   * @param position
   */
  abstract void toggleRecursively(M element, final int position);
}