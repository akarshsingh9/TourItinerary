package com.example.akarshsingh.touritinerary;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class addTI extends AppCompatActivity {

    CardView officer_cardView;
    FloatingActionButton add_fab;
    RecyclerView ticreated_recyclerview;
    RelativeLayout res_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ti);
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //view elements in the layout
        officer_cardView = (CardView)findViewById(R.id.officer_cardView);
        add_fab = (FloatingActionButton)findViewById(R.id.add_fab);
        ticreated_recyclerview = (RecyclerView)findViewById(R.id.ti_created_recyclerview);
        res_layout = (RelativeLayout)findViewById(R.id.reslayout);

        // recyclerview initialization
        ticreated_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ticreated_recyclerview.setLayoutManager(linearLayoutManager);

        // arraylist TODO: Retrieve details from addTI_Form Activity and add it here
        List<addTI_recyclermodel> recyclermodelList = new ArrayList<addTI_recyclermodel>();
        recyclermodelList.add(new addTI_recyclermodel("2016_0000123","Mumbai","Delhi",R.drawable.ic_train_round));
        recyclermodelList.add(new addTI_recyclermodel("2016_0000124","Ahmedabad","Delhi",R.drawable.ic_train_round));
        recyclermodelList.add(new addTI_recyclermodel("2016_0000125","kolkalta","Delhi",R.drawable.ic_flight_round));
        recyclermodelList.add(new addTI_recyclermodel("2016_0000126","Chennai","Delhi",R.drawable.ic_train_round));
        recyclermodelList.add(new addTI_recyclermodel("2016_0000127","Pune","Delhi",R.drawable.ic_flight_round));

        // horizontal line after each list item
        ticreated_recyclerview.addItemDecoration(new DividerItemDecoration(ticreated_recyclerview.getContext(),DividerItemDecoration.VERTICAL));

        // attaching adapter to recycler
        addTI_recyclerAdapter addTI_recyclerAdapter = new addTI_recyclerAdapter(recyclermodelList);
        ticreated_recyclerview.setAdapter(addTI_recyclerAdapter);

        //Floating Action Button click event
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addTI.this, addTI_Form.class);
                startActivity(intent);


            }
        });

    }

    // Up button on ActionBar
    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(addTI.this,CreateTI.class));
        finish();
        return true;
    }


}
