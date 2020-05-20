package com.example.trading_simulator.DAOHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOHelper extends SQLiteOpenHelper {

        public static String DATABASE_NAME;
        public static String TABLE_NAME;
        public static int DATABASE_Version;
        public static String ID;
        public static String CREATE_TABLE;
        public static String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

    public DAOHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            //Message.message(context,""+e);
        }
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            //Message.message(context, "" + e);
        }
    }
}
