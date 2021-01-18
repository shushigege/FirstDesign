package com.example.firstdesign.moments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.example.firstdesign.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
public class MomentFragment extends Fragment {
private View mView;
    private RecyclerView recyclerView;
    List<Content> contentList = new ArrayList<>();
    private Handler handler = new Handler(){
      @Override
        public void handleMessage(Message msg){
          switch (msg.what){
              case 0:
                  contentList = (List<Content>)msg.obj;
                  ContentAdapter adapter = new ContentAdapter(contentList,getActivity());
                  recyclerView.setAdapter(adapter);
          }
      }
};
@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScreenManager.getScreenManager().pushActivity(getActivity());
        mView = inflater.inflate(R.layout.fragment_moment, container, false);
        //Bmob.initialize(getActivity(),"168e402ba4c356b2e90c1548b4deb249");
        Bmob.initialize(getActivity(), "a50a9b783b262ebbb63dfe1c8bf0b140");
        recyclerView= (RecyclerView) mView.findViewById(R.id.recleView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        requestData();
        Log.d("MainActivity", contentList.equals(null) + "==" + contentList.size());
        FloatingActionButton fab= (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "into addShuoshuo");
                Intent intent=new Intent(getActivity(), AddShuoShuo.class);
                startActivity(intent);
                //getActivity().finish();
               // finish();
            }
        });
//        mView.findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//              //  finish();
//            }
//        });
        return mView;
    }
@Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }
private void requestData(){//利用handler.setMessage()方法传出讯息
        final BmobQuery<Content> query=new BmobQuery<Content>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Content>() {
            @Override
            public void done(List<Content> list, BmobException e) {
                if (e == null) {
                    Message message = handler.obtainMessage();
                    message.what = 0;
                    //以消息为载体
                    message.obj = list;//查询出的lsit
                    handler.sendMessage(message);
                }
            }
        });
    }
}