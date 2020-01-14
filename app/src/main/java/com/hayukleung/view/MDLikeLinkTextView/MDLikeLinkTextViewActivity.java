package com.hayukleung.view.MDLikeLinkTextView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.MDLikeLinkTextView
 * MDLikeLinkTextViewActivity.java
 *
 * by hayukleung
 * at 2017-01-24 16:32
 */

public class MDLikeLinkTextViewActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_md_like_link_text_view);
    MDLikeLinkTextView mdlltv = (MDLikeLinkTextView) findViewById(R.id.mdlltv);
//    mdlltv.setText("今天天气不错！");
//    mdlltv.setText("[今天天气不错！](http://www.baidu.com)");
//    mdlltv.setText("今天天气不错！[详情]()");
//    mdlltv.setText("今天天气不错！[](http://www.baidu.com)");
//    mdlltv.setText("今天天气不错！[详情](http://www.baidu.com)");
//    mdlltv.setText("[详情](http://www.baidu.com)今天天气不错！");
//    mdlltv.setText("[详情1](http://www.baidu.com)今天天气不错！[详情2](http://www.baidu.com)");
//    mdlltv.setText("[详情]今天天气不错！(http://www.baidu.com)");
//    mdlltv.setText("[详情1](http://www.baidu.com)[详情2](http://www.baidu.com)今天天气不错！");
    mdlltv.setText("今天天气不错！[详情1](http://www.baidu.com)[详情2](http://www.baidu.com)");
  }
}
