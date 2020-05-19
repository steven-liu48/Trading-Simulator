package com.example.trading_simulator.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trading_simulator.DAOHelper.DAOHelper;

public abstract class DAO {
    private DAOHelper myhelper;
    public DAO(Context context)
    {
        myhelper = new DAOHelper(context);
    }

    public long insertData(ContentValues content)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        long id = dbb.insert(DAOHelper.TABLE_NAME, null , content);
        return id;
    }

    public abstract String getData();

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(DAOHelper.TABLE_NAME ,DAOHelper.ID+" = ?",whereArgs);
        return  count;
    }

    public int update(ContentValues content, String oldUser)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs= {oldUser};
        int count =db.update(DAOHelper.TABLE_NAME,content, DAOHelper.ID+" = ?",whereArgs );
        return count;
    }

}
