package com.study.entity;

//网页类型的菜单
public class ViewButton extends AbstractButton {
    private String type;
    private String url;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public ViewButton(final String name, final String url) {
        super(name);
        this.type = "view";//网页类型
        this.url = url;
    }
}