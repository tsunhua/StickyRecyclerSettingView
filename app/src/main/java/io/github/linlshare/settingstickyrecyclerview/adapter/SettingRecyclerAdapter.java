package io.github.linlshare.settingstickyrecyclerview.adapter;

import io.github.linlshare.settingstickyrecyclerview.base.BaseRecyclerAdapterWithDelegate;
import io.github.linlshare.settingstickyrecyclerview.base.PinnedHeaderItemDecoration;
import io.github.linlshare.settingstickyrecyclerview.model.SettingItem;

/**
 * @author Lshare
 * @date 2016/11/29
 */
public class SettingRecyclerAdapter extends BaseRecyclerAdapterWithDelegate<SettingItem>
    implements PinnedHeaderItemDecoration.PinnedHeaderAdapter {
  private OnItemClickListener onItemClickListener;

  public SettingRecyclerAdapter() {
    HeaderAdapterDelegate headerAdapterDelegate = new HeaderAdapterDelegate();
    NormalAdapterDelegate normalAdapterDelegate = new NormalAdapterDelegate();
    addDelegate(headerAdapterDelegate);
    addDelegate(normalAdapterDelegate);
    headerAdapterDelegate.setOnHeaderItemClickListener(
        new HeaderAdapterDelegate.OnHeaderItemClickListener() {
          @Override public void onHeaderItemClick(int id, SettingItem item) {
            if (onItemClickListener != null) {
              onItemClickListener.onItemClick(id, item);
            }
          }
        });
    normalAdapterDelegate.setOnNormalItemClickListener(
        new NormalAdapterDelegate.OnNormalItemClickListener() {
          @Override public void onItemClick(int id, SettingItem item) {
            if (onItemClickListener != null) {
              onItemClickListener.onItemClick(id, item);
            }
          }
        });
  }

  public SettingItem getSettingItemById(int id) {
    for (SettingItem st : getAll()) {
      if (st.getId() == id) {
        return st;
      }
    }
    return null;
  }

  public int getPositionById(int id) {
    for (int i = 0; i < getAll().size(); i++) {
      if (get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }

  public void updateSecondaryText(int id, String secondaryText) {
    if (secondaryText == null) {
      secondaryText = "";
    }
    for (int i = 0; i < getAll().size(); i++) {
      SettingItem settingItem = get(i);
      if (settingItem.getId() == id) {
        settingItem.setSecondaryText(secondaryText);
        notifyItemChanged(i);
      }
    }
  }

  @Override public boolean isPinnedViewType(int viewType) {
    if (adapterDelegatesManager.getDelegateForViewType(viewType) instanceof HeaderAdapterDelegate) {
      return true;
    }
    return false;
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(int id, SettingItem item);
  }
}
