package com.czhappy.commonindexdemo.model;

import java.io.Serializable;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/21 0021
 * Time: 16:24
 */
public class IndexBanner implements Serializable {
    private String banner_id;
    private String banner_title;
    private String banner_url;
    private String link_url;

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
}
