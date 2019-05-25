package com.blogspot.nithinchalakkal.schoolvoiceapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.blogspot.nithinchalakkal.schoolvoiceapplication.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "schoolvoice.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Data, Integer> DataDao = null;
    private RuntimeExceptionDao<Data, Integer> DataRuntimeDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Data.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Data.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<Data, Integer> getDataDao() throws SQLException {
        if (DataDao == null) {
            DataDao = getDao(Data.class);
        }
        return DataDao;
    }

    public RuntimeExceptionDao<Data, Integer> getDataRuntimeDao() throws SQLException {
        if (DataRuntimeDao == null) {
            DataRuntimeDao = getRuntimeExceptionDao(Data.class);
        }
        return DataRuntimeDao;
    }
}
