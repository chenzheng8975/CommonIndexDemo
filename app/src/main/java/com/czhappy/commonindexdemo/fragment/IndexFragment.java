package com.czhappy.commonindexdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.czhappy.commonindexdemo.KuaiZhiApplication;
import com.czhappy.commonindexdemo.R;
import com.czhappy.commonindexdemo.adapter.CampaignListAdapter;
import com.czhappy.commonindexdemo.adapter.ClassflyListAdapter;
import com.czhappy.commonindexdemo.jsonparse.JSONObject;
import com.czhappy.commonindexdemo.jsonparse.ReflectUtil;
import com.czhappy.commonindexdemo.model.CampaignList;
import com.czhappy.commonindexdemo.model.Classfly;
import com.czhappy.commonindexdemo.model.IndexBanner;
import com.czhappy.commonindexdemo.model.IndexBannerList;
import com.czhappy.commonindexdemo.okhttp.LoadingDialogCallback;
import com.czhappy.commonindexdemo.utils.AccordionTransformer;
import com.czhappy.commonindexdemo.utils.Api;
import com.czhappy.commonindexdemo.utils.NetworkImageHolderView;
import com.czhappy.commonindexdemo.utils.ToastUtil;
import com.czhappy.commonindexdemo.view.GridViewForScrollView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/9 0009
 * Time: 17:18
 */
public class IndexFragment extends Fragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.layout_right)
    LinearLayout layoutRight;
    @BindView(R.id.campaign_recyclerview)
    LRecyclerView mRecyclerView;
    private View mView;

    private GridViewForScrollView classflyGridview;
    private LinearLayout pointGroup;;
    private ConvenientBanner convenientBanner;
    private TextView bannerTitleTv;;
    private List<String> networkImages = new ArrayList<String>();
    private int lastPosition = 0;
    private CampaignListAdapter campaignListAdapter;
    private ClassflyListAdapter classflyListAdapter;
    private IndexBannerList indexBannerList;
    private CampaignList campaignList;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    public int pageNum = 1;
    public int pageSize = 4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_index, container,
                false);
        ButterKnife.bind(this, mView);
        initView();
        initClassfly();
        return mView;
    }


    private void initView() {
        backIv.setVisibility(View.GONE);
        titleTv.setText("快知网");
        layoutRight.setVisibility(View.INVISIBLE);

        campaignListAdapter = new CampaignListAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(campaignListAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //add a HeaderView
        final View header = LayoutInflater.from(getActivity()).inflate(R.layout.index_header,(ViewGroup)mView.findViewById(android.R.id.content), false);
        classflyGridview = (GridViewForScrollView) header.findViewById(R.id.classfly_gridview);
        classflyGridview.setFocusable(false);
        pointGroup = (LinearLayout) header.findViewById(R.id.point_group);
        convenientBanner = (ConvenientBanner) header.findViewById(R.id.convenientBanner);
        bannerTitleTv = (TextView) header.findViewById(R.id.banner_title_tv);
        mLRecyclerViewAdapter.addHeaderView(header);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                doRequest(pageNum, true);
                requestBannerList();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNum++;
                doRequest(pageNum, false);

            }
        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        mRecyclerView.setRefreshing(true);

//        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//            }
//
//        });
//
//        mLRecyclerViewAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
    }


    private void initClassfly() {
        List<Classfly> list = new ArrayList<Classfly>();
        Classfly c1 = new Classfly("3", "通知公告", R.mipmap.classfly_bg1);
        Classfly c2 = new Classfly("4", "教育学习", R.mipmap.classfly_bg2);
        Classfly c3 = new Classfly("5", "偶像明星", R.mipmap.classfly_bg3);
        Classfly c4 = new Classfly("6", "特价优惠", R.mipmap.classfly_bg4);
        Classfly c5 = new Classfly("7", "线下活动", R.mipmap.classfly_bg5);
        Classfly c6 = new Classfly("8", "其它", R.mipmap.classfly_bg6);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        classflyListAdapter = new ClassflyListAdapter(getActivity());
        classflyGridview.setAdapter(classflyListAdapter);
        classflyListAdapter.setItems(list);
        classflyGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void doRequest(int page, final boolean isRefresh) {
        OkGo.post(Api.GET_CAMPAIGN_LIST)
                .tag(this)
                .params("currentPage", String.valueOf(page))
                .params("pageSize", String.valueOf(pageSize))
                .execute(new LoadingDialogCallback(getActivity()) {


                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if(isRefresh){
                            mRecyclerView.refreshComplete();
                        }else {
                            mRecyclerView.loadMoreComplete();
                        }
                        try {
                            campaignList = (CampaignList) ReflectUtil
                                    .copy(CampaignList.class,
                                            new JSONObject(s));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (campaignList != null) {
                            String result = campaignList.getResult();
                            if (Api.SUCCESS.equals(result)) {
                                int j = campaignList.getData().size();
                                if (isRefresh) {
                                    campaignListAdapter.clear();
                                }
                                if (j != pageSize) {
                                    mRecyclerView.setNoMore(true);
                                }
                                campaignListAdapter.addAll(campaignList.getData());
                            }else{
                                ToastUtil.show(getActivity(), campaignList.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if(isRefresh){
                            mRecyclerView.refreshComplete();
                        }else {
                            mRecyclerView.loadMoreComplete();
                        }
                        KuaiZhiApplication.showResultToast(getActivity(), call, e);
                    }

                });
    }


    private void requestBannerList() {
        OkGo.post(Api.GET_INDEX_BANNER_LIST)
                .tag(this)
                .cacheKey("cache_index_banner_list")
                .execute(new LoadingDialogCallback(getActivity()) {

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        analysisIndexBannerRequest(s);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        analysisIndexBannerRequest(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        KuaiZhiApplication.showResultToast(getActivity(), call, e);
                    }
                });
    }

    private void analysisIndexBannerRequest(String s){
        try {
            indexBannerList = (IndexBannerList) ReflectUtil
                    .copy(IndexBannerList.class,
                            new JSONObject(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (indexBannerList != null) {
            String result = indexBannerList.getResult();
            if (Api.SUCCESS.equals(result)) {
                networkImages.clear();
                for (IndexBanner ib : indexBannerList.getData()) {
                    networkImages.add(ib.getBanner_url());
                }
                setRecommendInfo();
                initBanner();
            }else{
                ToastUtil.show(getActivity(), indexBannerList.getMsg());
            }
        }
    }

    private void initBanner() {

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setOnPageChangeListener(this);//监听翻页事件
        convenientBanner.setPageTransformer(new AccordionTransformer());
        convenientBanner.startTurning(3000);
        convenientBanner.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void setRecommendInfo() {
        pointGroup.removeAllViews();
        for (int i = 0; i < indexBannerList.getData().size(); i++) {
            // 添加指示点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i != indexBannerList.getData().size() - 1) {
                params.rightMargin = 10;
            }
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 改变指示点的状态
        // 把当前点enbale 为true
        if (indexBannerList != null && indexBannerList.getData() != null && indexBannerList.getData().size() > 0) {
            bannerTitleTv.setText(indexBannerList.getData().get(position).getBanner_title());
        }
        pointGroup.getChildAt(position).setEnabled(true);
        // 把上一个点设为false
        pointGroup.getChildAt(lastPosition).setEnabled(false);
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
