package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import com.hayukleung.collapsibleview.CollapsibleView;
import com.hayukleung.collapsibleview.Element;
import com.hayukleung.collapsibleview.IElement;
import com.hayukleung.collapsibleview.OnCollapsibleClickListener;
import com.hayukleung.view.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UsingCollapsibleViewActivity extends AppCompatActivity {

  private PausedHandler mHandler = new Handler(this);

  private CollapsibleView mCollapsibleView;
  private ArrayList<Element> mAllElements = new ArrayList<>();
  private ArrayList<Element> mVisibleElements = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_collapsible_view);

    initWidgets();

    if (null == savedInstanceState) {
      initData();
    } else {
      onRestoreInstanceState(savedInstanceState);
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList("all", mAllElements);
    outState.putParcelableArrayList("visible", mVisibleElements);
  }

  @Override protected void onPause() {
    super.onPause();
    mHandler.pause();
  }

  @Override protected void onResume() {
    super.onResume();
    mHandler.resume();
  }

  /**
   * 初始化控件
   */
  private void initWidgets() {
    mCollapsibleView = (CollapsibleView) findViewById(R.id.collapsible_view);
    mCollapsibleView.setAllElements(mAllElements)
        .setVisibleElements(mVisibleElements)
        .setOnCollapsibleClickListener(new OnCollapsibleClickListener() {
          @Override public void onUsrClick(Element usr, int position) {
            Snackbar.make(mCollapsibleView, usr.getName(), Snackbar.LENGTH_SHORT).show();
          }

          @Override public boolean onOrgClick(Element org, int position) {
            return false;
          }
        })
        .commit();
  }

  /**
   * 初始化数据
   */
  private void initData() {

    JsonZone.getZoneList()
        .subscribeOn(Schedulers.io())
        .observeOn(PausedHandlerScheduler.from(this.mHandler))
        .subscribe(new Action1<List<Zone>>() {
          @Override public void call(List<Zone> zones) {

            List<Element> elementList = convert(zones);

            mAllElements.addAll(elementList);
            mVisibleElements.addAll(elementList);

            mCollapsibleView.buildTree().sortTree().notifyDataSetChanged();
          }
        });
  }

  private List<Element> convert(List<Zone> zoneList) {
    List<Element> elementList = new ArrayList<>();
    if (null == zoneList || 0 == zoneList.size()) {
      return elementList;
    }
    for (Zone zone : zoneList) {
      Element element = new Element(zone.getCode(), zone.getName(),
          null != zone.getSub() && 0 < zone.getSub().size()) {

        // 重写排序
        @Override public int compareTo(IElement another) {
          return Integer.parseInt(getId()) - Integer.parseInt(another.getId());
        }
      };
      element.setParentId(zone.getParentCode());
      element.addChildren(convert(zone.getSub()));
      elementList.add(element);
      Ln.e("parentCode = "
          + element.getParentId()
          + ", code = "
          + element.getId()
          + ", name = "
          + element.getName());
    }
    return elementList;
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (null != savedInstanceState) {
      if (0 == mAllElements.size()) {
        // mAllElements.addAll((ArrayList<Element>) savedInstanceState.getSerializable("all"));
        mAllElements = savedInstanceState.getParcelableArrayList("all");
      }
      if (0 == mVisibleElements.size()) {
        // mVisibleElements.addAll((ArrayList<Element>) savedInstanceState.getSerializable("visible"));
        mVisibleElements = savedInstanceState.getParcelableArrayList("visible");
      }
      mCollapsibleView.setAllElements(mAllElements).setVisibleElements(mVisibleElements).commit();
      // mCollapsibleView.buildTree().notifyDataSetChanged();
    }
  }

  private static class Handler extends PausedHandler {

    private WeakReference<UsingCollapsibleViewActivity> mUsingCollapsibleViewActivityWeakReference;

    public Handler(UsingCollapsibleViewActivity usingCollapsibleViewActivity) {
      this.mUsingCollapsibleViewActivityWeakReference =
          new WeakReference(usingCollapsibleViewActivity);
    }

    @Override protected void processMessage(Message message) {

    }
  }
}
