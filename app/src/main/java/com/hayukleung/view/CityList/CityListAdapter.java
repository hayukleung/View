package com.hayukleung.view.CityList;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hayukleung.view.R;
import java.util.List;

/**
 * View
 * com.hayukleung.view.CityList
 * CityListAdapter.java
 *
 * by hayukleung
 * at 2017-05-28 14:01
 */

public class CityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_INDEX = 0x0001;
  private static final int TYPE_CITY = 0x0002;

  private List<City> mCityList;

  public CityListAdapter(List<City> cityList) {
    mCityList = cityList;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_CITY: {
        return new HolderCity(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false));
      }
      case TYPE_INDEX: {
        return new HolderIndex(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false));
      }
    }
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case TYPE_CITY: {
        HolderCity holderCity = (HolderCity) holder;
        City city = mCityList.get(position);
        holderCity.name.setText(city.getName());
        holderCity.index.setVisibility(city.isIndex() ? View.VISIBLE : View.GONE);
        holderCity.index.setText(city.getPinyin().substring(0, 1));
        break;
      }
      case TYPE_INDEX: {
        break;
      }
    }
  }

  @Override public int getItemViewType(int position) {
    // TODO
    return TYPE_CITY;
  }

  @Override public int getItemCount() {
    return null == mCityList ? 0 : mCityList.size();
  }

  private static class HolderCity extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView index;

    public HolderCity(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name);
      index = (TextView) itemView.findViewById(R.id.index);
    }
  }

  private static class HolderIndex extends RecyclerView.ViewHolder {

    private TextView index;

    public HolderIndex(View itemView) {
      super(itemView);
      index = (TextView) itemView.findViewById(R.id.index);
    }
  }
}
