package com.amonmcduul.moviesinthepocket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.amonmcduul.moviesinthepocket.Data.DatabaseHelper;

import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private static int mId;
    private static String mTitle;
    private static String mReview;
    private static String mRating;
    private static long mRowId;

    private static final String TAG = "DatabaseHelper";

    public static final String TABLE_NAME = "movie_table3";
    private static final String COL1 = "ID";
    private static final String COL2 = "title";
    private static final String COL3 = "review";
    private static final String COL4 = "rating";

    Context mContext = InstrumentationRegistry.getTargetContext();


    @org.junit.Test
    public void testDropDB() {
        assertTrue(mContext.deleteDatabase(DatabaseHelper.TABLE_NAME));
    }

    @org.junit.Test
    public void testCreateDB() {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

    @org.junit.Test
    public void testInsertData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        mTitle = "TestTitle";
        mReview = "TestReview";
        mRating = "TestRating";

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, mTitle);
        contentValues.put(COL3, mReview);
        contentValues.put(COL4, mRating);

        mRowId = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        assertTrue(mRowId != -1);
    }

//    @org.junit.Test
//    public void testIsDataCorrectInDB(){
//        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null);
//        assertTrue(cursor.moveToFirst());
//
//        int idColumnIndex = cursor.getColumnIndex(C OL1);
//        int dbId = cursor.getInt(idColumnIndex);
//
//        int titleColumnIndex = cursor.getColumnIndex(COL2);
//        String dbTitle = cursor.getString(titleColumnIndex);
//
//        int reviewColumnIndex = cursor.getColumnIndex(COL3);
//        String dbReview = cursor.getString(reviewColumnIndex);
//
//        int ratingColumnIndex = cursor.getColumnIndex(COL4);
//        String dbRating = cursor.getString(ratingColumnIndex);
//
//     //   assertEquals(mRowId, dbId);
//        assertEquals(mTitle, dbTitle);
//        assertEquals(mReview, dbReview);
//        assertEquals(mRating, dbRating);
//
//    }
}