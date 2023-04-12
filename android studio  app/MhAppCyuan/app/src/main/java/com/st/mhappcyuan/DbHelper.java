package com.st.mhappcyuan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //创建数据库
    public DbHelper(@Nullable Context context) {
        super(context, "message.db", null, 1);
    }

    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="create table message (id integer primary key autoincrement,content text not null,date text) ";
        db.execSQL(sql);

    }
    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
