package com.example.petcareapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "PetDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Pet(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "species TEXT," +
                "breed TEXT," +
                "age TEXT," +
                "gender TEXT," +
                "image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pet");
        onCreate(db);
    }

    public void deletePet(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Pet", "name=?", new String[]{name});
    }
}