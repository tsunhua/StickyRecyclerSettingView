/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.github.linlshare.settingstickyrecyclerview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import io.github.linlshare.settingstickyrecyclerview.R;
import java.util.List;

/**
 * This delegate provide method to hook in this delegate to {@link RecyclerView.Adapter} lifecycle.
 * This "hook in" mechanism is provided by {@link AdapterDelegatesManager} and that is the
 * component
 * you have to use.
 *
 * @param <T> The type of the data source
 * @author Hannes Dorfmann
 * @since 1.0
 */
public abstract class AdapterDelegate<T> {

  /**
   * Called to determine whether this AdapterDelegate is the responsible for the given data
   * element.
   *
   * @param items The data source of the Adapter
   * @param position The position in the datasource
   * @return true, if this item is responsible,  otherwise false
   */
  public abstract boolean isForViewType(@NonNull T items, int position);

  /**
   * Creates the  {@link RecyclerView.ViewHolder} for the given data source item
   *
   * @param parent The ViewGroup parent of the given datasource
   * @return The new instantiated {@link RecyclerView.ViewHolder}
   */
  //@NonNull abstract protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

  /**
   * Called to bind the {@link RecyclerView.ViewHolder} to the item of the datas source set
   *
   * @param items The data source
   * @param position The position in the datasource
   * @param holder The {@link RecyclerView.ViewHolder} to bind
   * @param payloads A non-null list of merged payloads. Can be empty list if requires full update.
   */
  //protected abstract void onBindViewHolder(@NonNull T items, int position,
  //    @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads);

