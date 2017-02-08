package com.czhappy.commonindexdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.czhappy.commonindexdemo.R;
import com.czhappy.commonindexdemo.glide.GlideImgManager;
import com.czhappy.commonindexdemo.model.Campaign;
import com.czhappy.commonindexdemo.model.CampaignImage;
import com.czhappy.commonindexdemo.utils.ToastUtil;
import com.czhappy.commonindexdemo.utils.Utils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/14 0014
 * Time: 17:45
 */
public class CampaignListAdapter extends ListBaseAdapter<Campaign> {

    private Context mContext;

    @BindView(R.id.user_head_iv)
    ImageView userHeadIv;
    @BindView(R.id.username_tv)
    TextView usernameTv;
    @BindView(R.id.createtime_tv)
    TextView createtimeTv;
    @BindView(R.id.status_tv)
    TextView statusTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.campaign_time_tv)
    TextView campaignTimeTv;
    @BindView(R.id.prise_iv)
    ImageView priseIv;
    @BindView(R.id.prise_count_tv)
    TextView priseCountTv;
    @BindView(R.id.comment_count_tv)
    TextView commentCountTv;
    @BindView(R.id.nineGrid)
    NineGridView nineGrid;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.layout_address)
    LinearLayout layoutAddress;

    public CampaignListAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.campaign_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ButterKnife.bind(this, holder.itemView);
        final Campaign campaign = mDataList.get(position);

        if(!Utils.isEmpty(campaign.getStart_time()) && Utils.isEmpty(campaign.getEnd_time())){
            campaignTimeTv.setText("自"+Utils.StringDateFormat(campaign.getStart_time(), "yyyy-MM-dd HH:mm") + "起");
        }else{
            campaignTimeTv.setText(Utils.StringDateFormat(campaign.getStart_time(), "yyyy-MM-dd HH:mm") + "至" + Utils.StringDateFormat(campaign.getEnd_time(), "yyyy-MM-dd HH:mm"));
        }

        commentCountTv.setText(campaign.getComment_count());
        createtimeTv.setText(campaign.getCreate_time());
        descTv.setText(campaign.getCampaign_desc());
        priseCountTv.setText(campaign.getPraise_count());
        usernameTv.setText(campaign.getNickname());
        if(!Utils.isEmpty(campaign.getCampaign_address())){
            layoutAddress.setVisibility(View.VISIBLE);
            addressTv.setText(campaign.getCampaign_address());
        }else{
            layoutAddress.setVisibility(View.GONE);
        }

        if ("1".equals(campaign.getIs_praise())) {
            priseIv.setImageResource(R.mipmap.ding_checked_icon);
        } else {
            priseIv.setImageResource(R.mipmap.ding_uncheck_icon);
        }

        String statusStr = Utils.checkTimeStatus(campaign.getStart_time(), campaign.getEnd_time());
        statusTv.setText(statusStr);
        if ("未开始".equals(statusStr)) {
            statusTv.setTextColor(mContext.getResources().getColor(R.color.blue));
        } else if ("进行中".equals(statusStr)) {
            statusTv.setTextColor(mContext.getResources().getColor(R.color.red));
        } else if ("已结束".equals(statusStr)) {
            statusTv.setTextColor(mContext.getResources().getColor(R.color.common_gray9));
        }

        titleTv.setText(campaign.getCampaign_name());

        GlideImgManager.loadCircleImage(mContext, campaign.getHead_img(), userHeadIv);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<CampaignImage> images = campaign.getImg_list();
        if (images != null) {
            for (CampaignImage image : images) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image.getImage_url());
                info.setBigImageUrl(image.getImage_url());
                imageInfo.add(info);
            }
        }
        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));

        if (images != null && images.size() == 1) {
            //nineGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mContext, campaign.getCampaign_name());
            }
        });
    }
}
