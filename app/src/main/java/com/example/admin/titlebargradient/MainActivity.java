package com.example.admin.titlebargradient;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * CCB 版权所有 翻版必究 嘻嘻嘻
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private TextView tv;
    private List<String> data;
    private int height = 640;  // 滑动到什么地方完全变色
    private int ScrollUnm = 0;  //滑动的距离总和

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        tv = findViewById(R.id.tv);
        initData();
    }

    private void initData() {
        data = Arrays.asList("嗯嗯","啊啊","哦哦","呵呵","嘻嘻","哈哈","嘎嘎","嘿嘿","哼哼","哇哇","啪啪","嗝");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(new MyAdapter(this,data));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ScrollUnm = ScrollUnm + dy; //滑动距离总合
                Log.i("dy",dy+"");
                Log.i("overallXScroll",ScrollUnm+"");
                if (ScrollUnm<=0){  //在顶部时完全透明
                    tv.setBackgroundColor(Color.argb((int) 0, 255,41,76));
                }else if (ScrollUnm>0&&ScrollUnm<=height){  //在滑动高度中时，设置透明度百分比（当前高度/总高度）
                    double d = (double) ScrollUnm / height;
                    double alpha = (d*255);
                    tv.setBackgroundColor(Color.argb((int) alpha, 255,41,76));
                }else{ //滑出总高度 完全不透明
                    tv.setBackgroundColor(Color.argb((int) 255, 255,41,76));
                }
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.Vh>{
        private List<String> datas;
        private Context context;
        public MyAdapter(Context context,List datas) {
            this.datas = datas;
            this.context = context;
        }

        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context,R.layout.layout_item,null);
            return new Vh(view);
        }

        @Override
        public void onBindViewHolder(Vh holder, int position) {
          holder.tv.setText(datas.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class Vh extends RecyclerView.ViewHolder{
          TextView tv;
            public Vh(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
