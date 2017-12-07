package com.tt.ttbry.mybbs.model.duanzi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TTBry on 2017/12/5.
 */

public class GroupBean {
    private String text;
    private long id;
    private UserBean user;
    @JSONField(name="has_comments")
    private int hasComments;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public void setHasComments(int hasComments) {
        this.hasComments = hasComments;
    }

    public int getHasComments() {
        return hasComments;
    }

    public UserBean getUser() {
        return user;
    }

    public static class UserBean {

        private long user_id;
        private String name;
        private String avatar_url;

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }
    }
}
