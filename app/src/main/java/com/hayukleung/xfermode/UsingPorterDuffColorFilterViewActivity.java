package com.hayukleung.xfermode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UsingPorterDuffColorFilterViewActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new UsingPorterDuffColorFilterView(this));
  }
}
