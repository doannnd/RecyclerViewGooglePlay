package com.nguyendinhdoan.recyclerviewgoogleplay;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyendinhdoan.recyclerviewgoogleplay.adapter.MyDataAdapter;
import com.nguyendinhdoan.recyclerviewgoogleplay.model.MyData;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";
    public static final String FIREBASE_DATABASE_NAME = "MyData";

    private RecyclerView googlePlayRecyclerView;
    private AlertDialog dialog;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupUI();
    }

    private void initViews() {
        googlePlayRecyclerView = findViewById(R.id.google_play_recycler_view);
        dialog = new SpotsDialog.Builder().setContext(this).build();
    }

    private void setupUI() {
        dialog.show();
        setupFirebase();
    }

    private void setupFirebase() {
        db = FirebaseDatabase.getInstance().getReference(FIREBASE_DATABASE_NAME);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MyData> myDataList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    MyData myData = data.getValue(MyData.class);
                    myDataList.add(myData);
                }
                setupRecyclerView(myDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });

    }

    private void setupRecyclerView(List<MyData> myDataList) {
        googlePlayRecyclerView.setHasFixedSize(true);
        googlePlayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyDataAdapter myDataAdapter = new MyDataAdapter(this, myDataList);
        googlePlayRecyclerView.setAdapter(myDataAdapter);
        dialog.dismiss();
    }
}
