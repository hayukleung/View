package com.hayukleung.view.PatternView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.hayukleung.view.R;

/**
 * View
 * com.hayukleung.view.PatternView
 * PatternViewActivity.java
 *
 * by hayukleung
 * at 2017-02-03 14:22
 */

public class PatternViewActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pattern_view);
    final PatternView patternView = (PatternView) findViewById(R.id.pattern);
    findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        patternView.clear();
      }
    });
  }
}
