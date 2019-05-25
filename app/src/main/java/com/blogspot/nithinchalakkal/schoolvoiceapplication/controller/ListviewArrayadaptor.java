package com.blogspot.nithinchalakkal.schoolvoiceapplication.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ListviewArrayadaptor extends ArrayAdapter {
    TextView tv_name,tv_time;
    ImageView img;
    String name[];
    String time[];
    int image[];



    public ListviewArrayadaptor(Context context, int resource, String[] names, String[] time, int[] pics) {
        super(context, resource, names);;
        this.name=names;
        this.time=time;
        this.image=pics;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.customlistview,null);
         tv_name = (TextView) v.findViewById(R.id.tv_name);
         tv_time= (TextView) v.findViewById(R.id.tv_time);
         tv_name.setText(name[position]);
         tv_time.setText(time[position]);
         img = (ImageView) v.findViewById(R.id.iv_pic);
        Glide.with(getContext()).
                load(image[position]).
                apply(RequestOptions.circleCropTransform()).
                into(img);
        return v;
    }
}
