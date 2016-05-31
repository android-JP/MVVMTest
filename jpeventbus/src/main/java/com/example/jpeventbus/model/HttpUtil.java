package com.example.jpeventbus.model;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.jpeventbus.bean.IUserBiz;
import com.example.jpeventbus.bean.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Retrofit2 分析练习工具类
 *
 * Created by androidjp on 16-5-30.
 */
public class HttpUtil {

    public static OkHttpClient okHttpClient;

    //一般GET请求
    public static void simpleGet(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<List<User>> call = userBiz.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


    ///动态访问@PATH
    public static void pathTest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<User> call = userBiz.getUser("xiaoming");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //查询参数的设置@Query
    //example:http://baseurl/users?sortby=username
    public static void queryTest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<List<User>> call = userBiz.getUsersBySort("name");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }


    ///POST请求，json串上传
    public static void postJson(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<List<User>> call = userBiz.addUser(new User(1,"小明"));
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }


    //登录（简单表单Post方式）
    public static void login(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<User> call = userBiz.login("小明","123456");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    //注册（单文件上传Post方式）
    public static void register(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);

        File file = new File(Environment.getExternalStorageDirectory(),"icon.png");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photos","icon.png",photoRequestBody);
        Call<User> call = userBiz.registerUser(photo,RequestBody.create(null,"小明"),RequestBody.create(null,"123456"));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //多文件上传@PathMap
    public static void multipleFileTest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);


        File file = new File(Environment.getExternalStorageDirectory(),"messenger.png");
        RequestBody photo1 = RequestBody.create(MediaType.parse("image/png"),file);
        /**
         * map中放一个或者多个文件和键值对
         * 单独的键值对，可以用@Path
         */
        Map<String ,RequestBody> photoList = new HashMap<>();
        photoList.put("photos\";filename=\"icon.png",photo1);
        photoList.put("username",RequestBody.create(null,"小明"));

        Call<User> call = userBiz.multipleFiles(photoList,RequestBody.create(null,"123456"));

    }

    //下载
    public static void downloadTest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/myweb/index")
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(setOkHttpClient())
                .build();

        IUserBiz userBiz = retrofit.create(IUserBiz.class);

        Call<RequestBody> call = userBiz.downloadTest();
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });
    }


    //-------------------------------------------------------

    /**
     * 当使用Retrofit时，需要统一的log管理，给每一个请求添加统一的header等，应该用 okhttpclient 去操作
     */
    public static OkHttpClient setOkHttpClient(){
        if (okHttpClient==null){
            synchronized (HttpUtil.class){
                if (okHttpClient==null){
                    okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            return null;
                        }
                    }).build();

                }
            }
        }
        return okHttpClient;
    }


    //-----------------------------------------------------------
    /***
     * 原理剖析 1
     * 使用Retrofit.create(IXXX.class) 生成接口的实例对象 的过程，其实是：动态代理
     * Proxy在 java反射相关包中
     */

    public static void method1(){

        IUserBiz iUserBiz = (IUserBiz) Proxy.newProxyInstance(IUserBiz.class.getClassLoader(), new Class<?>[]{IUserBiz.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method/*方法*/, Object[] args/*参数*/) throws Throwable {

                /*获取参数内容*/
                GET get = method.getAnnotation(GET.class);
                /*输出：get.value()*/

                return null;
            }
        });

    }


    public static void method2(){

//        Log.e("rrrrr","当前线程："+Thread.currentThread().getName()+"");

        final Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("handler","当前线程："+Thread.currentThread().getName()+"");
            }
        },2000);

        ExecutorService singleThreadPool =  Executors.newSingleThreadExecutor();

        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("rrrrr1","当前线程："+Thread.currentThread().getName()+"");
                    }
                },3000);

                command.run();
            }
        };

        executor.execute(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("rrrrr2","当前线程："+Thread.currentThread().getName()+"");
                    }
                },4000);
            }
        });


        singleThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("rrrrr3","当前线程："+Thread.currentThread().getName()+"");
                    }
                },5000);
            }
        });

    }


}
