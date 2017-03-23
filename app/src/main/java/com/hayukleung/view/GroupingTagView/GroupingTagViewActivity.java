package com.hayukleung.view.GroupingTagView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.hayukleung.view.R;
import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTagViewActivity.java
 *
 * by hayukleung
 * at 2017-03-22 19:05
 */

/**
 *
 */
public class GroupingTagViewActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private List<GroupingTag> mGroupingTagList = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_grouping_tag_view);

    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
    mRecyclerView.setAdapter(new GroupingTagViewAdapter(mGroupingTagList));

    GroupingTag groupingTag;

    groupingTag = new GroupingTag();
    groupingTag.setContent("***");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("******");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("what are you 弄啥嘞？");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("**");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*****");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*****哈哈********");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("哦");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*纳尼*");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*****");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("你好饭店~~~");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("**");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*****");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("****");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("***");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("*****");
    mGroupingTagList.add(groupingTag);

    groupingTag = new GroupingTag();
    groupingTag.setContent("***");
    mGroupingTagList.add(groupingTag);

    mRecyclerView.getAdapter().notifyDataSetChanged();

    // GroupingTagView groupingTagView = (GroupingTagView) findViewById(R.id.grouping_tag_view);
    // groupingTagView.setContent("test");
  }
}
