package com.hayukleung.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hayukleung.view.HandleView.HandleViewActivity;
import com.hayukleung.view.RadarInfiniteLoadingView.RadarInfiniteLoadingViewActivity;
import com.hayukleung.view.SonarInfiniteLoadingView.SonarInfiniteLoadingViewActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view
 * ViewMainActivity.java
 *
 * by hayukleung
 * at 2016-12-26 16:54
 */

public class ViewMainActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private List<ViewModel> mViewModelList = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_main);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
      }
    });
    mRecyclerView.setAdapter(new ViewEntranceAdapter(this, mViewModelList));
    mViewModelList.add(new ViewModel("HandleView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, HandleViewActivity.class));
      }
    }));
    mViewModelList.add(new ViewModel("RadarInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, RadarInfiniteLoadingViewActivity.class));
      }
    }));
    mViewModelList.add(new ViewModel("SonarInfiniteLoadingView", new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ViewMainActivity.this, SonarInfiniteLoadingViewActivity.class));
      }
    }));
    mRecyclerView.getAdapter().notifyDataSetChanged();
  }

  private static class ViewEntranceAdapter
      extends RecyclerView.Adapter<ViewEntranceAdapter.ViewEntranceHolder> {

    private AppCompatActivity mContext;
    private List<ViewModel> mViewModelList;

    public ViewEntranceAdapter(AppCompatActivity context, List<ViewModel> viewModelList) {
      mContext = context;
      mViewModelList = viewModelList;
    }

    @Override public ViewEntranceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ViewEntranceHolder(
          mContext.getLayoutInflater().inflate(R.layout.item_view_entrance, parent, false));
    }

    @Override public void onBindViewHolder(ViewEntranceHolder holder, int position) {
      holder.name.setText(mViewModelList.get(position).name);
      holder.root.setOnClickListener(mViewModelList.get(position).listener);
    }

    @Override public int getItemCount() {
      return null == mViewModelList ? 0 : mViewModelList.size();
    }

    static class ViewEntranceHolder extends RecyclerView.ViewHolder {

      private View root;
      private TextView name;

      public ViewEntranceHolder(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        name = (TextView) itemView.findViewById(R.id.name);
      }
    }
  }

  private static class ViewModel {
    private String name;
    private View.OnClickListener listener;

    public ViewModel(String name, View.OnClickListener listener) {
      this.name = name;
      this.listener = listener;
    }
  }
}
