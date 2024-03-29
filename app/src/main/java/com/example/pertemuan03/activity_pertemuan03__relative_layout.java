package com.example.pertemuan03;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class activity_pertemuan03__relative_layout extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertemuan03__relative_layout);

        listData = new ArrayList<>();
        setData();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Recycle_Adapter(listData);
        recyclerView.setAdapter(adapter);
    }

    private void setData(){
        listData.add("Data Satu");
        listData.add("Data Dua");
        listData.add("Data Tiga");
        listData.add("Data Empat");
        listData.add("Data Lima");
    }

}
