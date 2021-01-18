package com.example.firstdesign.mainpage;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.firstdesign.R;
import com.example.firstdesign.activity.MActivity;
import com.example.firstdesign.activity.SettingsActivity;
import com.example.firstdesign.activity.webview.GongYiWebview;
import com.example.firstdesign.activity.webview.MaoYanWebview;
import com.example.firstdesign.activity.webview.TaoBaoWebview;
import com.example.firstdesign.activity.webview.TianQiWebview;
import com.example.firstdesign.activity.webview.TongChengWebview;
import com.example.firstdesign.activity.webview.WaiMaiWebview;
import com.example.firstdesign.adapter.GlideImageLoader;
import com.example.firstdesign.base.BaseFragment;
import com.example.firstdesign.moments.AddShuoShuo;
import com.example.firstdesign.moments.MomentFragment;
import com.example.firstdesign.personal.PersonalFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;
import cn.bmob.v3.Bmob;
import de.hdodenhof.circleimageview.CircleImageView;
public class MainPageFragment extends Fragment {
    private CircleImageView touxiang;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private View mView;
    private boolean context;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_page, container, false);
        setHasOptionsMenu(true);
        List<String> images = new ArrayList<>();
        images.add("https://i.loli.net/2020/11/11/g896fi5kmKDXzjo.jpg");
        images.add("https://i.loli.net/2020/11/11/35ybIWVjKiw1puS.jpg");
        images.add("https://i.loli.net/2020/11/16/19TnzDt4XlFYkxW.jpg");
        images.add("https://i.loli.net/2020/11/11/O7unC6qs34pdcI9.jpg");
        List<String> list_title = new ArrayList<>();
        list_title.add("实验综合楼");
        list_title.add("相约九月桥");
        list_title.add("易海静夜景");
        list_title.add("俯瞰宿舍楼");
        Banner banner = (Banner) mView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.ahjzu.edu.cn"));
                startActivity(intent);
            }
        });
/**此处可以跳转到一个webview的一个活动 等我找到合适的wedview 吧
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //Intent intent =new Intent("");
                //startActivity(intent);
            }
        });**/
        //ToolBar代替ActionBar
        toolbar= (Toolbar) mView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // 将ToolBar实例传入
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.more);// 设置导航图标
        }
        mDrawerLayout = (DrawerLayout)mView.findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView)mView.findViewById(R.id.nav_view);
        final View headView = navigationView.inflateHeaderView(R.layout.nav_header);
        touxiang = headView.findViewById(R.id.icon_touxiang);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**code code code code code code code code
                 * 可以添加对头像的操作   跳转到个人主界面 等我写完个人主界面再说吧
                 * */
            }
        });
        navigationView.setCheckedItem(R.id.nav_shop);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_moment:
                       Intent intent1 = new Intent(getActivity(), MActivity.class);
                       startActivity(intent1);
                       //getActivity().finish();
                       getActivity().getSupportFragmentManager()
                               .beginTransaction()
                               .replace(R.id.M,new MomentFragment())
                               .addToBackStack(null)
                               .commit();
                       getActivity().finish();
                        break;
                    case R.id.nav_personal:
                        Intent intent3 = new Intent(getContext(), PersonalFragment.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        return true;
                       // break;
                    case R.id.nav_setting:
                        Intent intent2 = new Intent(getActivity(), SettingsActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
/**
         * 悬浮按钮的操作
         * */
        FloatingActionButton floatingActionButton = (FloatingActionButton)mView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "删除数据？", Snackbar.LENGTH_SHORT)
                        //设置undo按钮
                        .setAction("重置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    Toast.makeText(getActivity(),"数据已重置", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
mView.findViewById(R.id.gongyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GongYiWebview.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.waimai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WaiMaiWebview.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.taobao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaoBaoWebview.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.lvxing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TongChengWebview.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.maoyan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MaoYanWebview.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.tianqi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TianQiWebview.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view);
        //RecyclerView recyclerView1 = mView.findViewById(R.id.recleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView1.setLayoutManager(layoutManager1);
        Glide.with(getActivity()).load("https://i.loli.net/2020/11/16/NlTviBcgF7oxSKs.jpg").into((ImageView)mView.findViewById(R.id.yihailiangting));
        Glide.with(getActivity()).load("https://i.loli.net/2020/11/16/vC59SlaI3Q26YtE.jpg").into((ImageView)mView.findViewById(R.id.fensejianda));
        return mView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }
    //加载toolbar文件
//把toolbar中的item导入，，，插入到toolBar中，R.menu.toolbar中间都是menuRes资源
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // Inflater.inflate(R.menu.toolbar,menu);
        inflater.inflate(R.menu.toolbar,menu);
        //return true;
    }
    @Override//传入item，，，确定哪个item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android .R.id.home://滑动菜单显示键
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}