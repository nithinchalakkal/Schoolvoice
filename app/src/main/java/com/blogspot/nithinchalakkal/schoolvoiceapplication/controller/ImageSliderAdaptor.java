package com.blogspot.nithinchalakkal.schoolvoiceapplication.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.DatabaseObject_SingletonClass;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Activities.LauncherActivity;
import com.bumptech.glide.Glide;

import java.sql.SQLException;

public class ImageSliderAdaptor extends PagerAdapter {
    Context context;
    int images[];
    LayoutInflater layoutInflater;


    public ImageSliderAdaptor(Context context, int images[]) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.launchitems, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        ImageView imageView_thumpup = (ImageView) itemView.findViewById(R.id.imageView_thumpup);
        imageView.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(imageView);
        container.addView(itemView);


        //listening to image click
        imageView_thumpup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    if (CommonResources.CheckAlreadyLiked(
                            DatabaseObject_SingletonClass.getInstance(context),
                            context,
                            "USER ID " + LauncherActivity.Session + (position + 1),
                            "1"
                    ).size() > 0) {
                        Toast.makeText(context, "Already liked in the same session", Toast.LENGTH_SHORT).show();
                    } else {

                        CommonResources.PushData(
                                DatabaseObject_SingletonClass.getInstance(context),
                                context,
                                "" + (position + 1),
                                "USER ID " + LauncherActivity.Session + (position + 1),
                                1);

                        Toast.makeText(context, "View has been liked", Toast.LENGTH_SHORT).show();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }


}