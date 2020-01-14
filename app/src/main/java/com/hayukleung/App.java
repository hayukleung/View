package com.hayukleung;

import androidx.multidex.MultiDexApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * View
 * com.hayukleung
 * App.java
 *
 * by hayukleung
 * at 2017-03-27 17:38
 */

public class App extends MultiDexApplication {

  private static App mInstance;
  private Gson mGson;

  public static App getInstance() {
    return mInstance;
  }

  public static synchronized Gson getGson() {
    if (mInstance.mGson == null) {
      mInstance.mGson = new GsonBuilder().create();
    }

    return mInstance.mGson;
  }

  @Override public void onCreate() {
    super.onCreate();
    mInstance = this;
  }
}
