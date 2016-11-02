package io.github.linlshare.library;

import android.support.annotation.DrawableRes;

/**
 * 设置项实体
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingItemModel {
    private
    @DrawableRes
    int leftIconRes = -1;
    private String mainText;
    private String secondaryText;

    private boolean isSecondaryTextHighLight;
    private boolean isShowSwitch;

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

    public SettingItemModel() {
    }

    private SettingItemModel(int leftIconRes, String mainText,
                             String secondaryText, boolean isSecondaryTextHighLight, boolean
                                     isShowSwitch) {
        this.leftIconRes = leftIconRes;
        this.mainText = mainText;
        this.secondaryText = secondaryText;
        this.isSecondaryTextHighLight = isSecondaryTextHighLight;
        this.isShowSwitch = isShowSwitch;
    }

    public static class Builder {
        private
        @DrawableRes
        int leftIconRes = -1;
        private String mainText;
        private String secondaryText;
        private boolean isShowSwitch;
        private boolean isSecondaryTextHighLight;

        public Builder(String mainText, boolean isShowSwitch) {
            this.mainText = mainText;
            this.isShowSwitch = isShowSwitch;
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
                    isSecondaryTextHighLight, isShowSwitch);
        }
    }
}
