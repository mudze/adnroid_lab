package com.example.adnroid_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLite (Context context) {
        super(context, "warzywaDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String DATABASE_CREATE =
                "create table warzywa " +
                "(_id integer primary key autoincrement, " +
                "rodzaj text not null, " +
                "kolor text not null, " +
                "wielkosc real not null, " +
                "opis text not null);";
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS warzywa");
        onCreate(db);
    }

    public void dodaj (Warzywa warzywo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rodzaj", warzywo.getRodzaj());
        values.put("kolor", warzywo.getKolor());
        values.put("wielkosc", warzywo.getWirlkosc());
        values.put("opis", warzywo.getOpis());

        db.insert("warzywa", null, values);
        db.close();
    }

    public void usun (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("warzywa", "_id = ?", new String[] { id });
        db.close();
    }

    public int aktualizuj(Warzywa warzywo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rodzaj", warzywo.getRodzaj());
        values.put("kolor", warzywo.getKolor());
        values.put("wielkosc", warzywo.getWirlkosc());
        values.put("opis", warzywo.getOpis());

        int i = db.update("warzywa", values, "_id = ?", new String[]{ String.valueOf(warzywo.getId())});

        db.close();
        return i;
    }

    public Warzywa pobierz (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("warzywa",
                new String[] {"rodzaj", "kolor", "wielkosc", "opis" },
                "_id = ?",
                new String[] {String.valueOf(id) },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        Warzywa warzywo = new Warzywa(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getString(4));
        warzywo.setId(Integer.parseInt(cursor.getString(0)));
        return warzywo;
    }

    public Cursor lista () {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from warzywa", null);
    }
}
