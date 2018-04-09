package com.example.administrator.tour;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1/001.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<BrowseData> dataList;

    private LayoutInflater layoutInflater;

    public MyRecyclerAdapter(Context context, List<BrowseData> dataList) {
        this.dataList = dataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_browse, parent, false);
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
        holder.tv_title.setText(dataList.get(position).getTitle());
        holder.tv_content.setText(dataList.get(position).getContent());


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

        private TextView tv_title;

        private TextView tv_content;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.content_title);
            tv_content = (TextView) itemView.findViewById(R.id.content);
        }
    }

}
