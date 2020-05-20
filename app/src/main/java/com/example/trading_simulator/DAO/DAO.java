package com.example.trading_simulator.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.trading_simulator.DAOHelper.DAOHelper;

import java.util.HashMap;

public abstract class DAO {
    private DAOHelper myhelper;
    public DAO(Context context)
    {
        myhelper = new DAOHelper(context);
    }

    public DAO(DAOHelper helper){
        myhelper = helper;
    }


    public SQLiteDatabase getDatabase(){
        return myhelper.getWritableDatabase();
    }
    /**
     * Inset the data to the database
     * @param content the data to insert
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insertData(ContentValues content)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        return dbb.insert(DAOHelper.TABLE_NAME, null , content);
    }

    /**
     * Return all the data associated with identifier.
     * @param identifier identifier
     * @return A HashMap from the name to the data
     */
    public abstract HashMap<String,String> getData(String identifier);

    /**
     * Delete the row specified by identifier
     * @param identifier identifier
     * @return the number of rows affected if a whereClause is passed in, 0 otherwise. To
     * remove all rows and get a count pass "1" as the whereClause.
     */
    public  int delete(String identifier)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={identifier};

        return db.delete(DAOHelper.TABLE_NAME ,DAOHelper.ID+" = ?",whereArgs);
    }

    /**
     * Update the data specified by oldUser with content
     * @param content The new data
     * @param identifier The identifier of the old data
     * @return the number of rows affected
     */
    public int update(ContentValues content, String identifier)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs= {identifier};
        return db.update(DAOHelper.TABLE_NAME,content, DAOHelper.ID+" = ?",whereArgs );
    }

}
