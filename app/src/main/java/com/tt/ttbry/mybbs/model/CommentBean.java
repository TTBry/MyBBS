package com.tt.ttbry.mybbs.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by TTBry on 2017/12/6.
 */

public class CommentBean {
    private String text;
    @JSONField(name="user_name")
    private String userName;
    @JSONField(name="avatar_url")
    private String userImage;
    @JSONField(name="medals")
    private List<MedalBean> medalBeans;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public List<MedalBean> getMedalBeans() {
        return medalBeans;
    }

    public void setMedalBeans(List<MedalBean> medalBeans) {
        this.medalBeans = medalBeans;
    }

    public class MedalBean{
        @JSONField(name="icon_url")
        private String iconUrl;
        private String name;
        @JSONField(name="small_icon_url")
        private String smallIconUrl;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSmallIconUrl() {
            return smallIconUrl;
        }

        public void setSmallIconUrl(String smallIconUrl) {
            this.smallIconUrl = smallIconUrl;
        }
    }

}
