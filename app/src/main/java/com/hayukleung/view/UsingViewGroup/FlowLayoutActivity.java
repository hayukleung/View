package com.hayukleung.view.UsingViewGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.view.GroupingTagView.GroupingTagView;
import com.hayukleung.view.GroupingTagView.Tag;
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

public class FlowLayoutActivity extends AppCompatActivity implements View.OnClickListener {

  private FlowLayout mFlowLayout;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flow_layout);

    mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
    findViewById(R.id.add).setOnClickListener(this);
    findViewById(R.id.id01).setOnClickListener(this);
    findViewById(R.id.id02).setOnClickListener(this);
    findViewById(R.id.id03).setOnClickListener(this);
    findViewById(R.id.id04).setOnClickListener(this);
    findViewById(R.id.id05).setOnClickListener(this);
    findViewById(R.id.id06).setOnClickListener(this);
    findViewById(R.id.id07).setOnClickListener(this);
    findViewById(R.id.id08).setOnClickListener(this);
    findViewById(R.id.id09).setOnClickListener(this);
    findViewById(R.id.id10).setOnClickListener(this);
    findViewById(R.id.id11).setOnClickListener(this);
    findViewById(R.id.id12).setOnClickListener(this);
    findViewById(R.id.id13).setOnClickListener(this);
    findViewById(R.id.id14).setOnClickListener(this);
    findViewById(R.id.id15).setOnClickListener(this);
    findViewById(R.id.id16).setOnClickListener(this);
    findViewById(R.id.id17).setOnClickListener(this);
    findViewById(R.id.id18).setOnClickListener(this);
    findViewById(R.id.id19).setOnClickListener(this);
    findViewById(R.id.id20).setOnClickListener(this);
    findViewById(R.id.id21).setOnClickListener(this);
    findViewById(R.id.id22).setOnClickListener(this);
    findViewById(R.id.id23).setOnClickListener(this);
    findViewById(R.id.id24).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.add: {
        GroupingTagView groupingTagView = new GroupingTagView(FlowLayoutActivity.this);
        Tag tag = new Tag();
        tag.setContent("new tag");
        groupingTagView.setTag(tag);
        ViewGroup.MarginLayoutParams marginLayoutParams =
            new ViewGroup.MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        mFlowLayout.addView(groupingTagView, marginLayoutParams);
        break;
      }
      case R.id.id01:
      case R.id.id02:
      case R.id.id03:
      case R.id.id04:
      case R.id.id05:
      case R.id.id06:
      case R.id.id07:
      case R.id.id08:
      case R.id.id09:
      case R.id.id10:
      case R.id.id11:
      case R.id.id12:
      case R.id.id13:
      case R.id.id14:
      case R.id.id15:
      case R.id.id16:
      case R.id.id17:
      case R.id.id18:
      case R.id.id19:
      case R.id.id20:
      case R.id.id21:
      case R.id.id22:
      case R.id.id23:
      case R.id.id24: {
        if (View.VISIBLE == v.getVisibility()) {
          v.setVisibility(View.GONE);
        }
        break;
      }
    }
  }
}
