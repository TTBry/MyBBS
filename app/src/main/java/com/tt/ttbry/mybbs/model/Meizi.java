package com.tt.ttbry.mybbs.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TTBry on 2017/12/5.
 */

public class Meizi {
    @JSONField(name="_id")
    private String id;
    @JSONField(name="createdAt")
    private String createAt;
    @JSONField(name="publishedAt")
    private String publishedAt;
    @JSONField(name="url")
    private String imageUrl;
    @JSONField(name="who")
    private String who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishdAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Meizi){
            Meizi meizi = (Meizi)obj;
            return this.getImageUrl().equals(meizi.getImageUrl());
        }
        return false;
    }
}
