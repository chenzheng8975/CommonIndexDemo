package com.czhappy.commonindexdemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.czhappy.commonindexdemo.KuaiZhiApplication;
import com.czhappy.commonindexdemo.R;
import com.czhappy.commonindexdemo.fragment.IndexFragment;
import com.czhappy.commonindexdemo.fragment.SelfFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_index)
    ImageView imgIndex;
    @BindView(R.id.txt_index)
    TextView txtIndex;
    @BindView(R.id.index_page)
    LinearLayout indexPage;
    @BindView(R.id.img_self)
    ImageView imgSelf;
    @BindView(R.id.txt_self)
    TextView txtSelf;
    @BindView(R.id.self_page)
    LinearLayout selfPage;
    @BindView(R.id.tab_layout)
    LinearLayout tabLayout;
    @BindView(R.id.content)
    FrameLayout content;
    private long time = 0;

    private FragmentManager fragmentManager;

    private int CURTAB = 0;
    private IndexFragment indexFragment;
    private SelfFragment selfFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setChioceItem(CURTAB);

    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();


    }


    private void show(int position) {
        imgIndex.setImageResource(position == 0 ? R.mipmap.home_green_icon
                : R.mipmap.home_gray_icon);
        txtIndex.setTextColor(getResources().getColor(
                position == 0 ? R.color.colorPrimary : R.color.common_gray8));
        imgSelf.setImageResource(position == 1 ? R.mipmap.self_green_icon
                : R.mipmap.self_gray_icon);
        txtSelf.setTextColor(getResources().getColor(
                position == 1 ? R.color.colorPrimary : R.color.common_gray8));
    }

    /**
     * 选中某个tab
     *
     * @param index
     */
    public void setChioceItem(int index) {
        show(index);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {

            case 0:
//			indexFragment = new IndexFragment();
//			transaction.add(R.id.content, indexFragment);
                if (indexFragment == null) {
                    indexFragment = new IndexFragment();
                    transaction.add(R.id.content, indexFragment);
                } else {
                    transaction.show(indexFragment);
                }
                break;

            case 1:
                if (selfFragment == null) {
                    selfFragment = new SelfFragment();
                    transaction.add(R.id.content, selfFragment);
                } else {
                    transaction.show(selfFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (indexFragment != null) {
            transaction.hide(indexFragment);
        }
        if (selfFragment != null) {
            transaction.hide(selfFragment);
        }
    }


    @Override
    public void onBackPressed() {
        long temp = System.currentTimeMillis();
        if (time == 0 || temp - time > 2000) {
            time = temp;
            Toast.makeText(this, "再按一次退出系统", Toast.LENGTH_LONG).show();
        } else {
            KuaiZhiApplication.getInstance().exit();
        }
    }



    @OnClick({R.id.index_page, R.id.self_page})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_page:
                setChioceItem(0);
                break;
            case R.id.self_page:
                setChioceItem(1);
                break;
        }
    }

}
