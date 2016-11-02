package io.github.linlshare.library;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Layout for SettingItem
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingItemView extends RelativeLayout {

    TextView mainTxt;
    TextView secondaryTxt;
    SwitchCompat mySwitch;
    ImageView rightImg;
    View lineView;
    ImageView leftImg;

    private int highLightColor = 0xffda595c;
    private int secondaryTextColor = 0x66000000;
    private int mainTextColor = 0xcc000000;

    public SettingItemView(Context context) {
        super(context);
        init(context);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.layout_setting_item, this);
        mainTxt = (TextView) findViewById(R.id.main_txt);
        secondaryTxt = (TextView) findViewById(R.id.secondary_txt);
        mySwitch = (SwitchCompat) findViewById(R.id.my_switch);
        lineView = findViewById(R.id.line_view);
        rightImg = (ImageView) findViewById(R.id.next_img);
        leftImg = (ImageView) findViewById(R.id.left_img);
        secondaryTxt.setVisibility(GONE);
        leftImg.setVisibility(GONE);
        mySwitch.setVisibility(GONE);
    }

    public void setMainTxt(String str) {
        mainTxt.setText(str);
        mainTxt.setTextColor(mainTextColor);
    }

    public void setSecondaryTxt(String str, boolean isHighLight) {
        secondaryTxt.setVisibility(VISIBLE);
        secondaryTxt.setText(str);
        if (isHighLight) {
            secondaryTxt.setTextColor(highLightColor);
        } else {
            secondaryTxt.setTextColor(secondaryTextColor);
        }
    }

    public void setLeftIconRes(@DrawableRes int leftIconRes) {
        leftImg.setVisibility(VISIBLE);
        leftImg.setImageResource(leftIconRes);
    }

    public void isShowRightIcon(boolean isShowRightIcon) {
        if (isShowRightIcon) {
            rightImg.setVisibility(VISIBLE);
        } else {
            rightImg.setVisibility(INVISIBLE);
        }
    }

    public void isShowSwitch(boolean isShowSwitch, boolean defaultValue) {
        if (isShowSwitch) {
            mySwitch.setVisibility(VISIBLE);
            mySwitch.setChecked(defaultValue);
        } else {
            mySwitch.setVisibility(GONE);
        }
    }

    public void isShowLine(boolean isShowLine) {
        if (isShowLine) {
            lineView.setVisibility(VISIBLE);
        } else {
            lineView.setVisibility(GONE);
        }
    }

    public void toggleSwitch() {
        if (mySwitch.getVisibility() == VISIBLE) {
            mySwitch.toggle();
        }
    }

    public boolean isChecked() {
        return mySwitch.isChecked();
    }

    public void setOncheckChangedListener(CompoundButton.OnCheckedChangeListener listener) {
        mySwitch.setOnCheckedChangeListener(listener);
    }
}
