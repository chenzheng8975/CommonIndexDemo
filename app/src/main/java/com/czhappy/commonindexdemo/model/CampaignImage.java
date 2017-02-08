package com.czhappy.commonindexdemo.model;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/20 0020
 * Time: 13:58
 */
public class CampaignImage {

    private String campaign_id;
    private String image_id;
    private String image_url;
    private String thumbnail;

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
