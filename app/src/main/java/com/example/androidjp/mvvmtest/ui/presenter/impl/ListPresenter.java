package com.example.androidjp.mvvmtest.ui.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.androidjp.mvvmtest.model.UserModel;
import com.example.androidjp.mvvmtest.model.utils.ThreadUtil;
import com.example.androidjp.mvvmtest.ui.callback.DataView;
import com.example.androidjp.mvvmtest.ui.presenter.CommonPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 嵌入okhttp，封装网络请求，获取数据
 *
 * Created by androidjp on 16-5-23.
 */
public class ListPresenter implements CommonPresenter{

    private DataView<List<UserModel>> dataView;

    Handler handler = new Handler();

    public ListPresenter(DataView dataView){
        this.dataView = dataView;
    }


    @Override
    public void startLoadData(){
        if (this.dataView!=null){
            this.dataView.startLoadData();
        }

        ThreadUtil.getFixedThreadPool().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {


                /**
                 * 执行请求
                 */
                List<UserModel> list = new ArrayList<UserModel>();
                for (int i=0;i<10;i++){
                     list.add(new UserModel("小明",19,"工程师"));
                }

                if (dataView!=null){
                    dataView.finishLoadData(list);
                }
                return null;

            }
        });

    }

    @Override
    public void clearData() {
        if (dataView!=null){
            dataView.finishLoadData(new ArrayList<UserModel>());
        }
    }


}
