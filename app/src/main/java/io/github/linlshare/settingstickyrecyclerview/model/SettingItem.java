package io.github.linlshare.settingstickyrecyclerview.model;

import android.support.annotation.DrawableRes;

/**
 * A JavaBean for SettingItem
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingItem {

  private @DrawableRes int leftIconRes = -1; // -1 is the invalid icon res id
  private String mainText;
  private String secondaryText;
  private boolean isSecondaryTextHighLight;
  private boolean isShowSwitch;
  private boolean switchDefaultOn;
  private boolean isShowRightIcon;
  private boolean isHeader;

  public SettingItem() {
  }

  private SettingItem(int leftIconRes, String mainText, String secondaryText,
      boolean isSecondaryTextHighLight, boolean isShowSwitch, boolean switchDefaultOn,
      boolean isShowRightIcon, boolean isHeader) {
    this.leftIconRes = leftIconRes;
    this.mainText = mainText;
    this.secondaryText = secondaryText;
    this.isSecondaryTextHighLight = isSecondaryTextHighLight;
    this.isShowSwitch = isShowSwitch;
    this.switchDefaultOn = switchDefaultOn;
    this.isShowRightIcon = isShowRightIcon;
    this.isHeader = isHeader;
  }

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

  public boolean isHeader() {
    return isHeader;
  }

  public void setHeader(boolean header) {
    isHeader = header;
  }

  public static class Builder {
    private @DrawableRes int leftIconRes = -1;
    private String mainText;
    private String secondaryText;
    private boolean isShowSwitch;
    private boolean isSecondaryTextHighLight;
    private boolean isShowRightIcon;
    private boolean switchDefaultOn;
    private boolean isHeader;

    public Builder(String mainText) {
      this(mainText, false);
    }

    public Builder(String mainText, boolean isHeader) {
      this.mainText = mainText;
      this.isHeader = isHeader;
      isShowRightIcon = !isHeader;
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

    public SettingItem build() {
      return new SettingItem(leftIconRes, mainText, secondaryText, isSecondaryTextHighLight,
          isShowSwitch, switchDefaultOn, isShowRightIcon, isHeader);
    }
  }
}
