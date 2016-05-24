package com.example.androidjp.mvvmtest.ui.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.androidjp.mvvmtest.ui.presenter.CommonPresenter;

/**
 * Created by androidjp on 16-5-23.
 */
public abstract class BindActivity extends Activity {

    private CommonPresenter presenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFit();
        viewBindData();
    }

    /**
     * 沉浸式适配方案
     */
    private void setWindowFit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    //1:从view引出一个连接器
    //2：用连接器连上想要连上的数据
    protected abstract void viewBindData();


    //========================================================

    public CommonPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(CommonPresenter presenter) {
        this.presenter = presenter;
    }
}
