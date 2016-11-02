package io.github.linlshare.settingstickyrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import io.github.linlshare.library.SettingHeaderModel;
import io.github.linlshare.library.SettingItemModel;
import io.github.linlshare.library.SettingRecyclerAdapter;

/**
 * Setting Activity
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingActivity extends AppCompatActivity implements SettingRecyclerAdapter
        .OnItemClickListener {

    RecyclerView recyclerView;
    SettingRecyclerAdapter settingRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
        settingRecyclerAdapter = new SettingRecyclerAdapter();

        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(101, "Normal Setting"),
                new SettingItemModel.Builder("ID", false).secondaryText("2333333", false).build(),
                new SettingItemModel.Builder("Name", false).secondaryText("Lshare", false).build(),
                new SettingItemModel.Builder("Sex", false).secondaryText("Male", false).build(),
                new SettingItemModel.Builder("City", false).secondaryText("Guangzhou Guangdong",
                        false).build(),
                new SettingItemModel.Builder("State", false).secondaryText("Coding", false).build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(102, "Function Setting"),
                new SettingItemModel.Builder("Gift Exchange", false).build(),
                new SettingItemModel.Builder("Live Story Setting", false).build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(104, "Share Setting"),
                new SettingItemModel.Builder("QQ", true).leftIconRes(R.drawable.ic_qzone).build(),
                new SettingItemModel.Builder("WeChat", true).leftIconRes(R.drawable.ic_wechat)
                        .build(),
                new SettingItemModel.Builder("Sina", true).leftIconRes(R.drawable.ic_sina).build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(105, "Other Setting"),
                new SettingItemModel.Builder("Feedback", false).build(),
                new SettingItemModel.Builder("About Us", false).build(),
                new SettingItemModel.Builder("Check Update", false).build(),
                new SettingItemModel.Builder("Exit", false).build()
        );
        settingRecyclerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(settingRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration
                (settingRecyclerAdapter);
        recyclerView.addItemDecoration(headersDecor);
    }

    @Override
    public void onItemClick(int position, SettingItemModel settingItemModel, boolean isChecked) {
        Toast.makeText(this, "position:" + position + "isChecked: " + isChecked, Toast
                .LENGTH_SHORT).show();
    }
}