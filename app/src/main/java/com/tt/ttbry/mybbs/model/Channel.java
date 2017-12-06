package com.tt.ttbry.mybbs.model;

import java.io.Serializable;

/**
 * Created by TTBry on 2017/12/6.
 */

public class Channel implements Serializable{
    private int channelId;
    private String channelName;
    private String channelAddress;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelAddress() {
        return channelAddress;
    }

    public void setChannelAddress(String channelAddress) {
        this.channelAddress = channelAddress;
    }
}
