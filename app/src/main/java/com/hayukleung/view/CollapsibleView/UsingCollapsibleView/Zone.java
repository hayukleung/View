package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.List;

/**
 * View
 * com.hayukleung.view.CollapsibleView.UsingCollapsibleView
 * Zone.java
 *
 * by hayukleung
 * at 2017-03-27 17:04
 */

/**
 * 行政区
 */
public class Zone implements Serializable, Comparable<Zone> {

  /** PPCCDD */
  private String code;
  private String name;
  private List<Zone> sub;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Zone> getSub() {
    return sub;
  }

  public void setSub(List<Zone> sub) {
    this.sub = sub;
  }

  public String getParentCode() {
    if ("00".equals(getCodePP())) {
      return "000000";
    } else if ("00".equals(getCodeCC())) {
      return "000000";
    } else if ("00".equals(getCodeDD())) {
      return getCodePP() + "0000";
    } else {
      return getCodePPCC() + "00";
    }
  }

  private String getCodePP() {
    return code.substring(0, 2);
  }

  private String getCodeCC() {
    return code.substring(2, 4);
  }

  private String getCodeDD() {
    return code.substring(4, 6);
  }

  private String getCodePPCC() {
    return code.substring(0, 4);
  }

  @Override public int hashCode() {
    return code.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (null == obj) return false;
    if (!(obj instanceof Zone)) return false;
    return code.equals(((Zone) obj).getCode());
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override public int compareTo(@NonNull Zone o) {
    return Integer.parseInt(code) - Integer.parseInt(o.code);
  }
}
