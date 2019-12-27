package cn.appoa.afdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

public class PlayTextView extends android.support.v7.widget.AppCompatTextView {

    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private int mTranslate;

    public PlayTextView(Context context) {
        super(context);
    }

    public PlayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();//系统里的函数

            if (mViewWidth > 0) {
                // 获取当前绘制TextView的Paint对象
                mPaint = getPaint();
                // 给这个paint对象设置原生TextView没有的LinearGradient属性：
                linearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
//                        new int[]{Color.BLUE, 0xffffffff, Color.GREEN},
                        new int[]{Color.RED, Color.GREEN, Color.BLUE},
                        new float[]{0, 1, 2},
                        Shader.TileMode.MIRROR);
                mPaint.setShader(linearGradient);
                matrix = new Matrix();
            }
        }
    }

    /**
     * 在onDraw中通过矩阵的方式来不断平移渐变效果，从而在绘制文字时，产生动态的闪动的效果：
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 回调父类方法super.onDraw(canvas)前，对TextView来说即是绘制文本内容之前
        super.onDraw(canvas);
        // TODO 回调父类方法后，对TextView来说即是绘制文本内容之后
        Log.e("mess", "------onDraw----");
        if (matrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            matrix.setTranslate(mTranslate, 0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }
}
