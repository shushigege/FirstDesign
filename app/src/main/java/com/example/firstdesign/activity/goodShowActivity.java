package com.example.firstdesign.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstdesign.Dingdan;
import com.example.firstdesign.Goods;
import com.example.firstdesign.R;
import com.example.firstdesign.shoppingcar.CarGoods;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
public class goodShowActivity extends AppCompatActivity implements View.OnClickListener{
private TextView tv_pirce,tv_show,tv_shopname;
    private ImageView imageView_goods,imageView_shouchang;
    private Button btn_joincart,btn_buy;
    private ArrayList isJoinCar=new ArrayList();
    private Goods good;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_show);
        initView();
        good=(Goods)getIntent().getSerializableExtra("good_data");
        setView(good);
}
void initView()
    {
        tv_pirce=findViewById(R.id.price_goods_tv);
        tv_show=findViewById(R.id.goods_tv_show);
        tv_shopname=findViewById(R.id.tv_shopName);
        imageView_goods=findViewById(R.id.image_goods);
        imageView_shouchang=findViewById(R.id.image_shouchang);
        btn_buy=findViewById(R.id.btn_buy);
        btn_joincart=findViewById(R.id.btn_joinCart);
        imageView_shouchang.setOnClickListener(this);
        btn_joincart.setOnClickListener(this);
        btn_buy.setOnClickListener(this);
    }
    void setView(Goods good)
    {
        tv_pirce.setText("￥"+good.getG_price());
        tv_show.setText("简  介： "+good.getG_type());
        tv_shopname.setText(good.getG_shop());
        imageView_goods.setBackgroundResource(good.getG_photo());
    }
@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buy:
                Intent intent =new Intent(goodShowActivity.this,DingDanActivity.class);
                List<CarGoods> goodsList=new ArrayList<CarGoods>();
                CarGoods carGoods=new CarGoods(good,1,1,"1");
                goodsList.add(carGoods);
                Dingdan dingdan =new Dingdan(goodsList,1,good.getG_price(),1);
                intent.putExtra("dingdanData",dingdan);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_joinCart:
                //加入购物车
                CarGoods carGoods2 =new CarGoods(good,1,1,"0");
                carGoods2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "加入购物车成功：", Toast.LENGTH_SHORT).show();
                            isJoinCar.add(carGoods2.getG_id());
                        } else {
                            Toast.makeText(getApplicationContext(), "加入购物车失败：", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.image_shouchang:
                Toast.makeText(this,"点击了收藏",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}