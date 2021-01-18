package com.example.firstdesign.shopping;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstdesign.Goods;
import com.example.firstdesign.R;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MainMarketFragment extends Fragment  implements ViewPager.OnPageChangeListener, View.OnClickListener{
private ViewPager viewPager;
    private List<Fragment> fragments;
    private TextView tv_wash,tv_study,tv_phone,tv_food,tv_else;
    private String mObjectId;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_main_market, container, false);
        viewPager=view.findViewById(R.id.layout_icon_market);
        tv_wash=view.findViewById(R.id.tv_wash_market);
        tv_study=view.findViewById(R.id.tv_study_market);
        tv_phone=view.findViewById(R.id.tv_phone_market);
        tv_food=view.findViewById(R.id.tv_food_market);
        tv_else=view.findViewById(R.id.tv_else_market);
        fragments=new ArrayList<Fragment>();
        fragments.add(new washFragment());
        fragments.add(new studyFragment());
        fragments.add(new phoneFragment());
        fragments.add(new foodFragment());
        fragments.add(new elseFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return  fragments.get(position);
            }
@Override
            public int getCount() {
                return  fragments.size();
            }
        });
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0,true);// 默认选择一
//先上传数据测试
//        save();
        //System.out.println("图片数据:"+R.drawable.aa01);
        //query();
        return view;
}
//Bmob批量保存数据
    private void save() {
        List<BmobObject> goodsList = new ArrayList<>();
goodsList.add(new Goods(9, R.drawable.aa01, "蓝月亮", "10000mAh 快充版", 79.00, 2800, "小米京东自营旗舰店",1));
        goodsList.add(new Goods(10, R.drawable.aa02, "汰渍", "20000mAh USB—C双向快充版", 129.00, 21000, "小米京东自营旗舰店",1));
        goodsList.add(new Goods(11, R.drawable.aa03, "超能", "20000mAh", 59.00, 3000, "罗马仕京东自营旗舰店",1));
        goodsList.add(new Goods(12, R.drawable.aa04, "CLEAN", "1.5匹 一级能效", 99.00, 14000, "格力京东自营旗舰店",1));
        goodsList.add(new Goods(13, R.drawable.aa05, "普通毛巾", "10000mAh 快充版", 29.00, 2800, "小米京东自营旗舰店",2));
        goodsList.add(new Goods(14, R.drawable.aa06, "加绒毛巾", "20000mAh USB—C双向快充版", 59.00, 21000, "小米京东自营旗舰店",2));
        goodsList.add(new Goods(15, R.drawable.aa07, "厨房毛巾", "20000mAh", 20.00, 3000, "罗马仕京东自营旗舰店",2));
        goodsList.add(new Goods(16, R.drawable.aa08, "浴巾", "1.5匹 一级能效", 66.00, 14000, "格力京东自营旗舰店",2));
new BmobBatch().insertBatch(goodsList).doBatch(new QueryListListener<BatchResult>() {
@Override
            public void done(List<BatchResult> results, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < results.size(); i++) {
                        BatchResult result = results.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {
                            System.out.println("上传成功");
                        } else {
                            System.out.println("上传成功");
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//Bmob查数据
    private void query() {
        BmobQuery<Goods> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject("5768e42dbf", new QueryListener<Goods>() {
            @Override
            public void done(Goods goods, BmobException e) {
                if (e == null) {
                    System.out.println("图片"+goods.getG_photo());
                    Drawable drawable = ContextCompat.getDrawable(getContext(),goods.getG_photo());
                    System.out.println("drawable"+drawable);
                    tv_else.setBackground(drawable);
                } else {
                }
            }
        });
    }
@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_wash.setOnClickListener(this);
        tv_study.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_food.setOnClickListener(this);
        tv_else.setOnClickListener(this);
    }
@Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
}
@Override
    public void onPageSelected(int position) {
}
@Override
    public void onPageScrollStateChanged(int state) {
}
@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_wash_market:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.tv_study_market:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.tv_phone_market:
                viewPager.setCurrentItem(2,true);
                break;
            case R.id.tv_food_market:
                viewPager.setCurrentItem(3,true);
                break;
            case R.id.tv_else_market:
                viewPager.setCurrentItem(4,true);
                break;
            default:
                break;
        }
    }
}