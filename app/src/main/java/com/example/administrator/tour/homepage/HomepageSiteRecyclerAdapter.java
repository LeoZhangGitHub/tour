package com.example.administrator.tour.homepage;

/**
 * Created by Administrator on 2018/4/3/003.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.administrator.tour.BrowseData;
import com.example.administrator.tour.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HomepageSiteRecyclerAdapter extends RecyclerView.Adapter<HomepageSiteRecyclerAdapter.MyViewHolder> {

    private List<Data> dataList;

    private LayoutInflater layoutInflater;

    private URL url;

    public HomepageSiteRecyclerAdapter(Context context, List<Data> dataList) {
        this.dataList = dataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_homepage_site, parent, false);
        return new MyViewHolder(itemView);
    }

    public interface OnRecyclerViewListener{
        void onItemClick(View view, int position); //单击

        void onItemLongClick(View view, int position); //长按
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener mOnItemClickListener) {
        this.onRecyclerViewListener = mOnItemClickListener;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.site_title.setText(dataList.get(position).getTitle());
        holder.site_price.setText(dataList.get(position).getPrice());
        holder.site_content.setText(dataList.get(position).getContent());
       // holder.imageView.setImageDrawable();

       /* try {
           url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523115695003&di=77bc6003204f3a4b0734d4ff7b35770c&imgtype=0&src=http%3A%2F%2Fpic29.photophoto.cn%2F20131204%2F0034034499213463_b.jpg");

        } catch (MalformedURLException e) {

        }
        holder.imageView.setImageBitmap(url);*/

        if (onRecyclerViewListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onRecyclerViewListener.onItemClick(holder.itemView,pos);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView site_title;
        private TextView site_price;
        private TextView site_content;
        private ImageView imageView;


        MyViewHolder(View itemView) {
            super(itemView);
            site_title = (TextView) itemView.findViewById(R.id.site_title);
            site_price = (TextView) itemView.findViewById(R.id.site_price);
            site_content = (TextView) itemView.findViewById(R.id.site_content);
            imageView = (ImageView) itemView.findViewById(R.id.head_img);
        }
    }

}
