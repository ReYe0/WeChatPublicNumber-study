package com.study.entity;

//拍照或传图菜单
public class PhotoOrAlbumButton extends AbstractButton{
    private String type;
    private String key;

    public PhotoOrAlbumButton(String name,String key) {
        super(name);
        this.type = "pic_photo_or_album";//拍照获取传图
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}