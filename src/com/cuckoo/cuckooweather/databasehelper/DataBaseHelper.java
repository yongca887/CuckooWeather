package com.cuckoo.cuckooweather.databasehelper;

import com.suckoo.cuckoo.weather.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yong on 14/11/8.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    public static final String USER_TABLE = "usertable";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USERTABLE_SQL = "create table " + USER_TABLE + "(" + User.KEY_ID +
            " integer primary key autoincrement," +
            User.KEY_NAME+" text," +
            User.KEY_PASSWORD+" text," +
            User.KEY_NICKNAME+" text," +
            User.KEY_AVATAR+" byte," +
            User.KEY_EMAIL+" text" + ");";

    private SQLiteDatabase db;
    private String createTableSqlString;

//    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public  DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  DataBaseHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        db.execSQL(CREATE_USERTABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w("TaskkDBAdapter", "Upgrading form version" + oldVersion + "to " + newVersion
        + ", which will destory all old data");

        db.execSQL("DROP TABLEs IF EXISTS " + USER_TABLE);

        onCreate(db);
    }
}
