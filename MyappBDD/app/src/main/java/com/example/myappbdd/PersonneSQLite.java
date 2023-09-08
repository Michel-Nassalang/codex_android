package com.example.myappbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonneSQLite extends SQLiteOpenHelper {
    private static final String TABLE_PERSONNES = "table_personnes";
    private static final String COL_ID = "ID";
    private static final String COL_PRENOM = "PRENOM";
    private static final String COL_NOM = "NOM";


    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_PERSONNES
            + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PRENOM
            + " TEXT NOT NULL, " + COL_NOM + " TEXT NOT NULL);";

    public PersonneSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_PERSONNES + ";");
        onCreate(db);
    }
}
