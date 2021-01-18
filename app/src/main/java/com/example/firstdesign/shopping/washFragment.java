package com.example.firstdesign.shopping;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.firstdesign.Goods;
import com.example.firstdesign.GoodsListAdapter;
import com.example.firstdesign.R;
import com.example.firstdesign.activity.goodShowActivity;
import com.example.firstdesign.shoppingcar.CarGoods;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
public class washFragment extends Fragment  implements View.OnClickListener{
private int[] ids1 = {R.drawable.a105, R.drawable.a104, R.drawable.a103, R.drawable.a102, R.drawable.a101, R.drawable.a100};
    private GoodsListAdapter goodsListAdapter;
    private ArrayList<Goods> goodsArrayList,goodsArrayList2;
    private RecyclerView recyclerView,recyclerView2;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView_shopcar;
    private  View view;
    private List<Goods> findList,test;
    private BmobQuery<Goods> goodsBmobQuery ;
    private  int[] first={0,0,0,0};
    private int  userid=1;
    //private  Boolean isJoinCar=false;
    private GoodsListAdapter goodsListAdapter2;
    private  ArrayList isJoinCar=new ArrayList();
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.fragment_wash, container, false);
        imageView1=view.findViewById(R.id.image_market1);
        imageView2=view.findViewById(R.id.image_market2);
        imageView3=view.findViewById(R.id.image_market3);
        imageView4=view.findViewById(R.id.image_market4);
        imageView5=view.findViewById(R.id.image_market5);
        imageView6=view.findViewById(R.id.image_market6);
        recyclerView=view.findViewById(R.id.layout_show_wash);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        System.out.println("createview运行完了");
        return  view;
    }
@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        //goodsArrayList=new ArrayList<Goods>();
    }
@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_market1:
                initData(1);//初始化数据
                // System.out.println("goodsArrayList"+goodsArrayList.get(0).getG_name());
                System.out.println("时间1"+System.currentTimeMillis());
                //joinCar();
break;
            case R.id.image_market3:
                initData(2);//初始化数据
                //joinCar();
//                goodsListAdapter =new GoodsListAdapter(goodsArrayList2);
//                recyclerView.setAdapter(goodsListAdapter);
//                goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
//                    @Override
//                    public void onClick(int position) {
//
//                        System.out.println("点击了第："+position);
//                        Intent intent=new Intent(getActivity(),Goods.class);
//                        intent.putExtra("good_data",goodsArrayList.get(position));
//                        startActivity(intent);
//                    }
//                });
                break;
            default:
                break;
        }
    }
void initData(int flag)
    {
imageView_shopcar=view.findViewById(R.id.goods_item_shopcar);
        switch (flag) {
            case 1:
                System.out.println("点击了图片一");
                //加判断是否要在查询一次
                if (first[flag]==0) {
                    goodsArrayList = new ArrayList<Goods>();
                    goodsBmobQuery = new BmobQuery<>();
                    goodsBmobQuery.addWhereEqualTo("classification", flag);
                    goodsBmobQuery.findObjects(new FindListener<Goods>() {
                        @Override
                        public void done(List<Goods> object, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "查询成功：" + object.size(), Toast.LENGTH_SHORT).show();
                                System.out.println("查询成功" + object.get(1).getG_name());
                                System.out.println("时间2" + System.currentTimeMillis());
                                for (int i = 0; i < object.size(); i++) {
                                    goodsArrayList.add(object.get(i));
                                }
                                setRecyclerView(flag);
                                first[flag]=1;
                            } else {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("查询失败" + e.getMessage());
                                //goodsArrayList = null;
                            }
                            System.out.println("内" + goodsArrayList.get(1).getG_name());
}
});
                }else {
                    setRecyclerView(flag);
                }
//equal(flag);
                //System.out.println("返回的测试结果"+findList.get(1).getG_name());
//                goodsArrayList.add(new Goods(9, R.drawable.aa01, "蓝月亮", "10000mAh 快充版", 79.00, 2800, "小米京东自营旗舰店",1));
//                goodsArrayList.add(new Goods(10, R.drawable.aa02, "汰渍", "20000mAh USB—C双向快充版", 129.00, 21000, "小米京东自营旗舰店",1));
//                goodsArrayList.add(new Goods(11, R.drawable.aa03, "超能", "20000mAh", 59.00, 3000, "罗马仕京东自营旗舰店",1));
//                goodsArrayList.add(new Goods(12, R.drawable.aa04, "CLEAN", "1.5匹 一级能效", 99.00, 14000, "格力京东自营旗舰店",1));
                break;
            case 2:
System.out.println("点击了图片一");
                if (first[flag]==0) {
                    goodsArrayList2 = new ArrayList<Goods>();
                    //equal(flag);
goodsBmobQuery = new BmobQuery<>();
                    goodsBmobQuery.addWhereEqualTo("classification", 2);
                    goodsBmobQuery.findObjects(new FindListener<Goods>() {
                        @Override
                        public void done(List<Goods> object, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "查询成功：" + object.size(), Toast.LENGTH_SHORT).show();
                                System.out.println("查询成功" + object.get(1).getG_name());
                                for (int i = 0; i < object.size(); i++) {
                                    goodsArrayList2.add(object.get(i));
                                    System.out.println("内2" + goodsArrayList2.get(i).getG_name());
                                }
                                setRecyclerView(flag);
                                first[flag]=1;
                            } else {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("查询失败" + e.getMessage());
                                //goodsArrayList2 = null;
                            }
                        }
                    });
                }else {
                    //什么都不用干
                    setRecyclerView(flag);
                }
