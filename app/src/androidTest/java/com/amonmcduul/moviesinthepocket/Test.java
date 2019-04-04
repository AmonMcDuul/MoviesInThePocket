package com.amonmcduul.moviesinthepocket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.amonmcduul.moviesinthepocket.Data.DatabaseHelper;
import android.support.test.InstrumentationRegistry;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Test{
    private static int mId;
    private static String mTitle;
    private static String mReview;
    private static String mRating;
    Context mContext = InstrumentationRegistry.getTargetContext();

@org.junit.Test
    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DatabaseHelper.TABLE_NAME));
      //  Utilities.Log("TestDropDB pass");
    }

    @org.junit.Test
    public void testCreateDB(){
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
      //  Utilities.Log("TestCreateDB pass");
    }

}