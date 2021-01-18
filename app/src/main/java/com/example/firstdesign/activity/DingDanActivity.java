package com.example.firstdesign.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstdesign.Dingdan;
import com.example.firstdesign.R;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
public class DingDanActivity extends AppCompatActivity {
private Button btn_buy;
    private Dingdan dingdan;
    private EditText et_name,et_number,et_shouAddress;
    private TextView tv_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉顶部标题
        getSupportActionBar().hide();
        //去掉最上面时间、电量等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        btn_buy=findViewById(R.id.ding_buy);
        et_name=findViewById(R.id.u_name);
        et_number=findViewById(R.id.u_phone);
        et_shouAddress=findViewById(R.id.u_address);
        tv_price=findViewById(R.id.ding_money);
        dingdan= (Dingdan) getIntent().getSerializableExtra("dingdanData");
        String price ="合计：￥"+dingdan.getTotal_price();
        tv_price.setText(price);
        dingdan.setShou_name(et_name.getText().toString());
        dingdan.setNumber(et_number.getText().toString());
        dingdan.setShouAddress(et_shouAddress.getText().toString());
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
dingdan.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            Toast.makeText(v.getContext(),"支付成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(),"支付失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
finish();
            }
        });
    }
}