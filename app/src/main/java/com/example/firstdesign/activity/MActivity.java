package com.example.firstdesign.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.example.firstdesign.R;
import com.example.firstdesign.base.BaseFragment;
import com.example.firstdesign.mainpage.MainPageFragment;
import com.example.firstdesign.moments.MomentFragment;
import com.example.firstdesign.personal.PersonalFragment;
import com.example.firstdesign.shopping.MainMarketFragment;
import com.example.firstdesign.shoppingcar.ShopCarFragment;
import java.util.ArrayList;
import java.util.List;
public class MActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager pager;
    private List<Fragment> fragments;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        initView();
    }
    private void initView() {
        pager = (ViewPager) findViewById(R.id.package_vPager);
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
        findViewById(R.id.rb_home).setOnClickListener(this);
        findViewById(R.id.rb_moment).setOnClickListener(this);
        findViewById(R.id.rb_user).setOnClickListener(this);
        findViewById(R.id.rb_shop).setOnClickListener(this);
        findViewById(R.id.rb_cart).setOnClickListener(this);
        fragments = new ArrayList<Fragment>();
        fragments.add(new MainPageFragment());
        fragments.add(new MainMarketFragment());
        fragments.add(new MomentFragment());
        fragments.add(new ShopCarFragment());
        fragments.add(new PersonalFragment());
        pager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }
            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });
        // 添加页面切换事件的监听器
        pager.setOnPageChangeListener(this);
        radioGroup.check(R.id.rb_home);
    }
    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
    /**
     * 这个方法在手指操作屏幕的时候发生变化。有三个值：0（END）,1(PRESS) , 2(UP) 。
     * 当用手指滑动翻页时，手指按下去的时候会触发这个方法，state值为1，手指抬起时，
     * 如果发生了滑动（即使很小），这个值会变为2，然后最后变为0
     * 。总共执行这个方法三次。一种特殊情况是手指按下去以后一点滑动也没有发生，
     * 这个时候只会调用这个方法两次，state值分别是1,0 。
     * 当setCurrentItem翻页时，会执行这个方法两次，state值分别为2 , 0 。
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    /**
     * 这个方法会在屏幕滚动过程中不断被调用。 有三个参数，第一个position，这个参数要特别注意一下。
     * 当用手指滑动时，如果手指按在页面上不动，position和当前页面index是一致的；
     * 如果手指向左拖动（相应页面向右翻动），这时候position大部分时间和当前页面是一致的，
     * 只有翻页成功的情况下最后一次调用才会变为目标页面；如果手指向右拖动（相应页面向左翻动），
     * 这时候position大部分时间和目标页面是一致的，只有翻页不成功的情况下最后一次调用才会变为原页面。
     * 当直接设置setCurrentItem翻页时，如果是相邻的情况（比如现在是第二个页面，跳到第一或者第三个页面），
     * 如果页面向右翻动，大部分时间是和当前页面是一致的，只有最后才变成目标页面；
     * 如果向左翻动，position和目标页面是一致的。这和用手指拖动页面翻动是基本一致的。如果不是相邻的情况，
     * 比如我从第一个页面跳到第三个页面，position先是0，然后逐步变成1，然后逐步变成2；
     * 我从第三个页面跳到第一个页面，position先是1，然后逐步变成0，并没有出现为2的情况。
     * positionOffset是当前页面滑动比例，如果页面向右翻动，这个值不断变大，最后在趋近1的情况后突变为0
     * 。如果页面向左翻动，这个值不断变小，
     * 最后变为0。positionOffsetPixels是当前页面滑动像素，变化情况和positionOffset一致
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    /**
     * 这个方法有一个参数position，代表哪个页面被选中。当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），
     * 手指抬起来就会立即执行这个方法，position就是当前滑动到的页面。如果直接setCurrentItem翻页，
     * 那position就和setCurrentItem的参数一致，这种情况在onPageScrolled执行方法前就会立即执行。
     */
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.rb_home);
                break;
            case 1:
                radioGroup.check(R.id.rb_shop);
                break;
case 2:
                radioGroup.check(R.id.rb_moment);
                break;
case 3:
                radioGroup.check(R.id.rb_cart);
                break;
            case 4:
                radioGroup.check(R.id.rb_user);
                break;
            default:
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                pager.setCurrentItem(0,true);
                break;
            case R.id.rb_shop:
                pager.setCurrentItem(1,true);
                break;
            case R.id.rb_moment:
                pager.setCurrentItem(2,true);
                break;
            case R.id.rb_cart:
                pager.setCurrentItem(3,true);
                break;
            case R.id.rb_user:
                pager.setCurrentItem(4,true);
                break;
            default:
                break;
        }
    }
}
