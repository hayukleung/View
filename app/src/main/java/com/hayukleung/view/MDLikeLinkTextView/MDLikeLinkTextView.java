package com.hayukleung.view.MDLikeLinkTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import com.hayukleung.utils.Screen;
import com.hayukleung.view.BaseView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hayukleung.view.MDLikeLinkTextView.MDLikeLinkRegex.REGEX;

/**
 * View
 * com.hayukleung.view.MDLikeLinkTextView
 * MDLikeLinkTextView.java
 *
 * by hayukleung
 * at 2017-01-24 15:36
 */

/**
 * TextView for markdown like link string.
 *
 * e.g. - [Google](https://google.com/) -
 *
 * regex
 *
 * [0] get markdown like link string from text
 * - \[{1}([^\[\]\(\)]+)\]\({1}([^\[\]\(\)]+)\)
 * @see MDLikeLinkRegex#REGEX
 *
 * [1] get text from markdown like link string
 * - \[{1}([^\[\]\(\)]+)\]
 * @see MDLikeLinkRegex#REGEX_TEXT
 *
 * [2] get link from markdown like link string
 * - \({1}([^\[\]\(\)]+)\)
 * @see MDLikeLinkRegex#REGEX_LINK
 */
public class MDLikeLinkTextView extends BaseView {

  private String mContent;
  private final List<MDLikeLinkString> mMDLikeLinkStringList = new ArrayList<>();
  private Pattern mPatternMDLLString;
  // private Pattern mPatternMDLLText, mPatternMDLLLink;

  private Paint mPaintText;

  public MDLikeLinkTextView(Context context) {
    super(context);
  }

  public MDLikeLinkTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MDLikeLinkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public MDLikeLinkTextView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mPatternMDLLString = Pattern.compile(REGEX);
    // mPatternMDLLText = Pattern.compile(REGEX_TEXT);
    // mPatternMDLLLink = Pattern.compile(REGEX_LINK);
    mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintText.setTextSize(Screen.getInstance(getContext()).sp2px(14));
  }

  @Override protected void onDraw(Canvas canvas) {
    for (MDLikeLinkString string : mMDLikeLinkStringList) {
      mContent = mContent.replace(string.getRaw(), string.getText());
    }
    if (null != mContent) {
      int size = getWidth() / 10;
      mPaintText.setColor(Color.argb(255, 0, 0, 0));
      canvas.drawText(mContent, size, size, mPaintText);
    }
  }

  /**
   * 设置内容
   *
   * @param content
   */
  public void setText(String content) {
    this.mContent = content;
    Log.e(MDLikeLinkTextView.class.getSimpleName(), "原始内容 --> " + content);
    Log.e(MDLikeLinkTextView.class.getSimpleName(), "解析出来的mdLikeLinkString --> ");

    mMDLikeLinkStringList.clear();
    Matcher matcher = mPatternMDLLString.matcher(content);
    int count = 0;
    while (matcher.find()) {
      MDLikeLinkString mdLikeLinkString = new MDLikeLinkString(matcher.group());
      mMDLikeLinkStringList.add(mdLikeLinkString);
      Log.e(MDLikeLinkTextView.class.getSimpleName(),
          "        " + "[" + count++ + "]" + mdLikeLinkString);
    }
    if (0 == count) {
      Log.e(MDLikeLinkTextView.class.getSimpleName(), "        " + "无匹配的结果");
    }
    Log.e(MDLikeLinkTextView.class.getSimpleName(), "\n");

    invalidate();
  }
}
