package com.example.firstdesign;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {
    private static List<Goods> mGoodsList;
    private String mObjectId;
@NonNull
    //创建实例，将goodsitem布局传入到构造函数中
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
//对子项数据进行赋值
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Goods good=mGoodsList.get(position);
        holder.goods_photo.setBackgroundResource(good.getG_photo());
        holder.goods_name.setText(good.getG_name());
        String price = good.getG_price()+"";
        holder.goods_price.setText(price);
        String sales = good.getG_sales()+"万+条评价 99%好评";
        holder.goods_sales.setText(sales);
        holder.goods_shop.setText(good.getG_shop());
        //对定义的点击接口回调
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接在里面填写即可，完成对该goods_item中控件的使用
                if (listener != null) {
                    listener.onClick(position);
                }
}
        });
holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onClick(position);
                }
                return true;
            }
        });
holder.goods_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果需要在外面定义的话 才需要下面方法
                if (clickListener != null) {
                    clickListener.onClick(position);
                }
}
        });
    }
/**
     * 新增一个对象
     */
//    private void save(CarGoods carGoods,View v) {
//
//        carGoods.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if (e == null) {
//                    mObjectId = objectId;
//                    Toast.makeText(v.getContext(), "加入购物车成功：" , Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(v.getContext(), "加入购物车失败：" , Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
@Override
    public int getItemCount() {
        return mGoodsList.size();
    }
static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView goods_photo;
        TextView goods_name;
        TextView goods_price;
        TextView goods_sales;
        TextView goods_shop;
        ImageView goods_buy;
public ViewHolder(@NonNull View view) {
            super(view);
            goods_photo= itemView.findViewById(R.id.goods_item_photo);
            goods_name=view.findViewById(R.id.goods_item_name);
            goods_price=view.findViewById(R.id.goods_item_price);
            goods_sales = view.findViewById(R.id.goods_item_shop);
            goods_shop=view.findViewById(R.id.goods_item_shop);
            goods_buy=view.findViewById(R.id.goods_item_shopcar);
}
    }
public GoodsListAdapter(List<Goods> goodsList){
        mGoodsList=goodsList;
    }
//第一步 定义点击接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
private OnItemClickListener listener;
//第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
//定义点击事件的接口
    public interface  OnClickBuyListener{
        void onClick(int position);
    }
    private OnClickBuyListener clickListener;
    //写公共方法
    public void setClickListener(OnClickBuyListener listener1){
        this.clickListener=listener1;
    }
//定义长按的接口
    public interface OnItemLongClickListener {
        void onClick(int position);
    }
private OnItemLongClickListener longClickListener;
public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
