package com.czhappy.commonindexdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.czhappy.commonindexdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/9 0009
 * Time: 17:18
 */
public class SelfFragment extends Fragment {

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
    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_self, container,
                false);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    private void initView() {
        backIv.setVisibility(View.GONE);
        titleTv.setText("我的");
        layoutRight.setVisibility(View.INVISIBLE);
    }

}
