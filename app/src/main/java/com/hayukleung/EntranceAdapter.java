package com.hayukleung;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hayukleung.view.R;
import java.util.List;

/**
 * View
 * com.hayukleung
 * EntranceAdapter.java
 *
 * by hayukleung
 * at 2016-12-28 09:53
 */

public class EntranceAdapter extends RecyclerView.Adapter<EntranceAdapter.EntranceHolder> {

  private AppCompatActivity mContext;
  private List<Entrance> mEntranceList;

  public EntranceAdapter(AppCompatActivity context, List<Entrance> entranceList) {
    mContext = context;
    mEntranceList = entranceList;
  }

  @Override public EntranceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new EntranceHolder(
        mContext.getLayoutInflater().inflate(R.layout.item_entrance, parent, false));
  }

  @Override public void onBindViewHolder(EntranceHolder holder, int position) {
    holder.name.setText(mEntranceList.get(position).getName());
    holder.root.setOnClickListener(mEntranceList.get(position).getListener());
  }

  @Override public int getItemCount() {
    return null == mEntranceList ? 0 : mEntranceList.size();
  }

  static class EntranceHolder extends RecyclerView.ViewHolder {

    private View root;
    private TextView name;

    public EntranceHolder(View itemView) {
      super(itemView);
      root = itemView.findViewById(R.id.root);
      name = (TextView) itemView.findViewById(R.id.name);
    }
  }
}