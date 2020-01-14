package com.hayukleung.view.CityList;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.List;

/**
 * 行政区
 */
public class City implements Serializable, Comparable<City> {

  /** PPCCDD */
  private String code;
  private String name;
  private String pinyin;
  private List<City> sub;
  /** 是否字母导航的第一个 */
  private boolean index = false;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<City> getSub() {
    return sub;
  }

  public void setSub(List<City> sub) {
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
    if (!(obj instanceof City)) return false;
    return code.equals(((City) obj).getCode());
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public boolean isIndex() {
    return index;
  }

  public void setIndex(boolean index) {
    this.index = index;
  }

  @Override public int compareTo(@NonNull City o) {
    // return Integer.parseInt(code) - Integer.parseInt(o.code);
    int delta = 0;
    int len =
        Math.min(null == pinyin ? 0 : pinyin.length(), null == o.pinyin ? 0 : o.pinyin.length());
    for (int i = 0; i < len; i++) {
      delta = pinyin.charAt(i) - o.pinyin.charAt(i);
      if (0 != delta) {
        return delta;
      }
    }
    return delta;
  }
}
