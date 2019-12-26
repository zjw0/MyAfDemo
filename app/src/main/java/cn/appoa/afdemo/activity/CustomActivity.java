package cn.appoa.afdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afdemo.widget.SortView;
import cn.appoa.afdemo.widget.TitleBar;
import cn.appoa.aframework.utils.AtyUtils;

public class CustomActivity extends BaseActivity {

    private TitleBar titlebar;
    private SortView mSortView;

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_custom);

    }


    @Override
    public void initData() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        /*
         * setTitleBarClickListener是在TitleBar定义中的一个方法，它用来接收listener。
         * TitleBarClickListener是在TitleBar中定义的一个接口，
         * 这个接口中有两个为实现的方法rightClick和leftClick。
         * 这里重写了leftClick和rightClick方法。
         * */
        titlebar.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {

            @Override
            public void rightClick() {
                AtyUtils.showShort(mActivity, "right---", false);

            }

            @Override
            public void leftClick() {
                AtyUtils.showShort(mActivity, "left---", false);

            }
        });

        mSortView = findViewById(R.id.sort_view);
//        mSortView.setTittle("综合1", 1);
//        mSortView.setTittle("销量1", 2);
//        mSortView.setTittle("价格1", 3);

        mSortView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtyUtils.showShort(mActivity, "综合", false);
            }
        });
        mSortView.setMiddleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtyUtils.showShort(mActivity, "销量", false);
            }
        });
        mSortView.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtyUtils.showShort(mActivity, "价格", false);
            }
        });

    }
}
