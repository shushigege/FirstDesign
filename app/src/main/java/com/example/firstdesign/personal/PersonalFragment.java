package com.example.firstdesign.personal;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.firstdesign.R;
import com.example.firstdesign.activity.LoginActivity;
import com.example.firstdesign.activity.SettingsActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
public class PersonalFragment extends Fragment {
private View mView;
    private Toolbar toolbar;
    private ImageView mHBack;
    private ImageView mHHead;
    private LinearLayout change,setting,logout;
@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            mView = inflater.inflate(R.layout.fragment_personal, container, false);
            initView();
            setData();
return mView;
    }
@Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }
//加载toolbar文件
//把toolbar中的item导入，，，插入到toolBar中，R.menu.toolbar中间都是menuRes资源
    private void setData() {
//设置背景磨砂效果
        Glide.with(getActivity()).load(R.drawable.touxiang)
                .bitmapTransform(new BlurTransformation(getActivity(), 25), new CenterCrop(getActivity()))
                .into(mHBack);
        //设置圆形图像
        Glide.with(getActivity()).load(R.drawable.touxiang)
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(mHHead);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(PersonPage.this,ChangeMessageActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private void initView() {
        //顶部头像控件
        change=mView.findViewById(R.id.ll_change);
        toolbar=  mView.findViewById(R.id.toolbar);
        mHBack = (ImageView) mView.findViewById(R.id.h_back);
        mHHead = (ImageView) mView.findViewById(R.id.user_image);
        setting = mView.findViewById(R.id.ll_setting);
        logout = mView.findViewById(R.id.ll_logout);
       // mUserLine = (ImageView) findViewById(R.id.user_line);
       // mUserName = (TextView) findViewById(R.id.user_name);
        //mUserVal = (TextView) findViewById(R.id.user_val);
    }
}