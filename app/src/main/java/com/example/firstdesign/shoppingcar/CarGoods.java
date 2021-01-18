package com.example.firstdesign.shoppingcar;
import com.example.firstdesign.Goods;
import com.example.firstdesign.shoppingcar.CarGoods;
import java.io.Serializable;
public class CarGoods extends Goods implements Serializable {
    private int   c_userId;
    private int c_num;
    private String c_checked;
public CarGoods(){}
    //构造函数
    public CarGoods(int g_id, int g_photo, String g_name, String g_type, double g_price, int c_num, int classification) {
        super(g_id, g_photo, g_name, g_type, g_price, 9, "京东",classification);
        this.c_num = c_num;
        this.c_checked = "0";
    }
    public CarGoods(Goods goods, int c_userId, int c_num, String c_checked){
        super(goods.getG_id(), goods.getG_photo(),goods.getG_name(),goods.getG_type(),goods.getG_price()
                , goods.getG_sales(), goods.getG_shop(),goods.getClassification());
        this.c_num=c_num;
        this.c_checked=c_checked;
        this.c_userId=c_userId;
}
public CarGoods(int g_id, int g_photo, String g_name, String g_type, double g_price, int c_num, String c_checked, int classification) {
        super(g_id, g_photo, g_name, g_type, g_price, 9, "京东",classification);
        this.c_num = 1;
        this.c_checked = c_checked;
    }
public int getC_num() {
        return c_num;
    }
public void setC_numUp(int n){
        if (c_num==1&&n<0)
        {
}else {
            this.c_num = this.c_num + n;
        }
    }
public String getC_checked() {
        return c_checked;
    }
public void setC_checked(String flag){
        this.c_checked=flag;
    }
public int getC_userId() {
        return c_userId;
    }
public void setC_userId(int c_userId) {
        this.c_userId = c_userId;
    }
}
