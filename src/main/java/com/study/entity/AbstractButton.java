package com.study.entity;

//所有菜单(按钮)的父类
public abstract class AbstractButton {
    private String name;//按钮标题

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AbstractButton(final String name) {
        this.name = name;
    }
}