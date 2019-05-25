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
public class ViewFragment extends Fragment {
    ListView lv_View;
    View v;

    public String[] name;
    int[] image;
    String[] time;
    public List<Data> DBResult;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;


        try {



            DBResult = CommonResources.GetOnlyLikesorViews(
                    DatabaseObject_SingletonClass.getInstance(context),
                    context,
                    MainActivity.Pass_Data,
                    "0"
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


    public ViewFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view, container, false);
        lv_View = (ListView) v.findViewById(R.id.lv_views);
        if (time != null) {
            lv_View.setAdapter(new ListviewArrayadaptor(getActivity(),
                    R.layout.customlistview,
                    name,
                    time,
                    image
            ));

        }
        return v;
    }
}
