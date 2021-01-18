package com.example.firstdesign.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.example.firstdesign.R;
import com.example.firstdesign.adapter.CustomVideoView;
import com.example.firstdesign.javabean.User;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
public class  LoginActivity extends AppCompatActivity {
    private CustomVideoView videoView;
private Button bt_login;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉顶部标题
        getSupportActionBar().hide();
        //去掉最上面时间、电量等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this,"a50a9b783b262ebbb63dfe1c8bf0b140");
        //Bmob.initialize(this, "168e402ba4c356b2e90c1548b4deb249");
        videoView = (CustomVideoView)findViewById(R.id.vedioview);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.loginbackground));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
        findViewById(R.id.tv_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.setPassword("toldyou87");
                user.setMobilePhoneNumber("17368835414");
                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
bt_login = (Button)findViewById(R.id.btn_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MActivity.class);
                startActivity(intent);
            }
        });
}
}