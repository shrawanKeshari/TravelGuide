package com.example.sonu.travelguide.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shrawankeshari on 13/12/17.
 */

public class PlaceDBOpenHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "TOURGUIDE";

    private static final String DATABASE_NAME = "place.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PLACE = "places";
    public static final String COLUMN_ID = "placeId";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_LOCATION = "location";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_PLACE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_IMAGE + " TEXT, " +
            COLUMN_LOCATION + " TEXT " +
            ")";

    public PlaceDBOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(db);
    }
}
