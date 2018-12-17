package soexample.umeng.com.weekthree.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soexample.umeng.com.weekthree.MyParticulars;
import soexample.umeng.com.weekthree.R;
import soexample.umeng.com.weekthree.bean.MyData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MyData.DataBean> mList;
    private Context context;
    private int flag = 1;
    private View v;
    private View v1;


    public void setOnClickCallBack(ItemClickCallBack itemClickCallBack) {
        this.clickCallBack = itemClickCallBack;
    }

    public interface ItemClickCallBack {
        void onItemClick(int pos);
    }

    public MyAdapter(ArrayList<MyData.DataBean> datas, int flag) {
        this.datas = datas;
        this.flag = flag;
    }

    public ArrayList<MyData.DataBean> datas = null;
    private ItemClickCallBack clickCallBack;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //传递上下文参数
        this.context = viewGroup.getContext();
        if (flag == 1) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groud, viewGroup, false);

        }

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mtext.setText(datas.get(i).getTitle());
        viewHolder.jiage.setText(datas.get(i).getPrice() + "");
        String url = datas.get(i).getImages();

        if (!url.isEmpty()) {
            String[] split = url.split("\\|");
            if (split != null) {
                for (int j = 0; j < split.length; j++) {
                    String replace = split[j].replace("https", "http");
                    Glide.with(context).load(replace).into(viewHolder.iv);
                }
            } else {
                url.replace("https", "http");
                Log.e("aaaa", url.toString());
                Glide.with(context).load(url).into(viewHolder.iv);

            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyParticulars.class);
                intent.putExtra("img", datas.get(i).getImages());
                intent.putExtra("price", datas.get(i).getPrice());
                intent.putExtra("title", datas.get(i).getTitle());
                context.startActivity(intent);

            }
        });

        //进行监听
        viewHolder.mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyParticulars.class);
                intent.putExtra("img", datas.get(i).getImages());
                intent.putExtra("price", datas.get(i).getPrice());
                intent.putExtra("title", datas.get(i).getTitle());
                context.startActivity(intent);
                if (clickCallBack != null) {
                    clickCallBack.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtext;
        private ImageView iv;
        private TextView jiage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.My_Img);
            mtext = itemView.findViewById(R.id.text);
            jiage = itemView.findViewById(R.id.jiage);
        }
    }


}
