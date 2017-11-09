package com.tt.ttbry.mybbs.model;

/**
 * Created by TTBry on 2017/11/8.
 */

public class Article {
    private String title;//标题
    private String thumb;//缩略图
    private String time;//时间
    private String subTitle;//副标题
    private String url;

    public Article(String title, String thumb, String time, String subTitle, String url) {
        this.title = title;
        this.thumb = thumb;
        this.time = time;
        this.subTitle = subTitle;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
