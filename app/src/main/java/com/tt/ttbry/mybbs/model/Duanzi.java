package com.tt.ttbry.mybbs.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TTBry on 2017/12/5.
 */

public class Duanzi {

    @JSONField(name="group")
    private GroupBean groupBean;
    private String type;

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
}
