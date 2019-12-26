package cn.appoa.afdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.appoa.afdemo.R;

public class SortView extends FrameLayout implements View.OnClickListener {

    private View.OnClickListener mLeftOnClickListener,mMiddleOnClickListener,mRightOnClickListener;
    private RelativeLayout rl_sort1;
    private RelativeLayout rl_sort2;
    private RelativeLayout rl_sort3;
    private TextView tv_sort1;
    private TextView tv_sort2;
    private TextView tv_sort3;
    private int type;

    public SortView(@NonNull Context context) {
        super(context);
    }

    public SortView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.sort_view, this);
        rl_sort1 = (RelativeLayout) findViewById(R.id.rl_sort1);
        rl_sort2 = (RelativeLayout) findViewById(R.id.rl_sort2);
        rl_sort3 = (RelativeLayout) findViewById(R.id.rl_sort3);
        tv_sort1 = (TextView) findViewById(R.id.tv_sort1);
        tv_sort2 = (TextView) findViewById(R.id.tv_sort2);
        tv_sort3 = (TextView) findViewById(R.id.tv_sort3);
        //点击事件
        rl_sort1.setOnClickListener(this);
        rl_sort2.setOnClickListener(this);
        rl_sort3.setOnClickListener(this);
    }

    public SortView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sort1:
                if (mLeftOnClickListener != null) {
                    mLeftOnClickListener.onClick(v);
                }
                break;
            case R.id.rl_sort2:
                if (mMiddleOnClickListener != null) {
                    mMiddleOnClickListener.onClick(v);
                }
                break;
            case R.id.rl_sort3:
                if (mRightOnClickListener != null) {
                    mRightOnClickListener.onClick(v);
                }
                break;
        }
    }

    public void setLeftOnClickListener(View.OnClickListener leftOnClickListener) {
        mLeftOnClickListener = leftOnClickListener;
    }
    public void setMiddleOnClickListener(View.OnClickListener middleOnClickListener) {
        mMiddleOnClickListener = middleOnClickListener;
    }
    public void setRightOnClickListener(View.OnClickListener rightOnClickListener) {
        mRightOnClickListener = rightOnClickListener;
    }

    public void setTittle(String title, int type) {
        switch (type) {
            case 1:
                tv_sort1.setText(title);
                break;
            case 2:
                tv_sort2.setText(title);
                break;
            case 3:
                tv_sort3.setText(title);
                break;
        }
    }
}
