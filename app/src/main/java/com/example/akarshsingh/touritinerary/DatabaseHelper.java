package com.example.akarshsingh.touritinerary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "travel_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(travelInfoModelClass.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + travelInfoModelClass.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertTI(String tino,String from,String to, int travelmode, String date, String time, String purpose) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(travelInfoModelClass.COLUMN_TINUM, tino);
        values.put(travelInfoModelClass.COLUMN_FROM, from);
        values.put(travelInfoModelClass.COLUMN_TO, to);
        values.put(travelInfoModelClass.COLUMN_TRAVELMODE, travelmode);
        values.put(travelInfoModelClass.COLUMN_DATE, date);
        values.put(travelInfoModelClass.COLUMN_TIME, time);
        values.put(travelInfoModelClass.COLUMN_PURPOSE, purpose);
        // insert row
        long id = db.insert(travelInfoModelClass.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public travelInfoModelClass getTravel(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(travelInfoModelClass.TABLE_NAME,
                new String[]{travelInfoModelClass.COLUMN_ID,travelInfoModelClass.COLUMN_TINUM, travelInfoModelClass.COLUMN_FROM, travelInfoModelClass.COLUMN_TO,travelInfoModelClass.COLUMN_TRAVELMODE,travelInfoModelClass.COLUMN_DATE,travelInfoModelClass.COLUMN_TIME,travelInfoModelClass.COLUMN_PURPOSE},
                travelInfoModelClass.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        travelInfoModelClass note = new travelInfoModelClass(
                cursor.getInt(cursor.getColumnIndex(travelInfoModelClass.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TINUM)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_FROM)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TO)),
                cursor.getInt(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TRAVELMODE)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TIME)),
                cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_PURPOSE)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<travelInfoModelClass> getAllTravels() {
        List<travelInfoModelClass> travels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + travelInfoModelClass.TABLE_NAME;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                travelInfoModelClass note = new travelInfoModelClass();
                note.setId(cursor.getInt(cursor.getColumnIndex(travelInfoModelClass.COLUMN_ID)));
                note.setTi_number(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TINUM)));
                note.setFrom(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_FROM)));
                note.setTo(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TO)));
                note.setTravelmode(cursor.getInt(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TRAVELMODE)));
                note.setDate(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_DATE)));
                note.setTime(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_TIME)));
                note.setPurpose(cursor.getString(cursor.getColumnIndex(travelInfoModelClass.COLUMN_PURPOSE)));

                travels.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return travels;
    }

    public int getTravelCount() {
        String countQuery = "SELECT  * FROM " + travelInfoModelClass.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public boolean deletetravel(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(travelInfoModelClass.TABLE_NAME, travelInfoModelClass.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        if (result>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
