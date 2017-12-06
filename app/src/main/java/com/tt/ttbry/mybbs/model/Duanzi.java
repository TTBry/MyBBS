package com.tt.ttbry.mybbs.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by TTBry on 2017/12/5.
 */

public class Duanzi {

    @JSONField(name="group")
    private GroupBean groupBean;

    private String type;

    @JSONField(name="comments")
    private List<CommentBean> commentBeans;

    public GroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GroupBean groupBean) {
        this.groupBean = groupBean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommentBeans(List<CommentBean> commentBeans) {
        this.commentBeans = commentBeans;
    }

    public List<CommentBean> getCommentBeans() {
        return commentBeans;
    }
}
