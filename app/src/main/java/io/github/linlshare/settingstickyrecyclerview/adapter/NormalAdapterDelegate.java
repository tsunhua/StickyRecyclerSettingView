package io.github.linlshare.settingstickyrecyclerview.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import io.github.linlshare.settingstickyrecyclerview.R;
import io.github.linlshare.settingstickyrecyclerview.base.AdapterDelegate;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;
import java.util.List;

/**
 * @author Lshare
 * @date 2016/11/29
 */
public class NormalAdapterDelegate extends AdapterDelegate<List<SettingItem>> {
  private int highLightColor = 0xffda595c;
  private int secondaryTextColor = 0x66000000;
  //private int mainTextColor = 0xcc000000;
  private OnNormalItemClickListener onNormalItemClickListener;

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
      holder.setVisible(R.id.secondary_txt, true);
      holder.setText(R.id.secondary_txt, settingItem.getSecondaryText());
    } else {
      holder.setVisible(R.id.secondary_txt, false);
    }
    if (settingItem.getLeftIconRes() != -1) {
      holder.setVisible(R.id.left_img, true);
      holder.setImageResource(R.id.left_img, settingItem.getLeftIconRes());
    } else {
      holder.setVisible(R.id.left_img, false);
    }
    if (settingItem.isSecondaryTextHighLight()) {
      holder.setTextColor(R.id.secondary_txt, highLightColor);
    } else {
      holder.setTextColor(R.id.secondary_txt, secondaryTextColor);
    }
    if (settingItem.isShowRightIcon()) {
      holder.setVisible(R.id.next_img, true);
    } else {
      holder.setVisible(R.id.next_img, false);
    }
    if (settingItem.isShowSwitch()) {
      holder.setVisible(R.id.my_switch, true);
    } else {
      holder.setVisible(R.id.my_switch, false);
    }

    // click event
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onNormalItemClickListener != null) {
          onNormalItemClickListener.onItemClick(settingItem.getId(), settingItem);
        }
      }
    });
  }

  public void setOnNormalItemClickListener(OnNormalItemClickListener listener) {
    this.onNormalItemClickListener = listener;
  }

  public interface OnNormalItemClickListener {
    void onItemClick(int id, SettingItem item);
  }
}
