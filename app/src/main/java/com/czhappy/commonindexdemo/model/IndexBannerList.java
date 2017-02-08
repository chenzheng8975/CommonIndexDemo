package com.czhappy.commonindexdemo.model;

import java.util.List;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/21 0021
 * Time: 16:27
 */
public class IndexBannerList {

    private String result;
    private String msg;
    private List<IndexBanner> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<IndexBanner> getData() {
        return data;
    }

    public void setData(List<IndexBanner> data) {
        this.data = data;
    }
}
