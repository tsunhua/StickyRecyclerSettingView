package io.github.linlshare.settingstickyrecyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.support.annotation.XmlRes;
import android.widget.Toast;
import com.google.gson.Gson;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author Lshare
 * @date 2016/11/29
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public class Util {

  private static Gson singleGson;

  static {
    singleGson = new Gson();
  }

  public static String toString(Object object) {
    if (object == null) {
      return "";
    }
    String json = singleGson.toJson(object);
    return json;
  }

  public static void toast(Context context, Object object) {
    Toast.makeText(context, "" + toString(object), Toast.LENGTH_SHORT).show();
  }

  /**
   * get resource id from resName and Class Type
   */
  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * parse xml to a SettingItem List
   */
  public static List<SettingItem> parseSettings(Resources resources, @XmlRes int xmlRes) {
    XmlResourceParser xmlResourceParser = resources.getXml(xmlRes);
    List<SettingItem> settingItemList = null;
    SettingItem.Builder settingItemBuilder = null;
    try {
      int eventType = xmlResourceParser.getEventType();
      while (eventType != XmlPullParser.END_DOCUMENT) {
        switch (eventType) {
          case XmlPullParser.START_DOCUMENT:
            settingItemList = new ArrayList<>();
            break;
          case XmlPullParser.START_TAG:
            String name = xmlResourceParser.getName();
            String mainTxt = xmlResourceParser.getAttributeValue(null, "name");
            int id = xmlResourceParser.getAttributeResourceValue(
                "http://schemas.android.com/apk/res/android", "id", 0);
            if (name.equals("header")) {
              settingItemBuilder = new SettingItem.Builder(id, mainTxt, true);
            } else if (name.equals("item")) {
              settingItemBuilder = new SettingItem.Builder(id, mainTxt);
              String secondaryTxt = xmlResourceParser.getAttributeValue(null, "secondaryTxt");
              boolean hightLight =
                  xmlResourceParser.getAttributeBooleanValue(null, "hightLight", false);
              settingItemBuilder.secondaryText(secondaryTxt, hightLight);
              boolean showRightIcon =
                  xmlResourceParser.getAttributeBooleanValue(null, "showRightIcon", true);
              if (!showRightIcon) {
                settingItemBuilder.hideRightIcon();
              }
              String showSwitch = xmlResourceParser.getAttributeValue(null, "showSwitch");
              if (showSwitch != null) {
                settingItemBuilder.showSwitch(showSwitch.equals("on"));
              }
              String leftIconRes = xmlResourceParser.getAttributeValue(null, "leftIconRes");
              settingItemBuilder.leftIconRes(Util.getResId(leftIconRes, Drawable.class));
            }
            break;
          case XmlPullParser.END_TAG:
            if (xmlResourceParser.getName().equals("header") || xmlResourceParser.getName()
                .equals("item")) {
              settingItemList.add(settingItemBuilder.build());
            }
            break;
        }
        eventType = xmlResourceParser.next();
      }
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return settingItemList;
  }
}
