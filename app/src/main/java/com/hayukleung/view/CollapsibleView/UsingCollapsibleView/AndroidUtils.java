package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import de.greenrobot.common.io.FileUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class AndroidUtils {

  private static int GL_MAX_TEXTURE_SIZE = 0;

  static {
    try {
      GL_MAX_TEXTURE_SIZE = glMaxTextureSize();
    } catch (Throwable ignored) {
    }
  }

  public static void goHome(Activity activity) {
    try {
      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.addCategory(Intent.CATEGORY_HOME);
      activity.startActivity(intent);
    } catch (ActivityNotFoundException e) {
      activity.moveTaskToBack(true);
    }
  }

  public static void goHome1(Activity activity) {
    activity.moveTaskToBack(true);
  }

  public static void showInputMethod(Activity context, View v) {
    InputMethodManager manager =
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
    if (manager != null) manager.showSoftInput(v, 0);
  }

  public static void showInputMethod(Context context, View v) {
    InputMethodManager manager =
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
    if (manager != null) {
      manager.showSoftInput(v, 0);
    }
  }

  public static void hideInputMethod(Context context, View v) {
    hideInputMethod(context, v.getWindowToken());
  }

  public static void hideInputMethod(Context context, IBinder token) {
    InputMethodManager manager =
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
    if (manager != null) {
      manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  public static void checkNotMain() {
    if (isMain()) {
      throw new IllegalStateException("Method call should not happen from the main thread.");
    }
  }

  public static boolean isMain() {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }

  public static void checkMain() {
    if (!isMain()) {
      throw new IllegalStateException("Method call should happen from the main thread.");
    }
  }

  public static int getGlMaxTextureSize() {
    return GL_MAX_TEXTURE_SIZE;
  }

  private static int glMaxTextureSize() {
    EGL10 egl = (EGL10) EGLContext.getEGL();

    EGLDisplay dpy = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
    int[] vers = new int[2];
    egl.eglInitialize(dpy, vers);

    int[] configAttr = {
        EGL10.EGL_COLOR_BUFFER_TYPE, EGL10.EGL_RGB_BUFFER, EGL10.EGL_LEVEL, 0,
        EGL10.EGL_SURFACE_TYPE, EGL10.EGL_PBUFFER_BIT, EGL10.EGL_NONE
    };
    EGLConfig[] configs = new EGLConfig[1];
    int[] numConfig = new int[1];
    egl.eglChooseConfig(dpy, configAttr, configs, 1, numConfig);
    if (numConfig[0] == 0) {
      // TROUBLE! No config found.
    }
    EGLConfig config = configs[0];

    int[] surfAttr = {
        EGL10.EGL_WIDTH, 64, EGL10.EGL_HEIGHT, 64, EGL10.EGL_NONE
    };
    EGLSurface surf = egl.eglCreatePbufferSurface(dpy, config, surfAttr);
    final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;  // missing in EGL10
    int[] ctxAttrib = {
        EGL_CONTEXT_CLIENT_VERSION, 1, EGL10.EGL_NONE
    };
    EGLContext ctx = egl.eglCreateContext(dpy, config, EGL10.EGL_NO_CONTEXT, ctxAttrib);
    egl.eglMakeCurrent(dpy, surf, surf, ctx);
    int[] maxSize = new int[1];
    GLES10.glGetIntegerv(GLES10.GL_MAX_TEXTURE_SIZE, maxSize, 0);
    egl.eglMakeCurrent(dpy, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
    egl.eglDestroySurface(dpy, surf);
    egl.eglDestroyContext(dpy, ctx);
    egl.eglTerminate(dpy);
    return maxSize[0];
  }

  public static boolean isPackageInstall(Context context, String packageName) {
    try {
      context.getPackageManager().getApplicationInfo(packageName, 0);
      return true;
    } catch (PackageManager.NameNotFoundException e) {
      return false;
    }
  }

  public static String getVersionName(Context context) {
    String versionName = null;
    try {
      versionName =
          context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException ignored) {
    }
    return versionName;
  }

  public static int getVersionCode(Context context) {
    int versionCode = 0;
    try {
      versionCode =
          context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    } catch (PackageManager.NameNotFoundException ignored) {
    }
    return versionCode;
  }

  public static String getAppName(Context context) {
    String versionName = null;
    try {
      int nameRes = context.getPackageManager()
          .getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes;
      if (nameRes != 0) versionName = context.getString(nameRes);
    } catch (PackageManager.NameNotFoundException ignored) {
    }
    return versionName;
  }

  public static int getNetworkType(Context context) {
    NetworkInfo info = ((ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    if (info != null) {
      return info.getType();
    }
    return -1;
  }

  public static boolean isNetworkConnected(Context context) {
    NetworkInfo info = ((ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    return info != null && info.isAvailable();
  }

  public static boolean isWifiConnected(Context context) {
    ConnectivityManager manager =
        ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    NetworkInfo wiFiNetworkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if (wiFiNetworkInfo != null) {
      return wiFiNetworkInfo.isAvailable();
    }
    return false;
  }

  public static boolean isMobileConnected(Context context) {
    ConnectivityManager manager =
        ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    NetworkInfo mobileNetworkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    if (mobileNetworkInfo != null) {
      return mobileNetworkInfo.isAvailable();
    }
    return false;
  }

  public static int getStatusBarHeight(Resources res) {
    int result = 0;
    int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = res.getDimensionPixelSize(resourceId);
    }
    return result;
  }

  public static int getNavBarHeight(Resources res) {
    int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
    if (resourceId > 0) {
      return res.getDimensionPixelSize(resourceId);
    }
    return 0;
  }

  public static int getWidth(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.widthPixels;
  }

  public static int getDensity(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.densityDpi;
  }

  public static int dp2px(Context context, float dp) {
    return Math.round(dp * context.getResources().getDisplayMetrics().density);
  }

  public static boolean hasJellyBean() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
  }

  public static boolean hasLollipop() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  }

  /**
   * 在系统 'Pictures' 文件夹中生成以 '.jpg' 结束的文件
   */
  public static File getTmpFile() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      return getTmpFile(null);
    }
    throw new IllegalStateException("The media not mounted.");
  }

  /**
   * @param parent 在 parent 目录中生成临时文件
   */
  public synchronized static File getTmpFile(File parent, String random) {
    if (!parent.exists()) {
      parent.mkdirs();
    }
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
    StringBuilder builder = new StringBuilder();
    builder.append("tmp_");
    builder.append(timeStamp);
    if (random != null) builder.append("_").append(random);
    String fileName = builder.toString();
    File file = new File(parent, fileName);
    if (file.exists()) {
      file = getTmpFile(parent, RandomStringUtils.randomAlphabetic(4));
    }
    try {
      file.createNewFile();
    } catch (IOException e) {
      Ln.e(e);
    }
    return file;
  }

  /**
   * @param parent 在 parent 目录中生成临时文件夹
   */
  public synchronized static File getTmpDir(File parent, String random) {
    if (!parent.exists()) {
      parent.mkdirs();
    }
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
    StringBuilder builder = new StringBuilder();
    builder.append("tmp_");
    builder.append(timeStamp);
    if (random != null) builder.append("_").append(random);
    String fileName = builder.toString();
    File file = new File(parent, fileName);
    if (file.exists()) {
      file = getTmpDir(parent, RandomStringUtils.randomAlphabetic(4));
    }
    file.mkdir();
    return file;
  }

  private synchronized static File getTmpFile(String random) {
    File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    if (!pic.exists()) {
      pic.mkdirs();
    }
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
    StringBuilder builder = new StringBuilder();
    builder.append("media_");
    builder.append(timeStamp);
    if (random != null) builder.append("_").append(random);
    builder.append(".jpg");
    String fileName = builder.toString();
    File file = new File(pic, fileName);
    if (file.exists()) {
      file = getTmpFile(RandomStringUtils.randomAlphabetic(4));
    }
    return file;
  }

  public static boolean isKeyPanShow(Context context, Window window) {
    return getKeyPanHeight(context, window) != 0;
  }

  public static int getKeyPanHeight(Context context, Window window) {
    return getHeight(context) - getStatusBarHeight(window) - getContentHeight(window);
  }

  public static int getHeight(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.heightPixels;
  }

  public static int getStatusBarHeight(Window window) {
    Rect rect = new Rect();
    window.getDecorView().getWindowVisibleDisplayFrame(rect);
    return rect.top;
  }

  public static int getContentHeight(Window window) {
    Rect rect = new Rect();
    window.getDecorView().getWindowVisibleDisplayFrame(rect);
    return rect.height();
  }

  public static void copy(Context context, String content) {
    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      cm.setPrimaryClip(ClipData.newPlainText(content, content));
    } else {
      cm.setText(content);
    }
  }

  /**
   * 遍历删除文件和子文件
   */
  public static boolean deleteFile(File file) {
    if (file.isDirectory()) {
      File[] children = file.listFiles();
      for (File temp : children) {
        boolean success = deleteFile(temp);
        if (!success) {
          return false;
        }
      }
    }
    return file.delete();
  }

  /**
   * 解压缩zip包
   *
   * @param zipFilePath zip文件路径
   * @param targetPath 解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
   * @throws IOException
   */
  public static void unZip(String zipFilePath, String targetPath) throws IOException {
    File pathFile = new File(targetPath);
    if (!pathFile.exists()) {
      pathFile.mkdirs();
    }
    byte[] buffer = new byte[1024];
    ZipFile zip = null;
    InputStream in = null;
    OutputStream out = null;
    try {
      zip = new ZipFile(zipFilePath);
      for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
        try {
          ZipEntry entry = (ZipEntry) entries.nextElement();
          String zipEntryName = entry.getName();
          in = zip.getInputStream(entry);
          String outPath = (targetPath + "/" + zipEntryName).replaceAll("\\*", "/");
          //判断路径是否存在,不存在则创建文件路径
          File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
          if (!file.exists()) {
            file.mkdirs();
          }
          //判断文件全路径是否为文件夹,如果是上面已经创建,不需要解压
          if (new File(outPath).isDirectory()) {
            continue;
          }
          int len;
          out = new FileOutputStream(outPath);
          while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
          }
        } finally {
          if (in != null) {
            in.close();
          }
          if (out != null) {
            out.close();
          }
        }
      }
    } finally {
      if (zip != null) {
        zip.close();
      }
    }
  }

  public static void copyDirectory(File srcDir, File destDir) throws IOException {
    if (destDir.exists()) {
      if (!destDir.isDirectory()) {
        throw new IOException("Destination '" + destDir + "' exists but is not a directory");
      }
    } else {
      if (!destDir.mkdirs()) {
        throw new IOException("Destination '" + destDir + "' directory cannot be created");
      }
    }
    if (!destDir.canWrite()) {
      throw new IOException("Destination '" + destDir + "' cannot be written to");
    }
    // recurse
    File[] files = srcDir.listFiles();
    if (files == null) {  // null if security restricted
      throw new IOException("Failed to list contents of " + srcDir);
    }
    for (int i = 0; i < files.length; i++) {
      File copiedFile = new File(destDir, files[i].getName());
      if (files[i].isDirectory()) {
        copyDirectory(files[i], copiedFile);
      } else {
        FileUtils.copyFile(files[i], copiedFile);
      }
    }
  }

  public static void prepareView(View view) {
    view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
  }

  // I added a generic return type to reduce the casting noise in client code
  @SuppressWarnings("unchecked") public static <T extends View> T get(View view, int id) {
    SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
    if (viewHolder == null) {
      viewHolder = new SparseArray<View>();
      view.setTag(viewHolder);
    }
    View childView = viewHolder.get(id);
    if (childView == null) {
      childView = view.findViewById(id);
      viewHolder.put(id, childView);
    }
    return (T) childView;
  }

  public static long handleDownload(final Context context, final String fromUrl,
      final String toFilename) {
    final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fromUrl));
    if (Build.VERSION.SDK_INT >= 11) {
      request.allowScanningByMediaScanner();
      request.setNotificationVisibility(
          DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    }
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, toFilename);

    final DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    try {
      return dm.enqueue(request);
    } catch (SecurityException e) {
      if (Build.VERSION.SDK_INT >= 11) {
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
      }
      return dm.enqueue(request);
    }
  }

  public static boolean openAppSettings(final Context context, final String packageName) {
    try {
      final Intent intent =
          new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
      intent.setData(Uri.parse("package:" + packageName));
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      context.startActivity(intent);

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
