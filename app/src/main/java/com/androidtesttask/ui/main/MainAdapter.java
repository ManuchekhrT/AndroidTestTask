package com.androidtesttask.ui.main;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtesttask.R;
import com.androidtesttask.data.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Data> items = new ArrayList<>();
    private OnItemClickListener listener;

    public MainAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(Data item);
    }

    public void changeDataSet(List<Data> dataList) {
        items.addAll(dataList);
        notifyDataSetChanged();
    }

    public void changeItem(Bitmap bitmap) {
        for (Data data : items) {
            data.setPicture(bitmap);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Data data = items.get(position);
        holder.nameTv.setText(data.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data);
            }
        });

        if (data.getPicture() != null) {
            holder.pictureIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.pictureIv.setImageBitmap(data.getPicture());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public ImageView pictureIv;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_name);
            pictureIv = itemView.findViewById(R.id.iv_picture);
        }

    }
}
