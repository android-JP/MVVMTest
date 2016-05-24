package com.example.androidjp.mvvmtest.model.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by androidjp on 16-5-23.
 */
public class ThreadUtil {

    public static ExecutorService fixedThreadPool;

    /**
     * 获取线程池
     * @return
     */
    public static ExecutorService getFixedThreadPool(){
        if (fixedThreadPool== null){
            int count = Runtime.getRuntime().availableProcessors();
            fixedThreadPool = Executors.newFixedThreadPool(count);
        }
        return fixedThreadPool;
    }
}
