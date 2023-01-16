package com.study.entity;

import java.util.ArrayList;
import java.util.List;

//二级菜单对象
public class SubButton extends AbstractButton {
    private List<AbstractButton> sub_button;

    public List<AbstractButton> getSub_button() {
        return this.sub_button;
    }

    public void setSub_button(final List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(final String name) {
        super(name);
        this.sub_button = new ArrayList<AbstractButton>();
    }
}