package com.hayukleung.view.CollapsibleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hayukleung.view.R;

/**
 * CollapsibleViewHolder.java
 * <p>
 * Created by hayukleung on 1/8/16.
 */
public class CollapsibleViewHolder extends RecyclerView.ViewHolder {

  RelativeLayout rlItem;
  ImageView imgToggle;
  TextView txtName;

  public CollapsibleViewHolder(View itemView) {
    super(itemView);
    rlItem = (RelativeLayout) itemView.findViewById(R.id.ItemCollapsible$rl_item);
    imgToggle = (ImageView) itemView.findViewById(R.id.ItemCollapsible$img_toggle);
    txtName = (TextView) itemView.findViewById(R.id.ItemCollapsible$txt_name);
  }
}