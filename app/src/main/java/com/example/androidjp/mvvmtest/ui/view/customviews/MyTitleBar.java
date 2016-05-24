package com.example.androidjp.mvvmtest.ui.view.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidjp.mvvmtest.R;
import com.example.androidjp.mvvmtest.model.utils.DisplayUtil;

/**
 * Created by androidjp on 16-5-23.
 */
public class MyTitleBar extends LinearLayout implements View.OnClickListener{

    private View bottomLine;

    private RelativeLayout subLayout;


    private TextView tvTitle;
    private Button btnLeft;/*文本按钮*/
    private Button btnRight;/*文本按钮*/
    private ImageButton ibtnLeft;/*图标（“返回”图标等）*/
    private ImageButton ibtnRight;

    private View leftView;/*左侧的自定义布局*/

    private View rightView;/*右侧的自定义布局*/

    //数据
    private String titleText;
    private String leftText;
    private String rightText;
    private int leftTextColorId;
    private int rightTextColorId;
    private int titleTextColorId;
    private int leftTextBgId;
    private int rightTextBgId;

    private int leftIconId;
    private int rightIconId;
    private int leftIconBgId;
    private int rightIconBgId;

    private float titleSize;
    private float leftSize;
    private float rightSize;

    /*自定义Layout 的 id*/
    private int leftViewLayout;
    private int rightViewLayout;

    private boolean leftBtnShow;
    private boolean rightBtnShow;
    private boolean leftIconShow;
    private boolean rightIconShow;
    private boolean leftLayoutShow;
    private boolean rightLayoutShow;

    private LeftClickListener leftClickListener;
    private RightClickListener rightClickListener;


    public MyTitleBar(Context context) {
        this(context,null);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initValues(context,attrs);
        initView(context);
        initEvent();
    }

    private void initEvent() {
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        ibtnLeft.setOnClickListener(this);
        ibtnRight.setOnClickListener(this);
        leftView.setOnClickListener(this);
        rightView.setOnClickListener(this);
    }

    private void initValues(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);

        titleText = array.getString(R.styleable.MyTitleBar_titleText);
        leftText = array.getString(R.styleable.MyTitleBar_leftBtnText);
        rightText = array.getString(R.styleable.MyTitleBar_rightBtnText);
        leftTextColorId = array.getColor(R.styleable.MyTitleBar_leftBtnTextColor, Color.WHITE);
        rightTextColorId = array.getColor(R.styleable.MyTitleBar_rightBtnTextColor,Color.WHITE);
        titleTextColorId = array.getColor(R.styleable.MyTitleBar_titleColor, Color.WHITE);
        leftTextBgId = array.getResourceId(R.styleable.MyTitleBar_leftBtnBg,R.color.transparent);
        rightTextBgId = array.getResourceId(R.styleable.MyTitleBar_rightBtnBg,R.color.transparent);

        leftIconId = array.getResourceId(R.styleable.MyTitleBar_leftIcon,R.mipmap.ic_launcher);
        rightIconId = array.getResourceId(R.styleable.MyTitleBar_rightIcon,R.mipmap.ic_launcher);
        leftIconBgId = array.getResourceId(R.styleable.MyTitleBar_leftIconBg,R.color.transparent);
        rightIconBgId = array.getResourceId(R.styleable.MyTitleBar_rightIconBg,R.color.transparent);

        titleSize = array.getDimension(R.styleable.MyTitleBar_titleSize,20);
        leftSize = array.getDimension(R.styleable.MyTitleBar_btnLeftTextSize,16);
        rightSize = array.getDimension(R.styleable.MyTitleBar_btnRightTextSize,16);

        leftViewLayout = array.getResourceId(R.styleable.MyTitleBar_leftLayout,0);
        rightViewLayout = array.getResourceId(R.styleable.MyTitleBar_rightLayout,0);

        leftBtnShow = array.getBoolean(R.styleable.MyTitleBar_leftBtnShow,false);
        rightBtnShow = array.getBoolean(R.styleable.MyTitleBar_rightBtnShow,false);
        leftIconShow = array.getBoolean(R.styleable.MyTitleBar_leftIconShow,false);
        rightIconShow = array.getBoolean(R.styleable.MyTitleBar_rightIconShow,false);
        leftLayoutShow = array.getBoolean(R.styleable.MyTitleBar_leftLayoutShow,false);
        rightLayoutShow = array.getBoolean(R.styleable.MyTitleBar_rightLayoutShow,false);

