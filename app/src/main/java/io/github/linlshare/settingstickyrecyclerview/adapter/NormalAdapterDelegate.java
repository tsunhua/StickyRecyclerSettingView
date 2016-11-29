package io.github.linlshare.settingstickyrecyclerview.adapter;

import android.support.annotation.NonNull;
import io.github.linlshare.settingstickyrecyclerview.R;
import io.github.linlshare.settingstickyrecyclerview.base.AdapterDelegate;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;
import java.util.List;

/**
 * @author Lshare
 * @date 2016/11/29
 */
public class NormalAdapterDelegate extends AdapterDelegate<List<SettingItem>> {
  @Override public boolean isForViewType(@NonNull List<SettingItem> items, int position) {
    return !items.get(position).isHeader();
  }

  @Override public int getLayoutResId() {
    return R.layout.list_item_setting_normal;
  }

  @Override public void convert(List<SettingItem> items, ViewHolder holder, int position) {
    final SettingItem settingItem = items.get(position);
    holder.setText(R.id.main_txt, settingItem.getMainText());
    if (settingItem.getSecondaryText() != null) {
      holder.setText(R.id.secondary_txt, settingItem.getSecondaryText());
    }
  }
}
