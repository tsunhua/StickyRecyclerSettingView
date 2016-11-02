package io.github.linlshare.library;

/**
 * 设置头/标题实体
 *
 * @author Lshare
 * @date 2016/11/2
 */
public class SettingHeaderModel {
    private int id;
    private String title;

    public SettingHeaderModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
