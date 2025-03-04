package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text, email text, password text)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerUser(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        //Insert new value into "users"
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public boolean login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        return c.moveToFirst();
    }
}
