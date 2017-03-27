package com.hayukleung.view.CollapsibleView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.hayukleung.view.R;
import java.util.ArrayList;
import java.util.List;

/**
 * CollapsibleView.java
 * <p>
 * Created by hayukleung on 1/8/16.
 */
public class CollapsibleView extends RecyclerView {

  private Context mContext;
  private List<Element> mAllElements = new ArrayList<>();
  private List<Element> mVisibleElements = new ArrayList<>();
  private OnCollapsibleClickListener mCollapsibleClickListener;
  private CollapsibleAdapter mCollapsibleAdapter;
  private int mBgResIdOrg;
  private int mBgResIdUsr;
  private int mBgResIdClosed;
  private int mBgResIdOpened;

  public CollapsibleView(Context context) {
    this(context, null);
  }

  public CollapsibleView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CollapsibleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.mContext = context;
    if (attrs != null) {
      // 自定义属性
      int defStyleRes = 0;
      TypedArray a =
          context.obtainStyledAttributes(attrs, R.styleable.CollapsibleView, defStyle, defStyleRes);
      mBgResIdOrg =
          a.getResourceId(R.styleable.CollapsibleView_drawableOrg, R.drawable.collapsible_view_org);
      mBgResIdUsr =
          a.getResourceId(R.styleable.CollapsibleView_drawableUsr, R.drawable.collapsible_view_usr);
      mBgResIdClosed = a.getResourceId(R.styleable.CollapsibleView_drawableClosed,
          R.drawable.collapsible_view_more);
      mBgResIdOpened = a.getResourceId(R.styleable.CollapsibleView_drawableOpened,
          R.drawable.collapsible_view_less);
      a.recycle();
    }
    setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    addItemDecoration(new DividerDecoration(context));
  }

  @Override public void setAdapter(Adapter adapter) {
    throw new UnsupportedOperationException(
        "Use setAllElements(allElements).setVisibleElements(visibleElements).setOnCollapsibleClickListener(onCollapsibleClickListener).commit() instead of this.");
  }

  public CollapsibleView setAllElements(List<Element> allElements) {
    this.mAllElements = allElements;
    return this;
  }

  public CollapsibleView setVisibleElements(List<Element> visibleElements) {
    this.mVisibleElements = visibleElements;
    return this;
  }

  public CollapsibleView setOnCollapsibleClickListener(
      OnCollapsibleClickListener onCollapsibleClickListener) {
    this.mCollapsibleClickListener = onCollapsibleClickListener;
    return this;
  }

  public void commit() {
    mCollapsibleAdapter =
        new CollapsibleAdapter(mContext, mBgResIdOrg, mBgResIdUsr, mBgResIdClosed, mBgResIdOpened,
            mAllElements, mVisibleElements, mCollapsibleClickListener);
    setAdapter(mCollapsibleAdapter);
  }

  private void setAdapter(CollapsibleAdapter collapsibleAdapter) {
    super.setAdapter(collapsibleAdapter);
  }

  public CollapsibleView buildTree() {
    if (null != mCollapsibleAdapter) {
      mCollapsibleAdapter.buildTree();
    }
    return this;
  }

  public CollapsibleView sortTree() {
    if (null != mCollapsibleAdapter) {
      mCollapsibleAdapter.sortTree(mVisibleElements);
    }
    return this;
  }

  public void notifyDataSetChanged() {
    if (null != mCollapsibleAdapter) {
      mCollapsibleAdapter.notifyDataSetChanged();
    }
  }
}
