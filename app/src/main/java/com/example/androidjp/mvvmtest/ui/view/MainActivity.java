package com.example.androidjp.mvvmtest.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidjp.mvvmtest.MyApplication;
import com.example.androidjp.mvvmtest.R;
import com.example.androidjp.mvvmtest.databinding.ActivityMainBinding;
import com.example.androidjp.mvvmtest.model.Channel;
import com.example.androidjp.mvvmtest.model.UserModel;
import com.example.androidjp.mvvmtest.ui.presenter.impl.ListPresenter;
import com.example.androidjp.mvvmtest.ui.view.customviews.MyTitleBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BindActivity implements MyTitleBar.LeftClickListener,MyTitleBar.RightClickListener{
    private ActivityMainBinding binding;

    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void viewBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        channel = new Channel();
        binding.setChannel(channel);
    }


    private void initView() {

        final Button button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Handler handler= new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                binding.getUser().setJob("老司机");
                Toast.makeText(MainActivity.this,binding.getUser().getJob(),Toast.LENGTH_SHORT).show();

                channel.setChannel(MyApplication.getInstance().getApplicationMetaValue("UMENG_CHANNEL"));

                return true;
            }
        }){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                binding.getUser().setJob("律师");
            }
        };

        Button buttonHide = (Button) findViewById(R.id.btn_hide);
        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.setUser(new UserModel());

//                user.setName("小明");
                binding.getUser().setName("小明");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        UserModel user2 = new UserModel();
//                        user2.setName("爸爸");
//                        user2.setJob("搬运工");
//                        user2.setAge(45);
//                        binding.setUser(user2);
                        binding.getUser().setJob("小学生");
                        Toast.makeText(MainActivity.this,binding.getUser().getJob(),Toast.LENGTH_SHORT).show();
                        UserModel userModel = binding.getUser();
                        Toast.makeText(MainActivity.this,binding.getUser().speak(),Toast.LENGTH_SHORT).show();
                        binding.setUser(userModel);
                    }
                },5000);
                handler.sendEmptyMessage(0);


            }
        });
    }

    @Override
    public void titleLeftClick() {
        Toast.makeText(this,"显示菜单",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,ListActivity.class));
    }

    @Override
    public void titleRightClick() {
        Toast.makeText(this,"显示个人中心",Toast.LENGTH_SHORT).show();

        UserModel userModel = new UserModel("小明", 20, "程序员");
        binding.setUser(userModel);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                binding.getUser().setName("小明老板");
            }
        },3000);

    }
}
