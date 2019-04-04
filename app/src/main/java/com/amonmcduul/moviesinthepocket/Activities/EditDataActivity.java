package com.amonmcduul.moviesinthepocket.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amonmcduul.moviesinthepocket.Data.DatabaseHelper;
import com.amonmcduul.moviesinthepocket.R;


public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item, editable_item2;

    DatabaseHelper mDatabaseHelper;

    private String selectedReview;
    private String selectedRating;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editable_item2 = (EditText) findViewById(R.id.editable_item2);
        mDatabaseHelper = new DatabaseHelper(this);
        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedReview = receivedIntent.getStringExtra("review");
        selectedRating = receivedIntent.getStringExtra("rating");

        //set the text to show the current selected name
        editable_item.setText(selectedReview);
        editable_item2.setText(selectedRating);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateData(selectedID,selectedReview,selectedRating);
                }else{
                    toastMessage("You must enter a review");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedReview);
                editable_item.setText("");
                toastMessage("removed from database");
            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}