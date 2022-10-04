package com.example.noted01k12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Note> dataSource;

    public MyAdapter(Context context, List<Note> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup, false);
        }
        // Bind id cho item view
        TextView tvTitle = view.findViewById(R.id.tvItemTitle);
        TextView tvContent = view.findViewById(R.id.tvItemContent);
        // Do du lieu vao view
        Note data = dataSource.get(i);
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
        return view;
    }
}
