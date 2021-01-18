package com.example.firstdesign;
import cn.bmob.v3.BmobUser;
public class User extends BmobUser {
    private  int user_id;
//    private  String username;
//    private  String password;
    private  String address;
    private int gender;
public int getUser_id() {
        return user_id;
    }
public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//        public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
public String getAddress() {
        return address;
    }
public void setAddress(String address) {
        this.address = address;
    }
public void setGender(int i) {
        this.gender=i;
    }
public int getGender() {
        return gender;
    }
}
