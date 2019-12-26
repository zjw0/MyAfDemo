package cn.appoa.afdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.appoa.afdemo.R;

public class TitleBar extends RelativeLayout {

    /**
     * 获取自定义的属性
     *
     * @param context
     */
    private int leftTextColor;
    private Drawable leftBackGround;
    private Drawable rightBackGround;
    private Drawable titleBackGround;
    private String leftText;
    private float leftTextSize;

    private int rightTextColor;
    private String rightText;
    private float rightTextSize;

    private int titleTextColor;
    private String titleText;
    private float titleTextSize;


    private TextView titleView;
    private Button leftButton;
    private Button rightButton;
    private RelativeLayout.LayoutParams leftParams;
    private RelativeLayout.LayoutParams rightParams;
    private RelativeLayout.LayoutParams titleParams;
    private TitleBarClickListener listener;


    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 通过这个方法，将你在attrs.xml中定义的 declare_styleable的
     * 所有属性的值存储到TypedArray中：
     * @param context
     * @param attrs
     */
    private void initAttr(Context context, AttributeSet attrs) {

        // 得到TypedArray对象typed
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        // 从typed中取出对应的值为要设置的属性赋值，第二个参数是未指定时的默认值
        // 这里第一个参数是 R.styleable.name_attrname 耶
        leftTextColor = typed.getColor(R.styleable.TitleBar_leftTextColor, 0XFFFFFFFF);
        //leftBackGround = typed.getDrawable(R.styleable.TitleBar_leftBackGround);
        leftText = typed.getString(R.styleable.TitleBar_leftText);
        leftTextSize = typed.getDimension(R.styleable.TitleBar_leftTextSize, 14);

        rightTextColor = typed.getColor(R.styleable.TitleBar_rightTextColor, 0XFFFFFFFF);
        //rightBackGround = typed.getDrawable(R.styleable.TitleBar_rightBackGround);
        rightText = typed.getString(R.styleable.TitleBar_rightText);
        rightTextSize = typed.getDimension(R.styleable.TitleBar_rightTextSize, 14);

        titleTextColor = typed.getColor(R.styleable.TitleBar_titleTextColor, 0XFFFFFFFF);
        //titleBackGround = typed.getDrawable(R.styleable.TitleBar_titleBackGround);
        titleText = typed.getString(R.styleable.TitleBar_title);
        titleTextSize = typed.getDimension(R.styleable.TitleBar_titleTextSize, 14);
        // 不要忘记调用,用来避免重新创建的时候的错误。
        typed.recycle();
    }

    /**
     * 代码布局
     *
     * @param context
     */
    @SuppressWarnings("deprecation")
    private void initView(Context context) {
        // TitleBar上的三个控件
        titleView = new TextView(context);
        leftButton = new Button(context);
        rightButton = new Button(context);

        // 为创建的组件赋值，标题栏
        titleView.setText(titleText);
        titleView.setTextSize(titleTextSize);
        titleView.setTextColor(titleTextColor);
        //titleView.setBackgroundDrawable(titleBackGround);
        titleView.setGravity(Gravity.CENTER);

        // 为创建的组件赋值，左边按钮
        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackgroundDrawable(leftBackGround);
        leftButton.setTextSize(leftTextSize);

        // 为创建的组件赋值，右边按钮
        rightButton.setText(rightText);
        rightButton.setTextSize(rightTextSize);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackgroundDrawable(rightBackGround);

        // 为组件元素设置相应的布局元素，设置大小和位置
        // 在左边
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        // 添加到ViewGroup中：
        addView(leftButton, leftParams);

        // 在右边
        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        addView(rightButton, rightParams);

        //中间
        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(titleView, titleParams);

        //添加点击监听，（下面讲述如何引入的）
        /*
         * 这里的setOnClickListener是系统的关于一个Button的自带的点击事件
         * */
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 在对点击事件做相应以前，在调用这的MainActivity中，就已经把listenr传入进来了，
                 * 在这里只需要直接调用就可以了。
                 * 其中listener是一个setTitleBarClickListener接口方法的对象。
                 * */
                if (listener != null) {
                    //正常设置它们的点击事件处理onClick，只是在onClick中让它们执行我们设定的处理。
                    listener.leftClick();
                }
            }
        });


        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.rightClick();
                }
            }
        });
    }

    /*
     * 这是一个接口方法，这个接口中有两个为实现的方法。
     * */
    public interface TitleBarClickListener{
        //左点击
        void leftClick();
        //右点击
        void rightClick();
    }

    /**
     * 暴露一个方法给调用者来注册接口回调，通过接口来获得回调者对接口方法TitleBarClickListener的实现
     * 这里的参数是一个TitleBarClickListener接口的接口对象。
     * @param listener
     */
    public void setTitleBarClickListener(TitleBarClickListener listener) {
        this.listener = listener;
    }
}
