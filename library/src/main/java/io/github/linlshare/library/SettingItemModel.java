package io.github.linlshare.library;

import android.support.annotation.DrawableRes;

/**
 * A JavaBean for SettingItem
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingItemModel {
    private
    @DrawableRes
    int leftIconRes = -1; // -1 is the invalid icon res id
    private String mainText;
    private String secondaryText;
    private boolean isSecondaryTextHighLight;
    private boolean isShowSwitch;
    private boolean switchDefaultOn;
    private boolean isShowRightIcon;

    public int getLeftIconRes() {
        return leftIconRes;
    }

    public String getMainText() {
        return mainText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public boolean isSecondaryTextHighLight() {
        return isSecondaryTextHighLight;
    }

    public boolean isShowSwitch() {
        return isShowSwitch;
    }

    public boolean isSwitchDefaultOn() {
        return switchDefaultOn;
    }

    public boolean isShowRightIcon() {
        return isShowRightIcon;
    }

    public SettingItemModel() {
    }

    private SettingItemModel(int leftIconRes, String mainText,
                             String secondaryText, boolean isSecondaryTextHighLight, boolean
                                     isShowSwitch, boolean switchDefaultOn, boolean isShowRightIcon) {
        this.leftIconRes = leftIconRes;
        this.mainText = mainText;
        this.secondaryText = secondaryText;
        this.isSecondaryTextHighLight = isSecondaryTextHighLight;
        this.isShowSwitch = isShowSwitch;
        this.switchDefaultOn = switchDefaultOn;
        this.isShowRightIcon = isShowRightIcon;
    }

    public static class Builder {
        private
        @DrawableRes
        int leftIconRes = -1;
        private String mainText;
        private String secondaryText;
        private boolean isShowSwitch;
        private boolean isSecondaryTextHighLight;
        private boolean isShowRightIcon;
        private boolean switchDefaultOn;

        public Builder(String mainText) {
            this.mainText = mainText;
            isShowRightIcon = true;
            isShowSwitch = false;
        }

        public Builder showSwitch(boolean isOn) {
            this.isShowSwitch = true;
            this.switchDefaultOn = isOn;
            return this;
        }

        public Builder hideRightIcon() {
            this.isShowRightIcon = false;
            return this;
        }

        public Builder leftIconRes(@DrawableRes int leftIconRes) {
            this.leftIconRes = leftIconRes;
            return this;
        }

        public Builder secondaryText(String secondaryText, boolean isSecondaryTextHighLight) {
            this.secondaryText = secondaryText;
            this.isSecondaryTextHighLight = isSecondaryTextHighLight;
            return this;
        }

        public SettingItemModel build() {
            return new SettingItemModel(leftIconRes, mainText, secondaryText,
                    isSecondaryTextHighLight, isShowSwitch, switchDefaultOn, isShowRightIcon);
        }
    }
}
