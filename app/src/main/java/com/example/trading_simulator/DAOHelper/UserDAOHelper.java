package com.example.trading_simulator.DAOHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserDAOHelper extends DAOHelper {

    public static String DATABASE_NAME = "USER_DATABASE";
    public static String TABLE_NAME = "USER_INFO_TABLE";
    public static int DATABASE_Version = 1;
    public static String ID = "USERNAME";
    public static String NAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+NAME+" VARCHAR(255) ,"+ PASSWORD+" VARCHAR(225));";;

    public UserDAOHelper(Context context) {
        super(context);
    }

}
