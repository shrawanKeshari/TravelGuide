package com.example.sonu.travelguide.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sonu.travelguide.Place;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by shrawankeshari on 13/12/17.
 */

public class PlaceDataSource {
    private static final String LOGTAG = "TOURGUIDE";
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            PlaceDBOpenHelper.COLUMN_ID,
            PlaceDBOpenHelper.COLUMN_TITLE,
            PlaceDBOpenHelper.COLUMN_IMAGE,
            PlaceDBOpenHelper.COLUMN_LOCATION
    };

    public PlaceDataSource(Context context){
        dbHelper = new PlaceDBOpenHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "Database opened");
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG,"Database closed");
        dbHelper.close();
    }

    public Place create(Place place){
        ContentValues values = new ContentValues();
        values.put(PlaceDBOpenHelper.COLUMN_TITLE, place.getPlaceName());
        values.put(PlaceDBOpenHelper.COLUMN_IMAGE, place.getImage());
        values.put(PlaceDBOpenHelper.COLUMN_LOCATION, place.getLocation());
        long insertId = database.insert(PlaceDBOpenHelper.TABLE_PLACE, null, values);
        place.setId(insertId);

        return place;
    }

    public List<Place> findAll(){
        Cursor cursor = database.query(PlaceDBOpenHelper.TABLE_PLACE, allColumns, null,
                null, null, null, null);
        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");

        List<Place> place = cursorToList(cursor);

        return place;
    }

    private List<Place> cursorToList(Cursor cursor){
        List<Place> places = new ArrayList<Place>();
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Place place = new Place();
                place.setId(cursor.getLong(cursor.getColumnIndex(PlaceDBOpenHelper.COLUMN_ID)));
                place.setName(cursor.getString(cursor.getColumnIndex
                        (PlaceDBOpenHelper.COLUMN_TITLE)));
                place.setImage(cursor.getString(cursor.getColumnIndex
                        (PlaceDBOpenHelper.COLUMN_IMAGE)));
                place.setLocation(cursor.getString(cursor.getColumnIndex(
                        PlaceDBOpenHelper.COLUMN_LOCATION)));
                places.add(place);
            }
        }

        return places;
    }
}
