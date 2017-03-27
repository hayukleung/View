package com.hayukleung.view.CollapsibleView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.view.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CollapsibleAdapter.java
 * <p>
 * Created by hayukleung on 1/8/16.
 */
public class CollapsibleAdapter extends CollapsibleAbstractAdapter<Element, CollapsibleViewHolder> {

  /** 每一级的缩进量 */
  private final int mIndentBase;
  private final List<Element> mTempElements = new ArrayList<>();
  private OnCollapsibleClickListener mOnCollapsibleClickListener;
  private LayoutInflater mLayoutInflater;
  /** resource ids */
  private int mBgResIdOrg;
  private int mBgResIdUsr;
  private int mBgResIdClosed;
  private int mBgResIdOpened;
  /** 元素数据源 */
  private List<Element> mAllElements;
  /** 树中元素 */
  private List<Element> mVisibleElements;

  /**
   * 构造函数
   *
   * @param context
   * @param bgResIdOrg
   * @param bgResIdUsr
   * @param bgResIdClosed
   * @param bgResIdOpened
   * @param allElements
   * @param visibleElements
   * @param onCollapsibleClickListener
   */
  public CollapsibleAdapter(Context context, int bgResIdOrg, int bgResIdUsr, int bgResIdClosed,
      int bgResIdOpened, List<Element> allElements, List<Element> visibleElements,
      OnCollapsibleClickListener onCollapsibleClickListener) {
    this.mLayoutInflater = LayoutInflater.from(context);
    this.mBgResIdOrg = bgResIdOrg;
    this.mBgResIdUsr = bgResIdUsr;
    this.mBgResIdClosed = bgResIdClosed;
    this.mBgResIdOpened = bgResIdOpened;
    this.mAllElements = allElements;
    this.mVisibleElements = visibleElements;
    this.mOnCollapsibleClickListener = onCollapsibleClickListener;
    this.mIndentBase =
        (int) (((float) context.getResources().getDisplayMetrics().widthPixels) / 20);
  }

  public List<Element> getAllElements() {
    return mAllElements;
  }

  public List<Element> getVisibleElements() {
    return mVisibleElements;
  }

