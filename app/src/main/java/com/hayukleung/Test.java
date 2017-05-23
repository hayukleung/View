package com.hayukleung;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * View
 * com.hayukleung
 * Test.java
 *
 * by hayukleung
 * at 2017-05-23 19:00
 */

public class Test {

  /**
   * 引用队列 Demo
   *
   * @param argv
   */
  public static void main(String[] argv) {
    final ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();

    Thread thread = new Thread() {
      @Override public void run() {
        try {
          int cnt = 0;
          WeakReference<byte[]> k;
          while ((k = (WeakReference) referenceQueue.remove()) != null) {
            System.out.println((cnt++) + "回收了:" + k);
          }
        } catch (InterruptedException e) {
          //结束循环
        }
      }
    };
    thread.setDaemon(true);
    thread.start();

    Object value = new Object();
    Map<Object, Object> map = new HashMap<>();
    for (int i = 0; i < 10000; i++) {
      byte[] bytes = new byte[1024 * 1024];
      WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
      map.put(weakReference, value);
    }
    System.out.println("map.size->" + map.size());
  }
}
