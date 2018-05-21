package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/******************************************
 * 类描述：
 *
 * @author: WangHaiYu
 * @time: 2018/5/21 15:04
 ******************************************/
public class CountrySelectorListView extends ListView {

    private int mWidth = 0;

    public CountrySelectorListView(Context context) {
        this(context, null);
    }

    public CountrySelectorListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountrySelectorListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    // 重写onMeasure方法 解决默认横向占满屏幕问题
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            int childWidth = getChildAt(i).getMeasuredWidth();
            mWidth = Math.max(mWidth, childWidth);
        }

        setMeasuredDimension(mWidth, height);
    }

    /**
     * 设置宽度，如果不设置，则默认包裹内容
     *
     * @param width 宽度
     */
    protected void setListWidth(int width) {
        mWidth = width;
        System.out.println("setWidth");
    }
}
