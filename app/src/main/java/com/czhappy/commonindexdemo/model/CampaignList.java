package com.czhappy.commonindexdemo.model;

import java.util.List;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/18 0018
 * Time: 17:53
 */
public class CampaignList {

    private String result;
    private String msg;
    private List<Campaign> data;

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

    public List<Campaign> getData() {
        return data;
    }

    public void setData(List<Campaign> data) {
        this.data = data;
    }
}
