package io.github.linlshare.library.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lshare
 * @date 2016/11/9
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public abstract class BaseRecyclerAdapterWithDelegate<T> extends RecyclerView.Adapter {

  private List<T> dataList;
  AdapterDelegatesManager<List<T>> adapterDelegatesManager;

  public BaseRecyclerAdapterWithDelegate() {
    dataList = new ArrayList<>();
    adapterDelegatesManager = new AdapterDelegatesManager<>();
    //addDelegateHere();
  }

  public abstract void addDelegateHere();

  public void addDelegate(AdapterDelegate delegate) {
    adapterDelegatesManager.addDelegate(delegate);
  }

  public void addAll(List<T> dataList) {
    this.dataList.addAll(dataList);
    notifyDataSetChanged();
  }

  public void add(T data) {
    this.dataList.add(data);
    notifyItemInserted(this.dataList.size() - 1);
  }

  public void remove(T data) {
    int index = this.dataList.indexOf(data);
    this.dataList.remove(data);
    notifyItemRemoved(index);
  }

  public void clear() {
    this.dataList.clear();
    notifyDataSetChanged();
  }

  public List<T> getAll() {
    return this.dataList;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return adapterDelegatesManager.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    adapterDelegatesManager.onBindViewHolder(dataList, position, holder);
  }

  @Override public int getItemViewType(int position) {
    return adapterDelegatesManager.getItemViewType(dataList, position);
  }

  @Override public int getItemCount() {
    return dataList.size();
  }
}
