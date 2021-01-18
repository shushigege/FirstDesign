package com.example.firstdesign.shoppingcar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstdesign.R;
import java.util.ArrayList;
public class CarGoodsAdapter extends RecyclerView.Adapter<com.example.firstdesign.shoppingcar.CarGoodsAdapter.ViewHolder> implements CompoundButton.OnCheckedChangeListener {
private ArrayList<CarGoods> mCarsList;
    private int num=1;
//选中按钮
    @Override
    public void onCheckedChanged(CompoundButton checkBox, boolean checked) {
        switch (checkBox.getId()){
            case R.id.cart_check:
                if (checked) {
                    System.out.println("已选中");
                    //mCarsList.get(i).setC_checked();
                }else {
                    //System.out.println("第一次被选中");
                }
                break;
            default:
                break;
        }
    }
//    public void setCheck(int postion){
//        mCarsList.get(postion).setC_checked();
//    }
static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView car_photo;
        TextView car_name;
        TextView car_price;
        TextView car_type;
        TextView car_num;
        ImageView car_jia,car_jian;//加，减
        CheckBox car_check;//是否选中
public ViewHolder(@NonNull View view) {
            super(view);
            //绑点控件
            car_photo = view.findViewById(R.id.cart_photo);
            car_name = view.findViewById(R.id.cart_name);
            car_type = view.findViewById(R.id.cart_type);
            car_price = view.findViewById(R.id.cart_price);
            car_num = view.findViewById(R.id.cart_num);
            car_jian = view.findViewById(R.id.cart_jian);
            car_jia = view.findViewById(R.id.cart_jia);
            car_check = view.findViewById(R.id.cart_check);
        }
    }
public CarGoodsAdapter(ArrayList<CarGoods> cargoodsList){
        mCarsList=cargoodsList;
    }
@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }
@Override
    public void onBindViewHolder(@NonNull final com.example.firstdesign.shoppingcar.CarGoodsAdapter.ViewHolder holder, int position) {
        final CarGoods good=mCarsList.get(position);
        holder.car_photo.setBackgroundResource(good.getG_photo());
        holder.car_name.setText(good.getG_name());
        String price = good.getG_price()+"";
        holder.car_price.setText(price);
        holder.car_type.setText(good.getG_type());
        System.out.println("test:"+good.getC_num());
//        holder.car_num.setText(good.getC_num());
        //加号设置
        holder.car_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                good.setC_numUp(1);
                int num = good.getC_num();
                System.out.println("test1:"+good.getC_num());
                holder.car_num.setText(String.valueOf(num ));
            }
        });
        //减号
        holder.car_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (good.getC_num()==1)
                {
                    Toast.makeText(v.getContext(),"不能再减啦~",Toast.LENGTH_SHORT).show();
                }else {
                    good.setC_numUp(-1);
                    int num = good.getC_num();
                    System.out.println("test1:" + good.getC_num());
                    holder.car_num.setText(String.valueOf(num));
                }
            }
        });
holder.car_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果需要在外面定义的话 才需要下面方法
                if (clickListener != null) {
                    clickListener.onClick(position);
                }
}
        });
//        holder.car_check.setOnCheckedChangeListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        })
//
    }
@Override
    public int getItemCount() {
        return mCarsList.size();
    }
//定义点击事件的接口
    public interface  OnClickBuyListener{
        void onClick(int position);
    }
    private com.example.firstdesign.shoppingcar.CarGoodsAdapter.OnClickBuyListener clickListener;
//写公共方法
    public void setClickListener(com.example.firstdesign.shoppingcar.CarGoodsAdapter.OnClickBuyListener listener1){
        this.clickListener=listener1;
    }
    //定义点击check事件接口
    public interface  OnCheckChangeListener{
        void onClick( int position,boolean checked);
    }
    private com.example.firstdesign.shoppingcar.CarGoodsAdapter.OnCheckChangeListener checkedChangeListener;
    public void setCheckClickListener(com.example.firstdesign.shoppingcar.CarGoodsAdapter.OnCheckChangeListener listener1){
        this.checkedChangeListener=listener1;
    }
}
