package com.example.administrator.tour.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.tour.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class HomepageTrafficRecyclerAdapter extends RecyclerView.Adapter<HomepageTrafficRecyclerAdapter.MyViewHolder> {

    private List<Data> dataList;

    private LayoutInflater layoutInflater;

    public HomepageTrafficRecyclerAdapter(Context context, List<Data> dataList) {
            this.dataList = dataList;
            layoutInflater = LayoutInflater.from(context);
            }

    @Override
    public HomepageTrafficRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.activity_homepage_traffic, parent, false);
            return new HomepageTrafficRecyclerAdapter.MyViewHolder(itemView);
            }

    public interface OnRecyclerViewListener{
        void onItemClick(View view, int position); //单击

        void onItemLongClick(View view, int position); //长按
    }

        private HomepageTrafficRecyclerAdapter.OnRecyclerViewListener onRecyclerViewListener;

        public void setOnRecyclerViewListener(HomepageTrafficRecyclerAdapter.OnRecyclerViewListener mOnItemClickListener) {
            this.onRecyclerViewListener = mOnItemClickListener;
        }

        @Override
        public void onBindViewHolder(final HomepageTrafficRecyclerAdapter.MyViewHolder holder, int position) {
            holder.traffic_title.setText(dataList.get(position).getTitle());
            holder.traffic_price.setText(dataList.get(position).getPrice());


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

        private TextView traffic_title;

        private TextView traffic_price;

        MyViewHolder(View itemView) {
            super(itemView);
            traffic_title = (TextView) itemView.findViewById(R.id.traffic_title);
            traffic_price = (TextView) itemView.findViewById(R.id.traffic_price);
        }
    }

}
