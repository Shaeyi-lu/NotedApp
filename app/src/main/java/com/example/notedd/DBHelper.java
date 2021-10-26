package com.example.notedd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    private static final String TableName="mynotes";
    private static final String ColumnId = "id";
    private static final String ColumnTitle="title";
    private static final String ColumnSubtitle="subtitle";
    private static final String ColumnNote="note";
    private static final String Color="color";
    private static final String DatabaseName="MyNotes";

    public DBHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = "CREATE TABLE " + TableName + " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ColumnTitle + " TEXT, " + ColumnSubtitle + " TEXT, "+
                ColumnNote + " TEXT);";

        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
         DB.execSQL("DROP TABLE IF EXISTS " + TableName);
         onCreate(DB);
    }

    void addData(String title, String subtitle, String note){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTitle, title);
        contentValues.put(ColumnSubtitle, subtitle);
        contentValues.put(ColumnNote, note);
        long result = DB.insert(TableName, null, contentValues);

        if(result == -1){
            Toast.makeText(context, "Data Not Added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Data Added Succssfully", Toast.LENGTH_SHORT).show();
        }

    }
}
