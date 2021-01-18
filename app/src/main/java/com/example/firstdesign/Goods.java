package com.example.firstdesign;
import java.io.Serializable;
import cn.bmob.v3.BmobObject;
/**
 * 商品类
 * */
public class Goods extends BmobObject implements Serializable   {
    private int g_id;
    private int g_photo;
    private String g_name;
    private String g_type;
    private double g_price;
    private int g_sales;
    private String g_shop;
    private int  classification;
//各种构造函数
    public Goods() {
    }
public Goods(int g_photo, String g_name, double g_price) {
        this.g_photo = g_photo;
        this.g_name = g_name;
        this.g_price = g_price;
    }
public Goods(int g_photo, String g_name, double g_price, int g_sales, String g_shop) {
        this.g_photo = g_photo;
        this.g_name = g_name;
        this.g_price = g_price;
        this.g_sales = g_sales;
        this.g_shop = g_shop;
    }
public Goods(int g_id, int g_photo, String g_name, String g_type, double g_price, int g_sales, String g_shop, int classification) {
        this.g_id = g_id;
        this.g_photo = g_photo;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_price = g_price;
        this.g_sales = g_sales;
        this.g_shop = g_shop;
        this.classification=classification;
    }
//    public Goods(int g_id, String g_photo2, String g_name, String g_type, double g_price, int g_sales, String g_shop) {
//        this.g_id = g_id;
//        this.g_photo2 = new BmobFile(new File(g_photo2));
//        this.g_name = g_name;
//        this.g_type = g_type;
//        this.g_price = g_price;
//        this.g_sales = g_sales;
//        this.g_shop = g_shop;
//    }
public int getG_photo() {
        return g_photo;
    }
public String getG_name() {
        return g_name;
    }
public String getG_type() {
        return g_type;
    }
public double getG_price() {
        return g_price;
    }
public int getG_sales() {
        return g_sales;
    }
public String getG_shop() {
        return g_shop;
    }
public int getG_id() {
        return g_id;
    }
public int getClassification() {
        return classification;
    }
public void setClassification(int classification) {
        this.classification = classification;
    }
}
