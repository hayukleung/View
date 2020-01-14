package com.hayukleung.view.GroupingTagView;

import androidx.recyclerview.widget.RecyclerView;
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

  private List<Tag> mTagList;

  GroupingTagViewAdapter(List<Tag> tagList) {
    mTagList = tagList;
  }

  @Override public GTVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GTVHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_grouping_tag_view, parent, false));
  }

  @Override public void onBindViewHolder(GTVHolder holder, int position) {
    holder.gtv.setTag(mTagList.get(position));
  }

  @Override public int getItemCount() {
    return null == mTagList ? 0 : mTagList.size();
  }

  static class GTVHolder extends RecyclerView.ViewHolder {

    private GroupingTagView gtv;

    GTVHolder(View itemView) {
      super(itemView);
      gtv = (GroupingTagView) itemView.findViewById(R.id.grouping_tag_view);
    }
  }
}
