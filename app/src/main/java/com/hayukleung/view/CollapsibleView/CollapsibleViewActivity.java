package com.hayukleung.view.CollapsibleView;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import com.hayukleung.view.R;
import java.util.ArrayList;

/**
 * Created by hayukleung on 15/9/3.
 */
public class CollapsibleViewActivity extends AppCompatActivity {

  private CollapsibleView mCollapsibleView;
  private ArrayList<Element> mAllElements = new ArrayList<>();
  private ArrayList<Element> mVisibleElements = new ArrayList<>();
  private ArrayList<Element> mTempElements = new ArrayList<>();

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
    // TODO set mAllElements & mVisibleElements
    Element rootElement = new Element("0", "中华人民共和国", true) {

      @Override public void onElementClick() {

      }
    };
    rootElement.setParentId("");
    mVisibleElements.add(rootElement);
    mAllElements.add(rootElement);
    // 大区 ======================================================
    Element element;
    element = new Element("11", "华北", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("12", "东北", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("13", "华东", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("14", "华南", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("15", "华中", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("16", "西南", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("17", "西北", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    element = new Element("18", "港澳台", true) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId(rootElement.getId());
    mAllElements.add(element);

    // 省区 ======================================================
    // 华北
    element = new Element("201", "北京市", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("11");
    mAllElements.add(element);
    element = new Element("202", "天津市", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("11");
    mAllElements.add(element);
    element = new Element("203", "河北省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("11");
    mAllElements.add(element);
    element = new Element("204", "山西省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("11");
    mAllElements.add(element);
    element = new Element("205", "内蒙古自治区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("11");
    mAllElements.add(element);
    // 东北
    element = new Element("206", "辽宁省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("12");
    mAllElements.add(element);
    element = new Element("207", "吉林省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("12");
    mAllElements.add(element);
    element = new Element("208", "黑龙江省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("12");
    mAllElements.add(element);
    // 华东
    element = new Element("209", "上海市", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("210", "江苏省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("211", "浙江省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("212", "安徽省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("213", "福建省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("214", "山西省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    element = new Element("215", "山东省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("13");
    mAllElements.add(element);
    // 华南
    element = new Element("216", "广东省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("14");
    mAllElements.add(element);
    element = new Element("217", "广西壮族自治区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("14");
    mAllElements.add(element);
    element = new Element("218", "海南省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("14");
    mAllElements.add(element);
    // 华中
    element = new Element("219", "河南省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("15");
    mAllElements.add(element);
    element = new Element("220", "湖北省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("15");
    mAllElements.add(element);
    element = new Element("221", "湖南省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("15");
    mAllElements.add(element);
    // 西南
    element = new Element("222", "重庆市", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("16");
    mAllElements.add(element);
    element = new Element("223", "四川省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("16");
    mAllElements.add(element);
    element = new Element("224", "贵州省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("16");
    mAllElements.add(element);
    element = new Element("225", "云南省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("16");
    mAllElements.add(element);
    element = new Element("226", "西藏自治区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("16");
    mAllElements.add(element);
    // 西北
    element = new Element("227", "陕西省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("17");
    mAllElements.add(element);
    element = new Element("228", "甘肃省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("17");
    mAllElements.add(element);
    element = new Element("229", "青海省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("17");
    mAllElements.add(element);
    element = new Element("230", "宁夏回族自治区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("17");
    mAllElements.add(element);
    element = new Element("231", "新疆维吾尔自治区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("17");
    mAllElements.add(element);
    // 港澳台
    element = new Element("232", "香港特别行政区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("18");
    mAllElements.add(element);
    element = new Element("233", "澳门特别行政区", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("18");
    mAllElements.add(element);
    element = new Element("234", "台湾省", false) {

      @Override public void onElementClick() {

      }
    };
    element.setParentId("18");
    mAllElements.add(element);

    // 市区 ======================================================

    mCollapsibleView.buildTree().sortTree().notifyDataSetChanged();
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
}
