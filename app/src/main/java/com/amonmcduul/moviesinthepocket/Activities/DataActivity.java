package com.amonmcduul.moviesinthepocket.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amonmcduul.moviesinthepocket.Adapters.SqliteCrudAdapter;
import com.amonmcduul.moviesinthepocket.Data.DatabaseHelper;
import com.amonmcduul.moviesinthepocket.Model.DataModel;
import com.amonmcduul.moviesinthepocket.R;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Data activity.
 */
public class DataActivity extends AppCompatActivity {

    /**
     * The Data list.
     */
    List<DataModel> dataList;
    /**
     * The List view.
     */
    ListView listView;

    /**
     * The M database.
     */

//The databasemanager object
    DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //Instantiating the database manager object
        mDatabase = new DatabaseHelper(this);
        dataList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewuser);

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        //we are here using the DatabaseManager instance to get all saved data
        Cursor cursor = mDatabase.getData();

        if (cursor.moveToFirst()) {
            do {
                dataList.add(new DataModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());

            //passing the databasemanager instance this time to the adapter
            SqliteCrudAdapter adapter = new SqliteCrudAdapter(this, R.layout.list_layout_data, dataList, mDatabase);
            listView.setAdapter(adapter);
        }
    }

}