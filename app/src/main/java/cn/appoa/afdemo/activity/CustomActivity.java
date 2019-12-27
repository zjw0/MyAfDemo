package cn.appoa.afdemo.activity;

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

        //自定义排序控件
        mSortView = findViewById(R.id.sort_view);
        mSortView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSortView.setTitle("综合1", 1);
                mSortView.setTitleColor(R.color.colorAccent, 1);
                mSortView.setTitleDrawable(0, 1);
            }
        });
        final boolean[] isSortMiddle = {false};
        mSortView.setMiddleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSortView.setTitle("销量1", 2);
                mSortView.setTitleColor(R.color.colorAccent, 2);
                if(isSortMiddle[0]){
                    isSortMiddle[0] = false;
                    //降序排列
                    mSortView.setTitleDrawable(2, 2);
                }else {
                    isSortMiddle[0] = true;
                    //升序排列
                    mSortView.setTitleDrawable(1, 2);
                }
            }
        });
        final boolean[] isSortRight = {false};
        mSortView.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSortView.setTitle("价格1", 3);
                mSortView.setTitleColor(R.color.colorAccent, 3);
                if(isSortRight[0]){
                    isSortRight[0] = false;
                    //降序排列
                    mSortView.setTitleDrawable(2, 3);
                }else {
                    isSortRight[0] = true;
                    //升序排列
                    mSortView.setTitleDrawable(1, 3);
                }
            }
        });

    }
}
