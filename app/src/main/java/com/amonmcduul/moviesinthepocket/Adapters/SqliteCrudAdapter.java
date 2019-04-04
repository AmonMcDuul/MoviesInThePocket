package com.amonmcduul.moviesinthepocket.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amonmcduul.moviesinthepocket.Activities.EditDataActivity;
import com.amonmcduul.moviesinthepocket.R;
import com.amonmcduul.moviesinthepocket.Data.DatabaseHelper;
import com.amonmcduul.moviesinthepocket.Model.DataModel;

import java.util.List;

public class SqliteCrudAdapter extends ArrayAdapter<DataModel> {
    private Button btnSave,btnDelete;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    Context mCtx;
    int layoutRes;
    List<DataModel> dataList;

    //the databasemanager object
    DatabaseHelper mDatabase;

    //modified the constructor and we are taking the DatabaseManager instance here
    public SqliteCrudAdapter(Context mCtx, int layoutRes, List<DataModel> dataList, DatabaseHelper mDatabase) {
        super(mCtx, layoutRes, dataList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.dataList = dataList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes, null);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewReview = view.findViewById(R.id.textViewReview);
        TextView textViewRating = view.findViewById(R.id.textViewRating);

        final DataModel user = dataList.get(position);

        textViewTitle.setText(user.getTitle());
        textViewReview.setText(user.getReview());
        textViewRating.setText(String.valueOf(user.getRating()));

        view.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(user);
            }
        });


        view.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(user);
            }
        });

        return view;
    }


    private void updateData(final DataModel userDataList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.edit_data_layout, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editReview = view.findViewById(R.id.editable_item);
        final EditText editRating = view.findViewById(R.id.editable_item2);

        editReview.setText(userDataList.getReview());
        editRating.setText(userDataList.getRating());


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newReview = editReview.getText().toString().trim();
                String newRating = editRating.getText().toString().trim();


                if (newReview.isEmpty()) {
                    editReview.setError("Review can't be empty");
                    editReview.requestFocus();
                    return;
                }

                if (newRating.isEmpty()) {
                    editRating.setError("Rating can't be empty");
                    editRating.requestFocus();
                    return;
                }


                //calling the update method from database manager instance

                if (mDatabase.updateData(userDataList.getId(), newReview, newRating)) {
                    Toast.makeText(mCtx, "User Details Updated", Toast.LENGTH_SHORT).show();
                    loadDataFromDatabase();
                }
                alertDialog.dismiss();
                loadDataFromDatabase();
            }
        });
    }


    private void deleteData(final DataModel DataList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //calling the delete method from the database manager instance
                if (mDatabase.deleteUser(DataList.getId()))
                    loadDataFromDatabase();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadDataFromDatabase() {
        //calling the read method from database instance
        Cursor cursor = mDatabase.getData();

        dataList.clear();
        if (cursor.moveToFirst()) {
            do {
                dataList.add(new DataModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }
}