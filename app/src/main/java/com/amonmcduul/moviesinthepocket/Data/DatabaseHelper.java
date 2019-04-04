package com.amonmcduul.moviesinthepocket.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * The type Database helper.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String TABLE_NAME = "movie_table3";
    private static final String COL1 = "ID";
    private static final String COL2 = "title";
    private static final String COL3 = "review";
    private static final String COL4 = "rating";

    /**
     * Instantiates a new Database helper.
     *
     * @param context the context
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT );";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Add data boolean.
     *
     * @param title  the title
     * @param review the review
     * @param rating the rating
     * @return the boolean
     */
    public boolean addData(String title, String review, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, review);
        contentValues.put(COL4, rating);

        Log.d(TAG, "addData: Adding " + title + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     *
     * @return data
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL2 + " ASC ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     *
     * @param name
     * @return
     */
//    public Cursor getItemID(String name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
//                " WHERE " + COL2 + " = '" + name + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }

    /**
     * Updates the data field
     *
     * @param id        the id
     * @param newReview the new review
     * @param newRating the new rating
     * @return the boolean
     */
    public boolean updateData(int id, String newReview, String newRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newReview + "' WHERE " + COL1 + " = '" + id + "'";
        String query2 = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newRating + "' WHERE " + COL1 + " = '" + id + "'";
        Log.d(TAG, "updateData: query: " + query);
        db.execSQL(query);
        db.execSQL(query2);

        return false;
    }

    /**
     * Delete from database
     *
     * @param id   the id
     * @param name the name
     */
    public void deleteName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COL1 + "=?", new String[]{String.valueOf(id)}) == 1;
    }

}
