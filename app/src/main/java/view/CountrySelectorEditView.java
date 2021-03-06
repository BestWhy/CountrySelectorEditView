package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import adapter.CountrySelectorAdapter;
import bean.Country;

import com.countryselectoreditview.MainActivity;
import com.countryselectoreditview.R;

/******************************************
 * 类描述：
 *
 * @author: WangHaiYu
 * @time: 2018/5/21 14:49
 ******************************************/
public class CountrySelectorEditView extends FrameLayout implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnFocusChangeListener {

    /**
     * editHint
     */
    private String mHint;
    /**
     * 尺寸模式
     */
    private int mCountryMode;
    /**
     * 用户名
     */
    private TextView mTvUserName;
    /**
     * 背景颜色
     */
    private LinearLayout mLinBackground;
    /**
     * 选择国家
     */
    private LinearLayout mLinSelector;
    /**
     * 国家图片
     */
    private ImageView mIvCountry;
    /**
     * 国家代号
     */
    private TextView mTvCountry;
    /**
     * 用户名
     */
    private EditText mEditUserName;
    /**
     * popup集合
     */
    private CountrySelectorListView mCountrySelectorListView;
    /**
     * popup
     */
    private PopupWindow mPopupWindow;
    /**
     * 右侧图片
     */
    private Drawable mDrawable;

    /**
     * edieView Drawable点击
     */
    private EditText.OnTouchListener mEditOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
            Drawable drawable = mEditUserName.getCompoundDrawables()[2];
            //如果右边没有图片，不再处理
            if (drawable == null)
                return false;
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP)
                return false;
            if (event.getX() > mEditUserName.getWidth() - mEditUserName.getPaddingRight() - drawable.getIntrinsicWidth()) {
                mEditUserName.setText("");
                return true;
            }
            return false;
        }
    };

    public CountrySelectorEditView(@NonNull Context context) {
        this(context, null);
    }

    public CountrySelectorEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountrySelectorEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.country_selector_layout, this);

        mCountrySelectorListView = (CountrySelectorListView) LayoutInflater.from(context).inflate(R.layout.country_selector_list_layout, null);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountrySelectorEditText, defStyleAttr, 0);
        mCountryMode = ta.getInt(R.styleable.CountrySelectorEditText_countrySelectorMode, 0);
        mHint = ta.getString(R.styleable.CountrySelectorEditText_hint);
        mDrawable = ta.getDrawable(R.styleable.CountrySelectorEditText_drawableRight);
        ta.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTvUserName = findViewById(R.id.tv_country_selector_name);
        mLinBackground = findViewById(R.id.lin_country_selector_bg);

        mLinSelector = findViewById(R.id.lin_country_selector);
        mIvCountry = findViewById(R.id.iv_country_selector);
        mTvCountry = findViewById(R.id.tv_country_selector);

        mEditUserName = findViewById(R.id.ed_country_selector);


        mEditUserName.setFocusable(true);
        mEditUserName.setFitsSystemWindows(true);
        if (!TextUtils.isEmpty(mHint)) {
            mEditUserName.setHint(mHint);
        }

        if (mDrawable != null) {
            mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
            mEditUserName.setCompoundDrawables(null, null, mDrawable, null);
            mEditUserName.setOnTouchListener(mEditOnTouchListener);
        }

        mLinSelector.setOnClickListener(this);
        mCountrySelectorListView.setOnItemClickListener(this);
        mEditUserName.setOnFocusChangeListener(this);

    }

    /**
     * 如果布局发生改,并且dropMode是match_parent,则设置ListView的宽度
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (changed && 0 == mCountryMode) {
            mCountrySelectorListView.setListWidth(getMeasuredWidth());
        }
    }


    /**
     * 设置Adapter
     *
     * @param adapter ListView的Adapter
     */
    public void setCountryListAdapter(BaseAdapter adapter) {
        mCountrySelectorListView.setAdapter(adapter);

        mPopupWindow = new PopupWindow(mCountrySelectorListView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true); // 让popwin获取焦点
    }


    /**
     * 获取输入的内容
     *
     * @return
     */
    public String getEditTextView() {
        String userName = mEditUserName.getText().toString();

        return userName;
    }

    /**
     * 获取国家代码
     */
    public String getCountryCode() {
        String countryCode = mTvCountry.getText().toString();
        return countryCode;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lin_country_selector) {
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
                return;
            }

            mPopupWindow.showAsDropDown(mLinSelector, 0, 0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CountrySelectorAdapter adapter = (CountrySelectorAdapter) mCountrySelectorListView.getAdapter();

        Country country = (Country) adapter.getItem(position);
        mTvCountry.setText(country.countryCode);
        mPopupWindow.dismiss();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        changeTextViewAndBackgroundColor(hasFocus);
    }


    /**
     * 切换文字和背景的样式
     */
    private void changeTextViewAndBackgroundColor(boolean hasFocus) {

        if (hasFocus) {
            mLinBackground.setBackground(getResources().getDrawable(R.drawable.shape_login_edit_selector));
            mTvUserName.setTextColor(getResources().getColor(R.color.colorText));
            mEditUserName.setHintTextColor(getResources().getColor(R.color.colorText));
        } else {
            mLinBackground.setBackground(getResources().getDrawable(R.drawable.shape_login_edit_nor));
            mTvUserName.setTextColor(getResources().getColor(R.color.colorEditNorText));
            mEditUserName.setHintTextColor(getResources().getColor(R.color.colorEditNorText));
        }
    }


}
