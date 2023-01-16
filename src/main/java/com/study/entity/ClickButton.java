package com.study.entity;

//点击类型的菜单
public class ClickButton extends AbstractButton {
    private String type;
    private String key;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public ClickButton(final String name, final String key) {
        super(name);
        this.type = "click";//点击类型
        this.key = key;
    }
}