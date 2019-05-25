package com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.CommonResources;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.controller.ListviewArrayadaptor;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.Data;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.DatabaseObject_SingletonClass;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.view.Activities.MainActivity;

import java.sql.SQLException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikesFragment extends Fragment {
    ListView lv_likes;
    View v;

    public String[] name;
    int[] image;
    String[] time;
    Context context;
    public List<Data> DBResult;

    public LikesFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;


        try {



            DBResult = CommonResources.GetOnlyLikesorViews(
                    DatabaseObject_SingletonClass.getInstance(context),
                    context,
                    MainActivity.Pass_Data,
                    "1"
            );

            name = new String[DBResult.size()];
            image = new int[DBResult.size()];
            time = new String[DBResult.size()];

            for (int i = 0; i < DBResult.size(); i++) {
                    name[i] = DBResult.get(i).getName();
                    image[i] = R.drawable.picone;
                    time[i] = (String) DateFormat.format("hh:mm:ss", DBResult.get(i).getTime());

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_likes, container, false);
        lv_likes = (ListView) v.findViewById(R.id.lv_likes);
        if (name != null) {
            lv_likes.setAdapter(new ListviewArrayadaptor(getActivity(), R.layout.customlistview, name, time, image));
        }
        return v;
    }


}





