package com.hayukleung.view.UsingViewGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import com.hayukleung.view.GroupingTagView.GroupingTag;
import com.hayukleung.view.GroupingTagView.GroupingTagView;
import com.hayukleung.view.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * FlowLayoutActivity.java
 *
 * by hayukleung
 * at 2017-03-23 16:18
 */

public class FlowLayoutActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flow_layout);

    final FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout);
    findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        GroupingTagView groupingTagView = new GroupingTagView(FlowLayoutActivity.this);
        GroupingTag groupingTag = new GroupingTag();
        groupingTag.setContent("new tag");
        groupingTagView.setGroupingTag(groupingTag);
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        flowLayout.addView(groupingTagView, marginLayoutParams);
      }
    });
  }
}
