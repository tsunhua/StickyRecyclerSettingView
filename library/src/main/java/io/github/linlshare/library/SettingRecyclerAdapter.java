package io.github.linlshare.library;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter for SettingItem
 *
 * @author Lshare
 * @date 2016/11/1
 */
public class SettingRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements
        StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private List<SettingItemModel> settingList = new ArrayList<>();
    // position of Header inserted corresponding to SettingHeaderModel
    private SparseArray<SettingHeaderModel> sparseArray = new SparseArray<>();

    /**
     * add a Setting Group
     *
     * @param header            the only Setting Header
     * @param settingItemModels multiple Setting Item
     */
    public void addGroup(SettingHeaderModel header, SettingItemModel... settingItemModels) {
        sparseArray.put(settingList.size(), header);
        settingList.addAll(Arrays.asList(settingItemModels));
    }

    /*SettingHeaderModel*/
    @Override
    public long getHeaderId(int position) {
        if (sparseArray.get(position) != null) {
            return sparseArray.get(position).getId();
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .list_item_setting_header,
                parent, false);
        return new RecyclerView.ViewHolder(textView) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        if (sparseArray.get(position) != null) {
            textView.setText(sparseArray.get(position).getTitle());
        }
    }

    /*Content*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SettingItemView settingItemView = new SettingItemView(parent.getContext());
        return new RecyclerView.ViewHolder(settingItemView) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SettingItemModel settingItemModel = settingList.get(position);
        final SettingItemView settingItemView = (SettingItemView) holder.itemView;
        settingItemView.setMainTxt(settingItemModel.getMainText());
        if (!TextUtils.isEmpty(settingItemModel.getSecondaryText())) {
            settingItemView.setSecondaryTxt(settingItemModel.getSecondaryText(), settingItemModel
                    .isSecondaryTextHighLight());
        } else {
            settingItemView.setSecondaryTxt("", false);
        }
        /*handle icon*/
        if (settingItemModel.getLeftIconRes() != -1) {
            settingItemView.setLeftIconRes(settingItemModel.getLeftIconRes());
        }
        /*handle right icon*/
        settingItemView.isShowRightIcon(settingItemModel.isShowRightIcon());
        /*handle line*/
        if (sparseArray.get(position + 1) != null) {
            settingItemView.isShowLine(false);
        } else {
            settingItemView.isShowLine(true);
        }
        /*handle switch*/
        settingItemView.isShowSwitch(settingItemModel.isShowSwitch(), settingItemModel
                .isSwitchDefaultOn());
        settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    settingItemView.toggleSwitch();
                    onItemClickListener.onItemClick(position, settingItemModel, settingItemView
                            .isChecked());
                }
            }
        });
        settingItemView.setOncheckChangedListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onItemClickListener.onItemClick(position, settingItemModel, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, SettingItemModel settingItemModel, boolean checked);
    }
}
