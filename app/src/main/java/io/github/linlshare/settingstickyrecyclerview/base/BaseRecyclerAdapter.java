package io.github.linlshare.settingstickyrecyclerview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lshare
 * @date 2016/11/9
 */
public abstract class BaseRecyclerAdapter<T>
    extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

  private List<T> dataList;

  public BaseRecyclerAdapter() {
    dataList = new ArrayList<>();
  }

  public T get(int position) {
    return dataList.get(position);
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

  public abstract int getLayoutResId();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
    return new ViewHolder(parent.getContext(), view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    convert(holder, position);
  }

  public abstract void convert(ViewHolder holder, int position);

  @Override public int getItemCount() {
    return dataList.size();
  }

  public T getData(int position) {
    return dataList.get(position);
  }

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
    public ViewHolder setText(int viewId, String text) {
      TextView tv = getView(viewId);
      tv.setText(text);
      return this;
    }

    /**
     * 设置选中状态
     */
    public ViewHolder setSelected(int viewId, boolean selected) {
      getView(viewId).setSelected(selected);
      return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
      ImageView view = getView(viewId);
      view.setImageResource(resId);
      return this;
    }

    public ViewHolder setImageUrl(int viewId, String url) {
      return setImageUrl(viewId, url, -1, -1);
    }

    public ViewHolder setImageUrl(int viewId, String url, Transformation transformation) {
      return setImageUrl(viewId, url, 0, 0, transformation);
    }

    public ViewHolder setImageUrl(int viewId, String path, int width, int height) {
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
    public ViewHolder setImageUrl(int viewId, String path, int width, int height,
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

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
      ImageView view = getView(viewId);
      view.setImageBitmap(bitmap);
      return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
      ImageView view = getView(viewId);
      view.setImageDrawable(drawable);
      return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
      View view = getView(viewId);
      view.setBackgroundColor(color);
      return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
      View view = getView(viewId);
      view.setBackgroundResource(backgroundRes);
      return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
      TextView view = getView(viewId);
      view.setTextColor(textColor);
      return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
      TextView view = getView(viewId);
      view.setTextColor(mContext.getResources().getColor(textColorRes));
      return this;
    }

    @SuppressLint("NewApi") public ViewHolder setAlpha(int viewId, float value) {
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

    public ViewHolder setVisible(int viewId, boolean visible) {
      View view = getView(viewId);
      view.setVisibility(visible ? View.VISIBLE : View.GONE);
      return this;
    }

    public ViewHolder linkify(int viewId) {
      TextView view = getView(viewId);
      Linkify.addLinks(view, Linkify.ALL);
      return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
      for (int viewId : viewIds) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
      }
      return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
      ProgressBar view = getView(viewId);
      view.setProgress(progress);
      return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
      ProgressBar view = getView(viewId);
      view.setMax(max);
      view.setProgress(progress);
      return this;
    }

    public ViewHolder setMax(int viewId, int max) {
      ProgressBar view = getView(viewId);
      view.setMax(max);
      return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
      RatingBar view = getView(viewId);
      view.setRating(rating);
      return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max) {
      RatingBar view = getView(viewId);
      view.setMax(max);
      view.setRating(rating);
      return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
      View view = getView(viewId);
      view.setTag(tag);
      return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
      View view = getView(viewId);
      view.setTag(key, tag);
      return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
      Checkable view = (Checkable) getView(viewId);
      view.setChecked(checked);
      return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
      View view = getView(viewId);
      view.setOnClickListener(listener);
      return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
      View view = getView(viewId);
      view.setOnTouchListener(listener);
      return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
      View view = getView(viewId);
      view.setOnLongClickListener(listener);
      return this;
    }
  }
}
