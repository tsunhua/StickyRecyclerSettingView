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
                new SettingItemModel.Builder("ID")
                        .secondaryText("2333333", false)
                        .build(),
                new SettingItemModel.Builder("Name")
                        .secondaryText("Lshare", false)
                        .build(),
                new SettingItemModel.Builder("Sex")
                        .secondaryText("Male", false)
                        .build(),
                new SettingItemModel.Builder("City")
                        .secondaryText("Guangzhou Guangdong", false)
                        .build(),
                new SettingItemModel.Builder("State")
                        .secondaryText("Coding", false)
                        .build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(102, "Function Setting"),
                new SettingItemModel.Builder("Gift Exchange")
                        .secondaryText("New", true)
                        .build(),
                new SettingItemModel.Builder("Live Story Setting").build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(104, "Share Setting"),
                new SettingItemModel.Builder("QQ")
                        .leftIconRes(R.drawable.ic_qzone)
                        .showSwitch(true)
                        .hideRightIcon()
                        .build(),
                new SettingItemModel.Builder("WeChat")
                        .leftIconRes(R.drawable.ic_wechat)
                        .showSwitch(true)
                        .hideRightIcon()
                        .build(),
                new SettingItemModel.Builder("Sina")
                        .leftIconRes(R.drawable.ic_sina)
                        .showSwitch(false)
                        .hideRightIcon()
                        .build()
        );
        settingRecyclerAdapter.addGroup(
                new SettingHeaderModel(105, "Other Setting"),
                new SettingItemModel.Builder("Feedback").build(),
                new SettingItemModel.Builder("About Us").build(),
                new SettingItemModel.Builder("Check Update").build(),
                new SettingItemModel.Builder("Exit").hideRightIcon().build()
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
        Toast.makeText(this, "position:" + position + "\nswitch check state: " + isChecked, Toast
                .LENGTH_SHORT).show();
    }
}