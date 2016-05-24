package com.example.androidjp.mvvmtest.model;

import android.databinding.BaseObservable;

/**
 * 打包渠道
 * Created by androidjp on 16-5-24.
 */
public class Channel extends BaseObservable{
    private String channel;

    public Channel() {
    }

    public Channel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