  /**
   * Called when a view created by this adapter has been recycled.
   *
   * <p>A view is recycled when a {@link RecyclerView.LayoutManager} decides that it no longer
   * needs to be attached to its parent {@link RecyclerView}. This can be because it has
   * fallen out of visibility or a set of cached views represented by views still
   * attached to the parent RecyclerView. If an item view has large or expensive data
   * bound to it such as large bitmaps, this may be a good place to release those
   * resources.</p>
   * <p>
   * RecyclerView calls this method right before clearing ViewHolder's internal data and
   * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
   * before being recycled, you can call {@link RecyclerView.ViewHolder#getAdapterPosition()} to
   * get
   * its adapter position.
   *
   * @param viewHolder The ViewHolder for the view being recycled
   */
  protected void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
  }

  /**
   * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
   * due to its transient state. Upon receiving this callback, Adapter can clear the
   * animation(s) that effect the View's transient state and return <code>true</code> so that
   * the View can be recycled. Keep in mind that the View in question is already removed from
   * the RecyclerView.
   * <p>
   * In some cases, it is acceptable to recycle a View although it has transient state. Most
   * of the time, this is a case where the transient state will be cleared in
   * {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)} call when View is
   * rebound to a new position.
   * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
   * value of this method to decide whether the View should be recycled or not.
   * <p>
   * Note that when all animations are created by {@link RecyclerView.ItemAnimator}, you
   * should never receive this callback because RecyclerView keeps those Views as children
   * until their animations are complete. This callback is useful when children of the item
   * views create animations which may not be easy to implement using an {@link
   * RecyclerView.ItemAnimator}.
   * <p>
   * You should <em>never</em> fix this issue by calling
   * <code>holder.itemView.setHasTransientState(false);</code> unless you've previously called
   * <code>holder.itemView.setHasTransientState(true);</code>. Each
   * <code>View.setHasTransientState(true)</code> call must be matched by a
   * <code>View.setHasTransientState(false)</code> call, otherwise, the state of the View
   * may become inconsistent. You should always prefer to end or cancel animations that are
   * triggering the transient state instead of handling it manually.
   *
   * @param holder The ViewHolder containing the View that could not be recycled due to its
   * transient state.
   * @return True if the View should be recycled, false otherwise. Note that if this method
   * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
   * the View and recycle it regardless. If this method returns <code>false</code>,
   * RecyclerView will check the View's transient state again before giving a final decision.
   * Default implementation returns false.
   */
  protected boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
    return false;
  }

  /**
   * Called when a view created by this adapter has been attached to a window.
   *
   * <p>This can be used as a reasonable signal that the view is about to be seen
   * by the user. If the adapter previously freed any resources in
   * {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)
   * onViewDetachedFromWindow}
   * those resources should be restored here.</p>
   *
   * @param holder Holder of the view being attached
   */
  protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
  }

  /**
   * Called when a view created by this adapter has been detached from its window.
   *
   * <p>Becoming detached from the window is not necessarily a permanent condition;
   * the consumer of an Adapter's views may choose to cache views offscreen while they
   * are not visible, attaching an detaching them as appropriate.</p>
   *
   * @param holder Holder of the view being detached
   */
  protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
  }

  public abstract int getLayoutResId();

  public AdapterDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
    return new AdapterDelegate.ViewHolder(parent.getContext(), view);
  }

  protected void onBindViewHolder(@NonNull T items, int position,
      @NonNull AdapterDelegate.ViewHolder holder, @NonNull List<Object> payloads) {
    convert(items, holder, position);
  }

  public abstract void convert(T items, ViewHolder holder, int position);

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView) {
      super(itemView);
      mContext = context;
      mConvertView = itemView;
      mViews = new SparseArray<View>();
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
      View view = mViews.get(viewId);
      if (view == null) {
        view = mConvertView.findViewById(viewId);
        mViews.put(viewId, view);
      }
      return (T) view;
    }

    public View getConvertView() {
      return mConvertView;
    }

    /****以下为辅助方法*****/

    /**
     * 设置TextView的值
     */
    public AdapterDelegate.ViewHolder setText(int viewId, String text) {
      TextView tv = getView(viewId);
      tv.setText(text);
      return this;
    }

    public AdapterDelegate.ViewHolder setImageResource(int viewId, int resId) {
      ImageView view = getView(viewId);
      view.setImageResource(resId);
      return this;
    }

    public AdapterDelegate.ViewHolder setImageUrl(int viewId, String url) {
      ImageView view = getView(viewId);
      Picasso.with(mContext).load(url).placeholder(R.drawable.ic_image_holder).into(view);
      return this;
    }

    public AdapterDelegate.ViewHolder setImageUrl(int viewId, String url,
        Transformation transformation) {
      return setImageUrl(viewId, url, 0, 0, transformation);
    }

    public AdapterDelegate.ViewHolder setImageUrl(int viewId, String path, int width, int height) {
      return setImageUrl(viewId, path, width, height, null);
    }

    /**
     * 设置图片path，可以是网络或本地的，本地的使用file:///开头，网络的使用http://开头
     *
     * @param viewId ImageView ID
     * @param path 网络或本地的路径
     * @param width 宽度，当设置为0时自适应
     * @param height 高度，当设置为0时自适应
     * <br/>Warning:当高度和宽度同时设置为等于或小于0时失效
     */
    public AdapterDelegate.ViewHolder setImageUrl(int viewId, String path, int width, int height,
        Transformation transformation) {
      final ImageView view = getView(viewId);
      RequestCreator requestCreator = Picasso.with(mContext)
          .load(path)
          .placeholder(R.drawable.ic_image_holder)
          .config(Bitmap.Config.RGB_565)
          .tag(viewId + "");
      if (!(width < 0 && height < 0)) { //height 和width不同时小于零
        requestCreator.resize(width, height);
      }

      if (transformation != null) {
        requestCreator.transform(transformation);
      }

      requestCreator.into(view);
      return this;
    }

    public AdapterDelegate.ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
      ImageView view = getView(viewId);
      view.setImageBitmap(bitmap);
      return this;
    }

    public AdapterDelegate.ViewHolder setImageDrawable(int viewId, Drawable drawable) {
      ImageView view = getView(viewId);
      view.setImageDrawable(drawable);
      return this;
    }

    public AdapterDelegate.ViewHolder setBackgroundColor(int viewId, int color) {
      View view = getView(viewId);
      view.setBackgroundColor(color);
      return this;
    }

    public AdapterDelegate.ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
      View view = getView(viewId);
      view.setBackgroundResource(backgroundRes);
      return this;
    }

    public AdapterDelegate.ViewHolder setTextColor(int viewId, int textColor) {
      TextView view = getView(viewId);
      view.setTextColor(textColor);
      return this;
    }

    public AdapterDelegate.ViewHolder setTextColorRes(int viewId, int textColorRes) {
      TextView view = getView(viewId);
      view.setTextColor(mContext.getResources().getColor(textColorRes));
      return this;
    }

    @SuppressLint("NewApi") public AdapterDelegate.ViewHolder setAlpha(int viewId, float value) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        getView(viewId).setAlpha(value);
      } else {
        // Pre-honeycomb hack to set Alpha value
        AlphaAnimation alpha = new AlphaAnimation(value, value);
        alpha.setDuration(0);
        alpha.setFillAfter(true);
        getView(viewId).startAnimation(alpha);
      }
      return this;
    }

    public AdapterDelegate.ViewHolder setVisible(int viewId, boolean visible) {
      View view = getView(viewId);
      view.setVisibility(visible ? View.VISIBLE : View.GONE);
      return this;
    }

    public AdapterDelegate.ViewHolder linkify(int viewId) {
      TextView view = getView(viewId);
      Linkify.addLinks(view, Linkify.ALL);
      return this;
    }

    public AdapterDelegate.ViewHolder setTypeface(Typeface typeface, int... viewIds) {
      for (int viewId : viewIds) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
      }
      return this;
    }

    public AdapterDelegate.ViewHolder setProgress(int viewId, int progress) {
      ProgressBar view = getView(viewId);
      view.setProgress(progress);
      return this;
    }

    public AdapterDelegate.ViewHolder setProgress(int viewId, int progress, int max) {
      ProgressBar view = getView(viewId);
      view.setMax(max);
      view.setProgress(progress);
      return this;
    }

    public AdapterDelegate.ViewHolder setMax(int viewId, int max) {
      ProgressBar view = getView(viewId);
      view.setMax(max);
      return this;
    }

    public AdapterDelegate.ViewHolder setRating(int viewId, float rating) {
      RatingBar view = getView(viewId);
      view.setRating(rating);
      return this;
    }

    public AdapterDelegate.ViewHolder setRating(int viewId, float rating, int max) {
      RatingBar view = getView(viewId);
      view.setMax(max);
      view.setRating(rating);
      return this;
    }

    public AdapterDelegate.ViewHolder setTag(int viewId, Object tag) {
      View view = getView(viewId);
      view.setTag(tag);
      return this;
    }

    public AdapterDelegate.ViewHolder setTag(int viewId, int key, Object tag) {
      View view = getView(viewId);
      view.setTag(key, tag);
      return this;
    }

    public AdapterDelegate.ViewHolder setChecked(int viewId, boolean checked) {
      Checkable view = (Checkable) getView(viewId);
      view.setChecked(checked);
      return this;
    }

    /**
     * 关于事件的
     */
    public AdapterDelegate.ViewHolder setOnClickListener(int viewId,
        View.OnClickListener listener) {
      View view = getView(viewId);
      view.setOnClickListener(listener);
      return this;
    }

    public AdapterDelegate.ViewHolder setOnTouchListener(int viewId,
        View.OnTouchListener listener) {
      View view = getView(viewId);
      view.setOnTouchListener(listener);
      return this;
    }

    public AdapterDelegate.ViewHolder setOnLongClickListener(int viewId,
        View.OnLongClickListener listener) {
      View view = getView(viewId);
      view.setOnLongClickListener(listener);
      return this;
    }
  }
}