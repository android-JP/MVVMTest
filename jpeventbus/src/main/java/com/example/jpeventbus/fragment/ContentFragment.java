package com.example.jpeventbus.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jpeventbus.R;
import com.example.jpeventbus.bean.Item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by androidjp on 16-5-24.
 */
public class ContentFragment extends Fragment{

    private View view;
    private TextView tvDetail;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_detail,container,false);
            tvDetail = (TextView) view.findViewById(R.id.tv_detail);
        }
        return view;
    }


    /** List点击时会发送些事件，接收到事件后更新详情 */
    @Subscribe
    public void onEvent(Item item)
    {
        if (item != null)
            tvDetail.setText(item.content);
    }
}
