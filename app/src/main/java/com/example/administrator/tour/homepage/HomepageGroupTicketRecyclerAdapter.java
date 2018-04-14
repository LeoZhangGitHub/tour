package com.example.administrator.tour.homepage;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tour.BrowseData;
import com.example.administrator.tour.R;
import com.example.administrator.tour.getImageViewSourceId;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class HomepageGroupTicketRecyclerAdapter extends RecyclerView.Adapter<HomepageGroupTicketRecyclerAdapter.MyViewHolder> {

private List<Data> dataList;

private LayoutInflater layoutInflater;

public HomepageGroupTicketRecyclerAdapter(Context context, List<Data> dataList) {
        this.dataList = dataList;
        layoutInflater = LayoutInflater.from(context);
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_homepage_group_ticket, parent, false);
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
        holder.group_title.setText(dataList.get(position).getTitle());
        holder.group_price.setText("RMB: " + dataList.get(position).getPrice());
        holder.group_content.setText(dataList.get(position).getContent());
        holder.group_num_people.setText("参团人数: "+dataList.get(position).getNumOfPeople());


        int id = getImageViewSourceId.getInstance().getResId(
                dataList.get(position).getImageView(), R.mipmap.class);

        holder.group_imageView.setImageResource(id);

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

        private TextView group_title;
        private TextView group_price;
        private TextView group_content;
        private TextView group_num_people;
        private ImageView group_imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            group_title = (TextView) itemView.findViewById(R.id.group_title);
            group_price = (TextView) itemView.findViewById(R.id.group_price);
            group_content = (TextView) itemView.findViewById(R.id.group_content);
            group_num_people = (TextView) itemView.findViewById(R.id.group_num_people);
            group_imageView = (ImageView) itemView.findViewById(R.id.head_img);
        }
    }

}