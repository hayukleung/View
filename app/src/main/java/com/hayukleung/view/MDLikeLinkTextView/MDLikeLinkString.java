package com.hayukleung.view.MDLikeLinkTextView;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hayukleung.view.MDLikeLinkTextView.MDLikeLinkRegex.REGEX;
import static com.hayukleung.view.MDLikeLinkTextView.MDLikeLinkRegex.REGEX_LINK;
import static com.hayukleung.view.MDLikeLinkTextView.MDLikeLinkRegex.REGEX_TEXT;

/**
 * View
 * com.hayukleung.view.MDLikeLinkTextView
 * MDLikeLinkString.java
 *
 * by hayukleung
 * at 2017-01-24 17:44
 */

/**
 * [AAAA](BBBB)
 */
public class MDLikeLinkString implements Serializable {

  private String raw;
  private String text;
  private String link;

  private Pattern mPatternMDLLString, mPatternMDLLText, mPatternMDLLLink;

  public MDLikeLinkString(String raw) {

    mPatternMDLLString = Pattern.compile(REGEX);
    mPatternMDLLText = Pattern.compile(REGEX_TEXT);
    mPatternMDLLLink = Pattern.compile(REGEX_LINK);

    Matcher matcher = mPatternMDLLString.matcher(raw);
    if (matcher.find()) {
      this.raw = raw;
      this.text = getMDLLText(raw);
      this.link = getMDLLLink(raw);
    } else {
      throw new MDLLException();
    }
  }

  public String getRaw() {
    return raw;
  }

  public String getText() {
    return text;
  }

  public String getLink() {
    return link;
  }

  /**
   * [AAAA](BBBB)
   *
   * @param mdll
   * @return AAAA
   */
  private String getMDLLText(String mdll) {
    Matcher matcher = mPatternMDLLText.matcher(mdll);
    if (matcher.find()) {
      String text = matcher.group();
      int length = text.length();
      return text.substring(1, length - 1);
    }
    return "";
  }

  /**
   * [AAAA](BBBB)
   *
   * @param mdll
   * @return BBBB
   */
  private String getMDLLLink(String mdll) {
    Matcher matcher = mPatternMDLLLink.matcher(mdll);
    if (matcher.find()) {
      String link = matcher.group();
      int length = link.length();
      return link.substring(1, length - 1);
    }
    return "";
  }
}
