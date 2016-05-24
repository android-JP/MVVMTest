package com.example.androidjp.mvvmtest.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.androidjp.mvvmtest.R;
import com.example.androidjp.mvvmtest.databinding.LayoutListBinding;
import com.example.androidjp.mvvmtest.model.UserModel;
import com.example.androidjp.mvvmtest.ui.callback.DataView;
import com.example.androidjp.mvvmtest.ui.presenter.impl.ListPresenter;

import java.util.List;

/**
 *
 * 使用RecycleView，进行列表的显示（MVVM + MVP）
 * Created by androidjp on 16-5-23.
 */
public class ListActivity extends BindActivity implements DataView<List<UserModel>>{

    private LayoutListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new ListPresenter(this));
    }


    @Override
    protected void viewBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.layout_list);
    }

    @Override
    public void startLoadData() {

    }

    @Override
    public void finishLoadData(List<UserModel> data) {

    }

    @Override
    public void failLoadData(String msg) {

    }
}
