package io.github.linlshare.settingstickyrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.github.linlshare.settingstickyrecyclerview.adapter.SettingRecyclerAdapter;
import io.github.linlshare.settingstickyrecyclerview.base.PinnedHeaderItemDecoration;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;

/**
 * Setting Activity
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

    for (int i = 0; i < 15; i++) {
      if (i % 5 == 0) {
        SettingItem header = new SettingItem.Builder("今日头条", true).build();
        settingRecyclerAdapter.add(header);
      }
      SettingItem normal = new SettingItem.Builder("头条1").secondaryText("详情下回分解", false).build();
      settingRecyclerAdapter.add(normal);
    }
  }
}