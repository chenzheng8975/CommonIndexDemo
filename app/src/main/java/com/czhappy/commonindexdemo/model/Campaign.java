package com.czhappy.commonindexdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/14 0014
 * Time: 17:55
 */
public class Campaign implements Serializable {

    private String campaign_id;
    private String campaign_name;
    private String campaign_desc;
    private String campaign_address;
    private String longitude;
    private String latitude;
    private String remark;
    private String dict_id;
    private String start_time;
    private String end_time;
    private String campaign_integral;
    private String status;
    private String is_recommand;
    private String user_id;
    private String create_time;
    private String comment_count;
    private String praise_count;

    private String nickname;
    private String head_img;
    private String dict_name;

    private String is_praise;

    public String getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(String is_praise) {
        this.is_praise = is_praise;
    }

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name;
    }

    private List<CampaignImage> img_list;

    public List<CampaignImage> getImg_list() {
        return img_list;
    }

    public void setImg_list(List<CampaignImage> img_list) {
        this.img_list = img_list;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getHead_img() {
        return head_img;
    }
    public void setHead_img(String headImg) {
        head_img = headImg;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getCampaign_desc() {
        return campaign_desc;
    }

    public void setCampaign_desc(String campaign_desc) {
        this.campaign_desc = campaign_desc;
    }

    public String getCampaign_address() {
        return campaign_address;
    }

    public void setCampaign_address(String campaign_address) {
        this.campaign_address = campaign_address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDict_id() {
        return dict_id;
    }

    public void setDict_id(String dict_id) {
        this.dict_id = dict_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCampaign_integral() {
        return campaign_integral;
    }

    public void setCampaign_integral(String campaign_integral) {
        this.campaign_integral = campaign_integral;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_recommand() {
        return is_recommand;
    }

    public void setIs_recommand(String is_recommand) {
        this.is_recommand = is_recommand;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(String praise_count) {
        this.praise_count = praise_count;
    }
}
