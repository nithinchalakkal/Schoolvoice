package com.blogspot.nithinchalakkal.schoolvoiceapplication.model;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseUtilConfig extends OrmLiteConfigUtil {

    private static final Class<?>[]classes=new Class[]{Data.class};

    public static void main(String args[]) throws IOException, SQLException {

        File configFile = new File(new File("").getAbsolutePath()

                .split("app" +File.separator + "build")[0] + File.separator +
                "app" + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "res" + File.separator +
                "raw" + File.separator +
                "ormlite_config.txt");

        writeConfigFile(configFile);

    }
}
