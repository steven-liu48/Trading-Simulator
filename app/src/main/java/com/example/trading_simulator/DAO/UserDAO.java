package com.example.trading_simulator.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trading_simulator.DAOHelper.UserDAOHelper;

import java.util.HashMap;

public class UserDAO extends DAO{

    public UserDAO(Context context) {
        super(new UserDAOHelper(context));
    }

    @Override
    public HashMap<String, String> getData(String identifier) {
        SQLiteDatabase db = super.getDatabase();
        String[] columns = {UserDAOHelper.NAME, UserDAOHelper.PASSWORD};
        Cursor cursor = db.query(UserDAOHelper.TABLE_NAME,
                null,UserDAOHelper.NAME+"=?",
                new String[]{identifier},null,null,null);
        String name = cursor.getString(cursor.getColumnIndex(UserDAOHelper.NAME));
        String password = cursor.getString(cursor.getColumnIndex(UserDAOHelper.PASSWORD));
        HashMap<String, String> map = new HashMap<>();
        map.put(UserDAOHelper.NAME, name);
        map.put(UserDAOHelper.PASSWORD, password);
        return map;
    }
}
