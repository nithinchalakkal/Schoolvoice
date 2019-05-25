package com.blogspot.nithinchalakkal.schoolvoiceapplication.model;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DatabaseObject_SingletonClass {
    private static  DataBaseHelper dataBaseHelper;

    public static DataBaseHelper getInstance(Context context)
    {
        if(dataBaseHelper==null){
            return OpenHelperManager.getHelper(context,DataBaseHelper.class);
        }else{
            return dataBaseHelper;
        }

    }


}