        array.recycle();

    }

    private void initView(Context context) {
        subLayout = new RelativeLayout(context);
//        subLayout.setBackgroundResource(R.color.colorPrimary);
        tvTitle = new TextView(subLayout.getContext());
        btnLeft = new Button(subLayout.getContext());
        btnRight = new Button(subLayout.getContext());
        ibtnLeft = new ImageButton(subLayout.getContext());
        ibtnRight = new ImageButton(subLayout.getContext());
        bottomLine = new  View(context);

        /**
         * addViews
         */

        LinearLayout.LayoutParams mSubLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(70,(Activity)context));

        addView(subLayout,mSubLayoutParams);

        LinearLayout.LayoutParams mBottomLineParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,1
        );
        bottomLine.setBackgroundResource(android.R.color.darker_gray);
        addView(bottomLine,mBottomLineParams);


        tvTitle.setText(titleText);
        tvTitle.setTextSize(titleSize);
        tvTitle.setTextColor(titleTextColorId);

        btnLeft.setText(leftText);
        btnLeft.setTextSize(leftSize);
        btnLeft.setTextColor(leftTextColorId);
        btnLeft.setBackgroundResource(leftTextBgId);
        btnLeft.setPadding(0,0,0,0);

        btnRight.setText(rightText);
        btnRight.setTextSize(rightSize);
        btnRight.setTextColor(rightTextColorId);
        btnRight.setBackgroundResource(rightTextBgId);
        btnRight.setPadding(0,0,0,0);

        ibtnLeft.setImageResource(leftIconId);
        ibtnLeft.setBackgroundResource(leftIconBgId);

        ibtnRight.setImageResource(rightIconId);
        ibtnRight.setBackgroundResource(rightIconBgId);


        if (leftViewLayout!=0){
            leftView = LayoutInflater.from(context).inflate(leftViewLayout,null);
        }else{
            leftView = new View(context);
        }
        if (rightViewLayout!=0){
            rightView = LayoutInflater.from(context).inflate(rightViewLayout,null);
        }else{
            rightView = new View(context);
        }

        RelativeLayout.LayoutParams mLeftViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLeftViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLeftViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mLeftViewParams.setMargins(DisplayUtil.dip2px(12,(Activity)context),0,0,0);
        subLayout.addView(leftView,mLeftViewParams);

        RelativeLayout.LayoutParams mRightViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRightViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mRightViewParams.setMargins(0,0,DisplayUtil.dip2px(12,(Activity)context),0);
        subLayout.addView(rightView,mRightViewParams);

        RelativeLayout.LayoutParams mLeftParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DisplayUtil.dip2px(40,(Activity)context));
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mLeftParams.setMargins(DisplayUtil.dip2px(12,(Activity)context),0,0,0);
        subLayout.addView(btnLeft,mLeftParams);

        RelativeLayout.LayoutParams mRightParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DisplayUtil.dip2px(40,(Activity)context));
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mRightParams.setMargins(0,0,DisplayUtil.dip2px(12,(Activity)context),0);
        subLayout.addView(btnRight,mRightParams);


        RelativeLayout.LayoutParams mTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        subLayout.addView(tvTitle,mTitleParams);


        RelativeLayout.LayoutParams mILeftParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(60,(Activity)context), DisplayUtil.dip2px(50,(Activity)context));
        mILeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mILeftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mILeftParams.setMargins(DisplayUtil.dip2px(12,(Activity)context),0,0,0);
        subLayout.addView(ibtnLeft,mILeftParams);

        RelativeLayout.LayoutParams mIRightParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(60,(Activity)context), DisplayUtil.dip2px(50,(Activity)context));
        mIRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mIRightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mIRightParams.setMargins(0,0, DisplayUtil.dip2px(12,(Activity)context),0);
        subLayout.addView(ibtnRight,mIRightParams);


        if(leftBtnShow)
            btnLeft.setVisibility(VISIBLE);
        else
            btnLeft.setVisibility(GONE);

        if (rightBtnShow)
            btnRight.setVisibility(VISIBLE);
        else
            btnRight.setVisibility(GONE);

        if (leftIconShow)
            ibtnLeft.setVisibility(VISIBLE);
        else
            ibtnLeft.setVisibility(GONE);

        if (rightIconShow)
            ibtnRight.setVisibility(VISIBLE);
        else
            ibtnRight.setVisibility(GONE);

        if (leftLayoutShow)
            leftView.setVisibility(VISIBLE);
        else
            leftView.setVisibility(GONE);

        if (rightLayoutShow)
            rightView.setVisibility(VISIBLE);
        else
            rightView.setVisibility(GONE);


        /**
         * 设置适配布局
         * ①两个都注释掉，布局整体上去了，没有适配好
         * ②注释了MinHeight，也一样没有适配
         * ③注释了fitSsytemWin，可以了，也就是说，没有特别大的关系，只要你内步有子View或者ViewGroup有高度，就行
         */

        setFitsSystemWindows(true);
    }


    public void setLeftClickListener(LeftClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public void setRightClickListener(RightClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnLeft)||v.equals(ibtnLeft)||v.equals(leftView)){
            if (leftClickListener!=null){
                leftClickListener.titleLeftClick();
            }
        }
        else if(v.equals(btnRight)||v.equals(ibtnRight)||v.equals(rightView)){
            if (rightClickListener!=null){
                rightClickListener.titleRightClick();
            }
        }
    }

    public interface LeftClickListener{
        public void titleLeftClick();
    }

    public interface RightClickListener {
        public void titleRightClick();
    }

    ///==============================================================================
    ///Data Binding 需要
    ///==============================================================================


    public void setTitleText(String titleText) {
        this.titleText = titleText;
        this.tvTitle.setText(titleText);
    }

    @BindingAdapter("app:titleWen")
    public static void setTitleWen(View view , String title){
        if (view instanceof MyTitleBar){
            MyTitleBar titleBar = (MyTitleBar)view;
            titleBar.setTitleText(title);
        }
    }



}
