package com.hayukleung.view.UsingViewGroup;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.UsingViewGroup
 * FlowLayoutActivity.java
 *
 * by hayukleung
 * at 2017-03-23 16:18
 */

/**
 * 实验中
 */
public class AutoSizingFlowLayoutActivity extends AppCompatActivity {

  private boolean isSized = false;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto_sizing_flow_layout);

    final AutoSizingFlowLayout autoSizingFlowLayout =
        (AutoSizingFlowLayout) findViewById(R.id.auto_sizing_flow_layout);

    autoSizingFlowLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
          int oldTop, int oldRight, int oldBottom) {
        Log.e("xx", "" + v.getWidth());
        if (!isSized) {
          isSized = true;
          AutoSizingUtil.group((AutoSizingFlowLayout) v);
        }
      }
    });
  }
}