//                goodsArrayList.add(new Goods(13, R.drawable.aa05, "普通毛巾", "10000mAh 快充版", 29.00, 2800, "小米京东自营旗舰店",2));
//                goodsArrayList.add(new Goods(14, R.drawable.aa06, "加绒毛巾", "20000mAh USB—C双向快充版", 59.00, 21000, "小米京东自营旗舰店",2));
//                goodsArrayList.add(new Goods(15, R.drawable.aa07, "厨房毛巾", "20000mAh", 20.00, 3000, "罗马仕京东自营旗舰店",2));
//                goodsArrayList.add(new Goods(16, R.drawable.aa08, "浴巾", "1.5匹 一级能效", 66.00, 14000, "格力京东自营旗舰店",2));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
}
}
private void swithList(int i)
    {
        switch (i){
            case  1:
                goodsListAdapter =new GoodsListAdapter(goodsArrayList);
                recyclerView.setAdapter(goodsListAdapter);
                goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(final int position) {
                        System.out.println("点击了第："+position);
                        Intent intent=new Intent(getActivity(), goodShowActivity.class);
                        intent.putExtra("good_data",goodsArrayList.get(position));
                        startActivity(intent);
                    }
                });
                joinCar(i);
//                goodsListAdapter.setClickListener(new GoodsListAdapter.OnClickBuyListener() {
//                    @Override
//                    public void onClick(int position) {
//                        System.out.println("点击了购物车");
//                        CarGoods carGoods=new CarGoods(goodsArrayList.get(position),userid,1,"true");
//                        save(carGoods);
//                    }
//                });
                break;
            case 2:
                goodsListAdapter2 =new GoodsListAdapter(goodsArrayList2);
                recyclerView.setAdapter(goodsListAdapter2);
                goodsListAdapter2.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(final int position) {
                        System.out.println("点击了第："+position);
                        Intent intent=new Intent(getActivity(), goodShowActivity.class);
                        intent.putExtra("good_data",goodsArrayList2.get(position));
                        startActivity(intent);
                    }
                });
                joinCar(i);
//                goodsListAdapter2.setClickListener(new GoodsListAdapter.OnClickBuyListener() {
//                    @Override
//                    public void onClick(int position) {
//                        System.out.println("点击了购物车");
//                        CarGoods carGoods=new CarGoods(goodsArrayList2.get(position),userid,1,"true");
//                        save(carGoods);
//                    }
//                });
break;
            default:
                break;
        }
    }
    private void setRecyclerView( int flag )
    {
System.out.println("时间3"+System.currentTimeMillis());
        swithList(flag);
    }
//Bmob查询,判断是否已经加入购物车了
    private void equal(int userid) {
        BmobQuery<CarGoods> goodsBmobQuery = new BmobQuery<>();
        goodsBmobQuery.addWhereEqualTo("c_userId", userid);
        goodsBmobQuery.findObjects(new FindListener<CarGoods>() {
            @Override
            public void done(List<CarGoods> object, BmobException e) {
                if (e == null) {
                    //Toast.makeText(getActivity(), "查询成功：" + object.size(), Toast.LENGTH_SHORT).show();
                    System.out.println("查询成功"+object.get(1).getG_name());
                    for (int i=0;i<object.size();i++){
                        isJoinCar.add(object.get(i).getG_id());
                    }
//return flag;
                } else {
                    //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("查询失败"+e.getMessage());
                    goodsArrayList=null;
                }
            }
        });
}
//加入购物车
    void joinCar(int flag){
        switch (flag){
            case 1:
                goodsListAdapter.setClickListener(new GoodsListAdapter.OnClickBuyListener() {
                    @Override
                    public void onClick(int position) {
                        System.out.println("点击了购物车");
                        CarGoods carGoods=new CarGoods(goodsArrayList.get(position),userid,1,"true");
                        save(carGoods);
                    }
                });
                break;
            case 2:
                goodsListAdapter2.setClickListener(new GoodsListAdapter.OnClickBuyListener() {
                    @Override
                    public void onClick(int position) {
                        System.out.println("点击了购物车");
                        CarGoods carGoods=new CarGoods(goodsArrayList2.get(position),userid,1,"true");
                        save(carGoods);
                    }
                });
                break;
            default:
                break;
}
}
private void save(CarGoods carGoods) {
        if (isJoinCar.contains(carGoods.getG_id())) {
            Toast.makeText(getActivity(), "该商品已经加入购物车：", Toast.LENGTH_SHORT).show();
        }else {
            carGoods.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        //mObjectId = objectId;
                        Toast.makeText(getActivity(), "加入购物车成功：", Toast.LENGTH_SHORT).show();
                        isJoinCar.add(carGoods.getG_id());
                    } else {
                        Toast.makeText(getActivity(), "加入购物车失败：", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}
    }
}