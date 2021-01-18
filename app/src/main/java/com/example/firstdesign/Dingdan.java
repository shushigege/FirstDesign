package com.example.firstdesign;
import com.example.firstdesign.shoppingcar.CarGoods;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobObject;
public class Dingdan extends BmobObject implements Serializable {
    private int d_id;
    private double total_price;
    private int d_Userid;
    private List<CarGoods> carGoodsList=new ArrayList<CarGoods>();
    private String shouAddress;
    private String jiAddress;
    private String shou_name;
    private String number;
    public  Dingdan(List<CarGoods> carGoodsList, int d_id, double total_price, int d_Userid){
        //this.carGoodsList=carGoodsList;
        this.d_id=d_id;
        this.total_price=total_price;
        this.d_Userid=d_Userid;
        for (int i=0;i<carGoodsList.size();i++)
        {
            if (carGoodsList.get(i).getC_checked().equals("1")){
                this.carGoodsList.add(carGoodsList.get(i));
            }
        }
    }
    public List<CarGoods> getCarGoodsList() {
        return carGoodsList;
    }
    public void setCarGoodsList(List<CarGoods> carGoodsList) {
        this.carGoodsList = carGoodsList;
    }
    public int getD_Userid() {
        return d_Userid;
    }
    public void setD_Userid(int d_Userid) {
        this.d_Userid = d_Userid;
    }
    public double getTotal_price() {
        return total_price;
    }
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
    public int getD_id() {
        return d_id;
    }
    public void setD_id(int d_id) {
        this.d_id = d_id;
    }
    public String getShouAddress() {
        return shouAddress;
    }
    public void setShouAddress(String shouAddress) {
        this.shouAddress = shouAddress;
    }
    public String getJiAddress() {
        return jiAddress;
    }
    public void setJiAddress(String jiAddress) {
        this.jiAddress = jiAddress;
    }
    public String getShou_name() {
        return shou_name;
    }
    public void setShou_name(String shou_name) {
        this.shou_name = shou_name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
}