  @Override public CollapsibleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return new CollapsibleViewHolder(
        mLayoutInflater.inflate(R.layout.item_collapsible_view, viewGroup, false));
  }

  @Override public void onBindViewHolder(final CollapsibleViewHolder holder, int position) {
    final Element element = mVisibleElements.get(holder.getAdapterPosition());

    holder.txtName.setText(element.getName());

    if (IElement.TYPE_ORG == element.getType()) {
      // 组织类型
      if (element.hasChildren()) {
        // 有孩子结点
        holder.rlItem.setBackgroundResource(mBgResIdOrg);
        if (!element.isExpanded()) {
          // 已闭合
          holder.imgToggle.setImageResource(mBgResIdClosed);
          holder.imgToggle.setVisibility(View.VISIBLE);
        } else {
          // 已展开
          holder.imgToggle.setImageResource(mBgResIdOpened);
          holder.imgToggle.setVisibility(View.VISIBLE);
        }
      } else {
        // 没有孩子结点
        holder.rlItem.setBackgroundResource(mBgResIdOrg);
        holder.imgToggle.setImageResource(mBgResIdOpened);
        holder.imgToggle.setVisibility(View.INVISIBLE);
      }
      holder.rlItem.setOnClickListener(new View.OnClickListener() {

        @Override public void onClick(View view) {
          if (null != mOnCollapsibleClickListener) {
            if (mOnCollapsibleClickListener.onOrgClick(element, holder.getAdapterPosition())) {
              // 锁定本次点击
            } else {
              // 额外执行toggle
              // mTreeViewAdapter.toggle(element, position);

              mTempElements.clear();
              // 下面一步操作会导致两个列表内的元素内容同时发生变更，所以 diffutil 无法判别内容是否发生变更
              // 暂时实现 diffutil callback areContentsTheSame return false; 这样 diffutil 针对每个元素都认为内容发生了变更
              mTempElements.addAll(mVisibleElements);
              toggleRecursively(element, holder.getAdapterPosition());
              DiffUtil.DiffResult diffResult =
                  DiffUtil.calculateDiff(new DiffUtilCallback(mTempElements, mVisibleElements));

              diffResult.dispatchUpdatesTo(CollapsibleAdapter.this);
              // notifyDataSetChanged();
            }
          }
        }
      });
    } else {
      // 人员类型
      holder.rlItem.setBackgroundResource(mBgResIdUsr);
      holder.imgToggle.setImageResource(mBgResIdOpened);
      holder.imgToggle.setVisibility(View.INVISIBLE);
      holder.rlItem.setOnClickListener(new View.OnClickListener() {

        @Override public void onClick(View view) {

          if (null != mOnCollapsibleClickListener) {
            mOnCollapsibleClickListener.onUsrClick(element, holder.getAdapterPosition());
          }
        }
      });
    }

    int level = element.getLevel();
    holder.rlItem.setPadding(mIndentBase * level, holder.rlItem.getPaddingTop(),
        holder.rlItem.getPaddingRight(), holder.rlItem.getPaddingBottom());
  }

  @Override
  public void onBindViewHolder(CollapsibleViewHolder holder, int position, List<Object> payloads) {

    if (null != payloads && 0 < payloads.size()) {
      mVisibleElements.get(holder.getAdapterPosition())
          .setName(((Bundle) payloads.get(0)).getString("name"));
      if (1 < payloads.size()) {
        mVisibleElements.get(holder.getAdapterPosition())
            .setExpanded(((Bundle) payloads.get(1)).getBoolean("expanded"));
      }
    }
    onBindViewHolder(holder, position);
  }

  @Override public long getItemId(int position) {
    return super.getItemId(position);
  }

  @Override public int getItemCount() {
    return null == mVisibleElements ? 0 : mVisibleElements.size();
  }

  @Override public void buildTree() {

    List<Element> temp = new ArrayList<>();
    temp.addAll(mAllElements);
    for (Element element1 : mAllElements) {
      temp.remove(element1);
      for (Element element2 : temp) {
        if (element2.getParentId().equals(element1.getId())) {
          element1.addChild(element2);
        }
      }
      temp.add(element1);
    }
  }

  @Override public void sortTree(List<Element> tops) {

    if (null == tops || tops.size() <= 0) {
      return;
    }

    Collections.sort(tops);
    for (IElement element : tops) {
      element.accessChildrenRecursively(new IElement.TraverseChildrenListener() {

        @Override public void doInTraverseChildren(List<Element> children, int nth) {
          Collections.sort(children);
        }

        @Override public void doInTraverseChild(Element child, int nth) {
        }
      });
    }
  }

  @Override public void toggle(Element element, int position) {

    if (IElement.TYPE_USR == element.getType()) {
      return;
    }

    if (element.isExpanded()) {
      collapse(element, position);
    } else {
      expand(element, position);
      element.accessChildren(new IElement.TraverseChildrenListener() {

        @Override public void doInTraverseChild(Element child, int nth) {
          // 默认闭合
          child.setExpanded(false);
        }

        @Override public void doInTraverseChildren(List<Element> children, int nth) {
        }
      });
    }
  }

  /**
   * 闭合结点
   *
   * @param element
   * @param position
   */
  private void collapse(IElement element, int position) {

    // 闭合操作
    element.setExpanded(false);
    // 删除节点内部对应子节点数据，包括子节点的子节点
    ArrayList<IElement> elementsToDel = new ArrayList<>();
    // 从position+1开始进行遍历
    for (int i = position + 1; i < mVisibleElements.size(); i++) {
      if (element.getLevel() >= mVisibleElements.get(i).getLevel()) break;
      elementsToDel.add(mVisibleElements.get(i));
    }
    mVisibleElements.removeAll(elementsToDel);
  }

  /**
   * 展开下一级孩子
   *
   * @param element
   * @param position
   */
  private void expand(IElement element, final int position) {

    // 展开操作
    element.setExpanded(true);
    element.accessChildren(new IElement.TraverseChildrenListener() {

      @Override public void doInTraverseChild(Element child, int nth) {
        mVisibleElements.add(position + nth, child);
        // LogMgr.showLog("expand " + child.getName() + " at position " + (position + nth));
      }

      @Override public void doInTraverseChildren(List<Element> children, int nth) {
      }
    });
  }

  @Override public void toggleRecursively(Element element, final int position) {

    if (IElement.TYPE_USR == element.getType()) {
      return;
    }

    if (element.isExpanded()) {
      collapse(element, position);
    } else {
      expand(element, position);
      // 这里不应该递归调用，第一层是toggle，接下来应该用refresh
      element.accessChildren(new IElement.TraverseChildrenListener() {

        @Override public void doInTraverseChild(Element child, int nth) {
          // refreshRecursively(child, position + nth);
          refreshRecursively(child, -1);
        }

        @Override public void doInTraverseChildren(List<Element> children, int nth) {
        }
      });
    }
  }

  /**
   * 用于刷新重新展开的结点的孩子的闭合状态
   *
   * @param element
   * @param position
   */
  private void refreshRecursively(IElement element, final int position) {

    if (IElement.TYPE_USR == element.getType()) {
      return;
    }

    if (element.isExpanded()) {

      final int start = mVisibleElements.indexOf(element);
      expand(element, start);
      element.accessChildren(new IElement.TraverseChildrenListener() {

        @Override public void doInTraverseChild(Element child, int nth) {
          // refreshRecursively(child, start + nth);
          refreshRecursively(child, -1);
        }

        @Override public void doInTraverseChildren(List<Element> children, int nth) {
        }
      });
    }
  }
}
