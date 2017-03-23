package com.hayukleung.view.GroupingTagView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.view.R;
import java.util.List;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTagViewAdapter.java
 *
 * by hayukleung
 * at 2017-03-23 10:13
 */

class GroupingTagViewAdapter extends RecyclerView.Adapter<GroupingTagViewAdapter.GTVHolder> {

  private List<GroupingTag> mGroupingTagList;

  GroupingTagViewAdapter(List<GroupingTag> groupingTagList) {
    mGroupingTagList = groupingTagList;
  }

  @Override public GTVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GTVHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_grouping_tag_view, parent, false));
  }

  @Override public void onBindViewHolder(GTVHolder holder, int position) {
    holder.gtv.setGroupingTag(mGroupingTagList.get(position));
  }

  @Override public int getItemCount() {
    return null == mGroupingTagList ? 0 : mGroupingTagList.size();
  }

  static class GTVHolder extends RecyclerView.ViewHolder {

    private GroupingTagView gtv;

    GTVHolder(View itemView) {
      super(itemView);
      gtv = (GroupingTagView) itemView.findViewById(R.id.grouping_tag_view);
    }
  }
}
