package com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Activities;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.CommonResources;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.ViewPagerAdapter;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.Data;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.DatabaseObject_SingletonClass;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Fragments.LikesFragment;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Fragments.ViewFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView iv_action_bar_icon, iv_action_bar_back;
    TextView action_bar_notification;
    public static String Pass_Data = "";
    public List<Data> DBResult;
    public List<Data> DBResult_Views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(0);

        Intent intent = getIntent();
        Pass_Data = intent.getStringExtra("CurrentView");

        try {
            DBResult = CommonResources.GetOnlyLikesorViews(
                    DatabaseObject_SingletonClass.getInstance(this),
                    this,
                    MainActivity.Pass_Data,
                    "0"
            );

            DBResult_Views = CommonResources.GetOnlyLikesorViews(
                    DatabaseObject_SingletonClass.getInstance(this),
                    this,
                    MainActivity.Pass_Data,
                    "1"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();


        iv_action_bar_back = (ImageView) view.findViewById(R.id.action_bar_back);
        iv_action_bar_icon = (ImageView) view.findViewById(R.id.action_bar_icon);
        action_bar_notification = (TextView) view.findViewById(R.id.action_bar_notification);

        action_bar_notification.setText(
                ((DBResult == null) ? "0" : "" + DBResult.size())
        );


        iv_action_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorAccent));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int value) {
                if (value == 0) {
                    iv_action_bar_icon.setImageResource(R.drawable.eye_icon);
                    action_bar_notification.setText(
                            ((DBResult == null) ? "0" : "" + DBResult.size())
                    );

                } else {
                    iv_action_bar_icon.setImageResource(R.drawable.heart_icon);
                    action_bar_notification.setText(
                            ((DBResult_Views == null) ? "0" : "" + DBResult_Views.size())
                    );
                }

            }
        });
    }


    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(CommonResources.tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(CommonResources.tabIcons[1]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ViewFragment(), "View");
        adapter.addFragment(new LikesFragment(), "Like");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelperManager.releaseHelper();
    }
}