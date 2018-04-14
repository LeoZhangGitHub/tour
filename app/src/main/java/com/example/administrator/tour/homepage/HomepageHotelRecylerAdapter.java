package com.example.administrator.tour.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tour.BrowseData;
import com.example.administrator.tour.MyRecyclerAdapter;
import com.example.administrator.tour.R;
import com.example.administrator.tour.getImageViewSourceId;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class HomepageHotelRecylerAdapter  extends RecyclerView.Adapter<HomepageHotelRecylerAdapter.MyViewHolder> {

    private List<Data> dataList;

    private LayoutInflater layoutInflater;

    public HomepageHotelRecylerAdapter(Context context, List<Data> dataList) {
            this.dataList = dataList;
            layoutInflater = LayoutInflater.from(context);
            }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.activity_hotel, parent, false);
            return new MyViewHolder(itemView);
            }

    public interface OnRecyclerViewListener{
        void onItemClick(View view, int position); //单击

        void onItemLongClick(View view, int position); //长按
    }

        private HomepageHotelRecylerAdapter.OnRecyclerViewListener onRecyclerViewListener;

        public void setOnRecyclerViewListener(HomepageHotelRecylerAdapter.OnRecyclerViewListener mOnItemClickListener) {
            this.onRecyclerViewListener = mOnItemClickListener;
        }

        @Override
        public void onBindViewHolder(final HomepageHotelRecylerAdapter.MyViewHolder holder, int position) {
            holder.hotel_title.setText(dataList.get(position).getTitle());
            holder.hotel_price.setText("RMB: "+dataList.get(position).getPrice());

            int id = getImageViewSourceId.getInstance().getResId(
                    dataList.get(position).getImageView(), R.mipmap.class);

            holder.hotel_imageView.setImageResource(id);

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

        private TextView hotel_title;
        private TextView hotel_price;
        private ImageView hotel_imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            hotel_title = (TextView) itemView.findViewById(R.id.hotel_title);
            hotel_price = (TextView) itemView.findViewById(R.id.hotel_price);
            hotel_imageView = (ImageView) itemView.findViewById(R.id.head_img);
        }
    }

    }

