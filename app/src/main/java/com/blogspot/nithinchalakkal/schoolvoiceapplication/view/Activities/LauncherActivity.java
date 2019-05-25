package com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.CommonResources;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.ImageSliderAdaptor;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.DatabaseObject_SingletonClass;

import java.sql.SQLException;

public class LauncherActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout lay_actions,lay_close;
    int images[] = {R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    public int CurrentView = 1;
    ImageView iv_close;
    Intent intent;
    public static int Session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        lay_actions = (LinearLayout) findViewById(R.id.lay_actions);
        lay_close= (LinearLayout) findViewById(R.id.lay_close);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        viewPager.setAdapter(new ImageSliderAdaptor(LauncherActivity.this, images));
        viewPager.setCurrentItem(0);
        Session = CommonResources.rand_num();


        try {
            CommonResources.PushData(
                    DatabaseObject_SingletonClass.getInstance(LauncherActivity.this),
                    LauncherActivity.this,
                    "" + CurrentView,
                    "USER ID " + Session+CurrentView,
                    0);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int arg0) {
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageSelected(int currentPage) {
                CurrentView = (currentPage + 1);


                try {
                    if (CommonResources.CheckExist(
                            DatabaseObject_SingletonClass.getInstance(LauncherActivity.this),
                            LauncherActivity.this,
                            "USER ID " + LauncherActivity.Session + CurrentView
                    ).size() > 0){

                    }else
                    {
                        CommonResources.PushData(
                                DatabaseObject_SingletonClass.getInstance(LauncherActivity.this),
                                LauncherActivity.this,
                                "" + CurrentView,
                                "USER ID " + Session + CurrentView,
                                0);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        lay_actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(LauncherActivity.this, MainActivity.class);
                intent.putExtra("CurrentView", "" + CurrentView);
                startActivity(intent);


            }
        });

        lay_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}