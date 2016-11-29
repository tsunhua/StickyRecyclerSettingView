package io.github.linlshare.settingstickyrecyclerview;

import java.lang.reflect.Field;

/**
 * @author Lshare
 * @date 2016/11/29
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public class Util {

  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
