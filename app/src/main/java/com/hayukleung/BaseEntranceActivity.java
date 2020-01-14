package com.hayukleung;

import android.graphics.Canvas;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hayukleung.view.R;
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

public class BaseEntranceActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private List<Entrance> mEntranceList = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_entrance);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
      }
    });
    mRecyclerView.setAdapter(new EntranceAdapter(this, mEntranceList));
  }

  protected void addEntrance(Entrance entrance) {
    mEntranceList.add(entrance);
    mRecyclerView.getAdapter().notifyDataSetChanged();
  }
}
