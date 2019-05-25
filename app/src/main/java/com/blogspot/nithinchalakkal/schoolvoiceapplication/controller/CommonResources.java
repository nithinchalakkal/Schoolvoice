package com.blogspot.nithinchalakkal.schoolvoiceapplication.controller;

import android.content.Context;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.Data;
import com.blogspot.nithinchalakkal.schoolvoiceapplication.model.DataBaseHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class CommonResources {

    public static int[] tabIcons = {
            R.drawable.eye_icon,
            R.drawable.heart_icon,

    };


    public static int rand_num()
    {
        Random rand = new Random();
      return rand.nextInt(100);

    }


    public static void PushData(DataBaseHelper  dataBaseHelper, Context context,String ItemID, String Name, int view_Like) throws SQLException {
        RuntimeExceptionDao<Data,Integer> dataDao=dataBaseHelper.getDataRuntimeDao();
        dataDao.create(new Data(ItemID,Name,view_Like));
    }


    public static void GetAllData(DataBaseHelper  dataBaseHelper, Context context) throws SQLException {
        RuntimeExceptionDao<Data,Integer>dataDao=dataBaseHelper.getDataRuntimeDao();
        List<Data> Result =dataDao.queryForAll();

    }
    public static List<Data> Getviews$Likes(DataBaseHelper  dataBaseHelper, Context context,String ItemID) throws SQLException {
        RuntimeExceptionDao<Data,Integer>dataDao=dataBaseHelper.getDataRuntimeDao();
        return dataDao.queryForEq("itemID",ItemID);
    }

    public static List<Data> CheckExist(DataBaseHelper  dataBaseHelper, Context context,String name) throws SQLException {
        RuntimeExceptionDao<Data,Integer>dataDao=dataBaseHelper.getDataRuntimeDao();
        return dataDao.queryForEq("name",name);
    }

    public static List<Data> GetOnlyLikesorViews(DataBaseHelper  dataBaseHelper, Context context,String ItemID ,String view_Like) throws SQLException {
        RuntimeExceptionDao<Data,Integer>dataDao=dataBaseHelper.getDataRuntimeDao();

        QueryBuilder<Data, Integer> queryBuilder = dataDao.queryBuilder();
// get the WHERE object to build our query
        Where<Data, Integer> where = queryBuilder.where();

            ((Where) where).like("itemID",ItemID);

        where.and();

        ((Where) where).like("view_Like",view_Like);

        PreparedQuery<Data> preparedQuery = queryBuilder.prepare();

        return dataDao.query(preparedQuery);
    }

    public static List<Data> CheckAlreadyLiked(DataBaseHelper  dataBaseHelper, Context context,String name ,String view_Like) throws SQLException {
        RuntimeExceptionDao<Data,Integer>dataDao=dataBaseHelper.getDataRuntimeDao();

        QueryBuilder<Data, Integer> queryBuilder = dataDao.queryBuilder();
// get the WHERE object to build our query
        Where<Data, Integer> where = queryBuilder.where();

        ((Where) where).like("name",name);

        where.and();

        ((Where) where).like("view_Like",view_Like);

        PreparedQuery<Data> preparedQuery = queryBuilder.prepare();

        return dataDao.query(preparedQuery);
    }

}
