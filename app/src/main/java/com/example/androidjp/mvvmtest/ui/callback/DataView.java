package com.example.androidjp.mvvmtest.ui.callback;

/**
 * Created by androidjp on 16-5-23.
 */
public interface DataView<T> {

    /*开始加载数据*/
    public void startLoadData();

    /*加载数据成功*/
    public void finishLoadData(T data);

    /*加载数据失败*/
    public void failLoadData(String msg);
}
