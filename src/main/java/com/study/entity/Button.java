package com.study.entity;

import java.util.ArrayList;
import java.util.List;

//一级菜单对象
public class Button {
    private List<AbstractButton> button;

    public Button() {
        this.button = new ArrayList<AbstractButton>();
    }

    public List<AbstractButton> getButton() {
        return this.button;
    }

    public void setButton(final List<AbstractButton> button) {
        this.button = button;
    }
}
