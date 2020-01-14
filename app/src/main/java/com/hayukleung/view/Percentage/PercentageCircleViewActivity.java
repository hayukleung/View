package com.hayukleung.view.Percentage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.hayukleung.view.R;
import java.lang.ref.WeakReference;

/**
 * View
 * com.hayukleung.view.Percentage
 * PercentageCircleViewActivity.java
 *
 * by hayukleung
 * at 2017-05-25 10:16
 */

public class PercentageCircleViewActivity extends AppCompatActivity {

  private PercentageCircleView mPercentageCircleView;
  private PercentageHandler mPercentageHandler;
  private int mTotal = 100;
  private int mCurrent = 0;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_percentage_circle_view);

    mPercentageCircleView = (PercentageCircleView) findViewById(R.id.percentage_circle_view);

    mPercentageHandler = new PercentageHandler(this);
    mPercentageHandler.sendEmptyMessage(0);
  }

  @Override protected void onDestroy() {
    mPercentageHandler.removeCallbacksAndMessages(null);
    mPercentageHandler = null;
    super.onDestroy();
  }

  public void updatePercentage() {
    mPercentageCircleView.setTotal(mTotal);
    mPercentageCircleView.setCurrent(mCurrent);
    mPercentageCircleView.invalidate();
  }

  private static class PercentageHandler extends Handler {

    private WeakReference<PercentageCircleViewActivity> mPercentageCircleViewActivityWeakReference;

    public PercentageHandler(PercentageCircleViewActivity percentageCircleViewActivity) {
      mPercentageCircleViewActivityWeakReference =
          new WeakReference<>(percentageCircleViewActivity);
    }

    @Override public void handleMessage(Message msg) {
      PercentageCircleViewActivity percentageCircleViewActivity =
          mPercentageCircleViewActivityWeakReference.get();
      if (null == percentageCircleViewActivity) {
        return;
      }
      switch (msg.what) {
        case 0: {
          percentageCircleViewActivity.mCurrent++;
          percentageCircleViewActivity.mCurrent %= percentageCircleViewActivity.mTotal;
          percentageCircleViewActivity.updatePercentage();
          sendEmptyMessageDelayed(0, 100);
          break;
        }
      }
    }
  }
}
