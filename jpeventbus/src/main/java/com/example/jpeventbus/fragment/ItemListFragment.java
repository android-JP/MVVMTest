package com.example.jpeventbus.fragment;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jpeventbus.bean.Item;
import com.example.jpeventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by androidjp on 16-5-24.
 */
public class ItemListFragment extends ListFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Register*/
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*Unregister*/
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /*TODO：使用单例线程池去加载数据*/
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    ///后台线程发布一个事件
                    EventBus.getDefault().post(new MessageEvent(Item.ITEMS));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

//        /*TODO:方法二：使用简单的子线程去加载数据*/
//        new Thread()
//        {
//            public void run()
//            {
//                try
//                {
//                    Thread.sleep(2000); // 模拟延时
//                    // 发布事件，在后台线程发的事件
//                    EventBus.getDefault().post(new MessageEvent(Item.ITEMS));
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        /*TODO：使用主线程加载数据*/
//        EventBus.getDefault().post(new MessageEvent(Item.ITEMS));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showList(MessageEvent event){
        setListAdapter(new ArrayAdapter<Item>(getActivity()
                ,android.R.layout.simple_list_item_activated_1
                ,android.R.id.text1,event.objList));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }
}
