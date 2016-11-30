package io.github.linlshare.settingstickyrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.github.linlshare.settingstickyrecyclerview.adapter.SettingRecyclerAdapter;
import io.github.linlshare.settingstickyrecyclerview.base.PinnedHeaderItemDecoration;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;
import java.util.List;

/**
 * Setting Activity
 * reference:https://github.com/sockeqwe/AdapterDelegates
 * and
 * https://github.com/takahr/pinned-section-item-decoration
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingActivity extends AppCompatActivity
    implements SettingRecyclerAdapter.OnItemClickListener {

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

    settingRecyclerAdapter.setOnItemClickListener(this);

    List<SettingItem> settingItemList = Util.parseSettings(getResources(), R.xml.settings);
    settingRecyclerAdapter.addAll(settingItemList);
  }

  @Override public void onItemClick(int id, SettingItem item) {
    Util.toast(this, item);
    if (id == R.id.name) {
      settingRecyclerAdapter.updateSecondaryText(id, "Here it go");
    }
  }
}