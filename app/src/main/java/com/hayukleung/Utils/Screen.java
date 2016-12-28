package com.hayukleung.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕模型，对DisplayMetrics进行封装
 *
 * @author hayukleung
 * @ref https://github.com/hayukleung/AndroidScreenMatchingUtil
 */
public class Screen {

  /** 竖屏 */
  public final static int ORIENTATION_VERTICAL = 0x0001;
  /** 横屏 */
  public final static int ORIENTATION_HORIZONTAL = 0x0002;
  private static Screen singleInstance;
  /** 宽 */
  public int widthPx;
  /** 高 */
  public int heightPx;
  /** 密度dpi */
  public int densityDpi;
  /** 缩放系数densityDpi/160 */
  public float densityScale;
  /** 文字缩放系数 */
  public float fontScale;
  /** 屏幕朝向 */
  public int orientation;

  /**
   * 私有构造方法
   *
   * @param context
   */
  private Screen(Context context) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    this.widthPx = metrics.widthPixels;
    this.heightPx = metrics.heightPixels;
    this.densityDpi = metrics.densityDpi;
    this.densityScale = metrics.density;
    this.fontScale = metrics.scaledDensity;
    this.orientation = heightPx > widthPx ? ORIENTATION_VERTICAL : ORIENTATION_HORIZONTAL;
  }

  /**
   * 获取实例
   *
   * @param context
   * @return
   */
  public static Screen getInstance(Context context) {
    if (singleInstance == null) {
      singleInstance = new Screen(context);
    }
    return singleInstance;
  }

  /**
   * 根据设备屏幕密度将px转换为dp
   *
   * @param valuePx
   * @return
   */
  public int px2dp(float valuePx) {
    return (int) (valuePx / densityScale + 0.5f);
  }

  /**
   * 根据设备屏幕密度将dp转换为px
   *
   * @param valueDp
   * @return
   */
  public int dp2px(float valueDp) {
    return (int) (valueDp * densityScale + 0.5f);
  }

  /**
   * 根据设备屏幕密度将px转换为sp
   *
   * @param valuePx
   * @return
   */
  public int px2sp(float valuePx) {
    return (int) (valuePx / fontScale + 0.5f);
  }

  /**
   * 根据设备屏幕密度将sp转换为px
   *
   * @param valueSp
   * @return
   */
  public int sp2px(float valueSp) {
    return (int) (valueSp * fontScale + 0.5f);
  }
}