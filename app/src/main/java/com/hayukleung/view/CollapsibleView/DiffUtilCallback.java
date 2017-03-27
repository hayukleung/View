package com.hayukleung.view.CollapsibleView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.diffutil
 * DiffUtilCallback.java
 *
 * by hayukleung
 * at 2017-03-20 10:08
 */

public class DiffUtilCallback extends DiffUtil.Callback {

  private List<Element> oldDataList;
  private List<Element> newDataList;

  public DiffUtilCallback(List<Element> oldDataList, List<Element> newDataList) {
    this.oldDataList = oldDataList;
    this.newDataList = newDataList;
  }

  @Override public int getOldListSize() {
    return null == oldDataList ? 0 : oldDataList.size();
  }

  @Override public int getNewListSize() {
    return null == newDataList ? 0 : newDataList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldDataList.get(oldItemPosition).equals(newDataList.get(newItemPosition));
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return false;
    // oldDataList.get(oldItemPosition).getName().equals(newDataList.get(newItemPosition).getName())
    // && oldDataList.get(oldItemPosition).isExpanded() == newDataList.get(newItemPosition).isExpanded();
  }

  @Nullable @Override public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    Bundle bundle = null;
    if (areItemsTheSame(oldItemPosition, newItemPosition) && !areContentsTheSame(oldItemPosition,
        newItemPosition)) {
      bundle = new Bundle();
      bundle.putString("name", newDataList.get(newItemPosition).getName());
      bundle.putBoolean("expanded", newDataList.get(newItemPosition).isExpanded());
    }
    return bundle;
  }
}
