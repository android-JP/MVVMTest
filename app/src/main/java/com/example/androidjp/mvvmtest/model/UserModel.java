package com.example.androidjp.mvvmtest.model;

/**
 * 用户类
 *
 * Created by androidjp on 16-5-20.
 */
public class UserModel {

    private String name;
    private int age;
    private String job;


    //==================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    //=====================================

    public String speak(){
        return "I am "+ name + ", "+ age +"years old , my job is " + job;
    }

    public UserModel() {
    }

    public UserModel(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
}
