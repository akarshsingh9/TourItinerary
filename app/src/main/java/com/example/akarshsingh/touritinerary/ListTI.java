package com.example.akarshsingh.touritinerary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListTI extends AppCompatActivity {

    RecyclerView listTI_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ti);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listTI_recyclerView = (RecyclerView)findViewById(R.id.recycler_view_listTI);
        listTI_recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listTI_recyclerView.setLayoutManager(layoutManager);

        listTI_recyclerView.addItemDecoration(new DividerItemDecoration(listTI_recyclerView.getContext(),DividerItemDecoration.VERTICAL));

        List<listTI_modelClass> modelClassList = new ArrayList<listTI_modelClass>();

        //Static details added, TODO: retrieve from another JSON file which has submitted TI list and add it here
        modelClassList.add(new listTI_modelClass("Arun Kumar Panda","2016_0000123","Submitted"));
        modelClassList.add(new listTI_modelClass("Mohan Lal","2016_0000345","Accepted"));
        modelClassList.add(new listTI_modelClass("K. Narayan Subramanium","2016_0000567","Pending"));

        listTI_recyclerAdapter adapter = new listTI_recyclerAdapter(modelClassList);

        listTI_recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
