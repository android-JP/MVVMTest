package com.example.jpeventbus.bean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by androidjp on 16-5-30.
 */
public interface IUserBiz {
    @GET("users")
    Call<List<User>> getUsers();

    //接收username参数， @GET注解使用{username}声明访问路径，这里，@GET中的username可以看作占位符，实际运行会通过被标记的参数进行替换
    @GET("{username}")
    Call<User> getUser(@Path("username")String username);

    ///查询参数的上传： http://com.xxx.xxx/users?orderby=sort
    @GET("users")
    Call<List<User>> getUsersBySort(@Query("sortby")String sort);

    @POST("add")
    Call<List<User>> addUser(@Body User user);

    //对应okhttp，还有两种requestBody（请求体）：1.FormBody（表单方式传递简单键值对）， 2.MultipleBody（POST表单的方式上传文件可以携带参数）
    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("username")String username, @Field("password")String password);


    ///其中， RequestBody 为键值对中的值，而“Part("username")”则为键值对的键
    //多个Part， 每一个Part对应一个RequestBody
    @POST("register")
    @Multipart
    Call<User> registerUser(@Part MultipartBody.Part photo, @Part("username")RequestBody username ,@Part("password")RequestBody password);


    //不建议使用的文件上传方式（文件名硬编码）
    @Multipart
    @POST("/api/Accounts/editaccount")
    Call<User> editUser(@Header("Authorization")String authorization, @Part("file\";filename=\"pic.png")RequestBody file , @Part("FirstName")RequestBody fname, @Part("Id")RequestBody id);


    ///多文件上传
    @Multipart
    @POST("register")
    Call<User> multipleFiles(@PartMap Map<String, RequestBody> params, @Part("password")RequestBody password);

    //下载文件
    @GET("download")
    Call<RequestBody> downloadTest();

}
