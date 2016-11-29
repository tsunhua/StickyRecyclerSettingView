package io.github.linlshare.settingstickyrecyclerview;

import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.github.linlshare.settingstickyrecyclerview.adapter.SettingRecyclerAdapter;
import io.github.linlshare.settingstickyrecyclerview.base.PinnedHeaderItemDecoration;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Setting Activity
 * reference:https://github.com/sockeqwe/AdapterDelegates
 * and
 * https://github.com/takahr/pinned-section-item-decoration
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  SettingRecyclerAdapter settingRecyclerAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);
    init();
  }

  private void init() {
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        SettingActivity.this.finish();
      }
    });
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    settingRecyclerAdapter = new SettingRecyclerAdapter();
    recyclerView.setAdapter(settingRecyclerAdapter);
    recyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
    loadData();
  }

  private void loadData() {
    XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.settings);
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
            if (name.equals("header")) {
              settingItemBuilder = new SettingItem.Builder(mainTxt, true);
            } else if (name.equals("item")) {
              settingItemBuilder = new SettingItem.Builder(mainTxt);
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

    settingRecyclerAdapter.addAll(settingItemList);
  }
}