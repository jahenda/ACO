package com.jahendamercy.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConn extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String COL_1 = "entername";
    public static final String COL_2 = "phonenumber";
    public static final String COL_3 = "address";
    public static final String COL_4 = "country";
    public static final String COL_5 = "occupation";
    public static final String COL_6 = "email";

    public DatabaseConn(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL("CREATE TABLE register (email VARCHAR PRIMARY KEY, entername VARCHAR, phonenumber INTEGER, address VARCHAR, country VARCHAR, occupation VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
      sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registeruser" , null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser (String username, String password){
        String[] columns = { COL_6};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_1 + "=?" + " and " + COL_6 + "=?";
        String [] selectionArghs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArghs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}

