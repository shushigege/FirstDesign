package com.example.firstdesign.shoppingcar;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstdesign.Dingdan;
import com.example.firstdesign.R;
import com.example.firstdesign.activity.DingDanActivity;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
public class ShopCarFragment extends Fragment {
    private CarGoodsAdapter carGoodsAdapter;
    private ArrayList<CarGoods> cargoodsArrayList;
    private RecyclerView recyclerView;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btn_jiesuan,btn_delete;
    private TextView tv_price;
    private CheckBox checkBox_allSelect;
    private double  totalPrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_shop_car, container, false);
        recyclerView=view.findViewById(R.id.recycler_view_car);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        btn_jiesuan=view.findViewById(R.id.btn_jiesuan);
        tv_price=view.findViewById(R.id.car_price);
        btn_delete=view.findViewById(R.id.btn_delete);
//        tv_price.setText((int) totalPrice);
        checkBox_allSelect=view.findViewById(R.id.btn_allSelect);
        swipeRefreshLayout=view.findViewById(R.id.main_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                //获取完成,将刷新标志去除
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        initData();
        //设置计算的按钮
//        btn_jiesuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent intent =new Intent();
//            }
//        });
        checkBox_allSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //点击了全选之后
                    for (int i=0;i<cargoodsArrayList.size();i++) {
                        cargoodsArrayList.get(i).setC_checked("1");

                    }
                    //tv_price.setText((int) );
                    jisuanzhonge();
                    String temp=""+totalPrice;
                    tv_price.setText(temp);
                }else{
                    for (int i=0;i<cargoodsArrayList.size();i++) {
                        cargoodsArrayList.get(i).setC_checked("0");
                    }
                    jisuanzhonge();

                    String temp=""+totalPrice;
                    tv_price.setText(temp);}
            }
        });
        btn_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), DingDanActivity.class);
                //Dingdan dingdan=new Dingdan(cargoodsArrayList,1,totalPrice,1);
                Dingdan dingdan = new Dingdan(cargoodsArrayList,1,totalPrice,1);
                intent.putExtra("dingdanData",dingdan);
                startActivity(intent);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BmobObject> categories = new ArrayList<>();
                for(int i=0;i<cargoodsArrayList.size();i++)
                {
                    if (cargoodsArrayList.get(i).getC_checked().equals("1")){
                        CarGoods bmobObject=new CarGoods();
                        bmobObject.setObjectId(cargoodsArrayList.get(i).getObjectId());
                        categories.add(bmobObject);
                    }
                }
                new BmobBatch().deleteBatch(categories).doBatch(new QueryListListener<BatchResult>() {
                    @Override
                    public void done(List<BatchResult> results, BmobException e) {
                        if (e == null) {
                            for (int i = 0; i < results.size(); i++) {
                                BatchResult result = results.get(i);
                                BmobException ex = result.getError();
                                if (ex == null) {
                                    Toast.makeText(getActivity(), "数据批量删除成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "数据批量删除失败", Toast.LENGTH_SHORT).show();
                                    System.out.println("删除失败"+ex);
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(),   "个数据批量删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }
    private  double jisuanzhonge()
    {
        totalPrice=0;
        for (int i=0;i<cargoodsArrayList.size();i++){
            System.out.println("选中情况"+cargoodsArrayList.get(i).getC_checked());
            if (cargoodsArrayList.get(i).getC_checked().equals("1")){
                System.out.println("选中的价格"+cargoodsArrayList.get(i).getG_price()+"选中的数量"+cargoodsArrayList.get(i).getC_num());
                totalPrice=totalPrice+cargoodsArrayList.get(i).getC_num()*cargoodsArrayList.get(i).getG_price();
            }
        }
        return totalPrice;
    }
    public void initData()
    {
        //查询
        cargoodsArrayList=new ArrayList<CarGoods>();
        BmobQuery<CarGoods> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("c_userId", 1);
        bmobQuery.findObjects(new FindListener<CarGoods>() {
            @Override
            public void done(List<CarGoods> carGoods, BmobException e) {
                if (e == null) {
                    //                   Toast.makeText(getActivity(), "查询成功：" + carGoods.size(), Toast.LENGTH_SHORT).show();
                    for (int i=0;i<carGoods.size();i++) {
                        cargoodsArrayList.add(carGoods.get(i));
                    }
                    carGoodsAdapter =new CarGoodsAdapter(cargoodsArrayList);
                    recyclerView.setAdapter(carGoodsAdapter);
                    carGoodsAdapter.setClickListener(new CarGoodsAdapter.OnClickBuyListener() {
                        @Override
                        public void onClick(int position) {
                            System.out.println("点击了第几个"+position);
                            if (cargoodsArrayList.get(position).getC_checked().equals("0")){
                                cargoodsArrayList.get(position).setC_checked("1");
                            }else{
                                cargoodsArrayList.get(position).setC_checked("0");
                            }
                            //每点击一次就计算一次总额
                            //tv_price.setText("100");
                            System.out.println("计算结果"+jisuanzhonge());
                            String temp=""+totalPrice;
                            tv_price.setText(temp);
                        }
                    } );
                } else {
                    Toast.makeText(getActivity(), "查询时出错了"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        goodsArrayList = new ArrayList<CarGoods>();
//        goodsArrayList.add(new CarGoods(9, R.drawable.aa01, "蓝月亮", "10000mAh 快充版", 79.00, 1, "小米京东自营旗舰店",1));
//        goodsArrayList.add(new CarGoods(10, R.drawable.aa02, "汰渍", "20000mAh USB—C双向快充版", 129.00, 1, "小米京东自营旗舰店",1));
//        goodsArrayList.add(new CarGoods(11, R.drawable.aa03, "超能", "20000mAh", 59.00, 1, "罗马仕京东自营旗舰店",1));
        //goodsArrayList.add(new CarGoods(12, R.drawable.aa04, "CLEAN", "1.5匹 一级能效", 99.00, 14000, "格力京东自营旗舰店"));
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String temp=""+totalPrice;
        tv_price.setText(temp);
    }
}