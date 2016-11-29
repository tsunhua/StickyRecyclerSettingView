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
  public SettingRecyclerAdapter() {
    addDelegate(new HeaderAdapterDelegate());
    addDelegate(new NormalAdapterDelegate());
  }

  @Override public boolean isPinnedViewType(int viewType) {
    if (adapterDelegatesManager.getDelegateForViewType(viewType) instanceof HeaderAdapterDelegate) {
      return true;
    }
    return false;
  }
}
