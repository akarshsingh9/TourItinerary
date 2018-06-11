package com.example.akarshsingh.touritinerary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class addTI extends AppCompatActivity {

    CardView officer_cardView;
    FloatingActionButton add_fab;
    RecyclerView ticreated_recyclerview;
    RelativeLayout res_layout;
    TextView summaryName, summaryContacts, summaryStatus, summaryDept, summaryApproveAuth;
    String tino,fromplace,toplace,travelmode,dateText,timeText,purposeText;
    public int flag = 0;

    DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ti);


        //get SharedPref
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        tino = preferences.getString("tino", null);
        String officername = preferences.getString("officername", null);
        String contactno = preferences.getString("contactno", null);
        String status = preferences.getString("status", null);
        String dept = preferences.getString("dept", null);
        String approvingauth = preferences.getString("approvingauth", null);


        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //view elements in the layout
        officer_cardView = (CardView) findViewById(R.id.officer_cardView);
        add_fab = (FloatingActionButton) findViewById(R.id.add_fab);
        ticreated_recyclerview = (RecyclerView) findViewById(R.id.ti_created_recyclerview);
        res_layout = (RelativeLayout) findViewById(R.id.reslayout);
        summaryApproveAuth = (TextView) findViewById(R.id.summary_auth);
        summaryName = (TextView) findViewById(R.id.summary_name);
        summaryContacts = (TextView) findViewById(R.id.summary_contact);
        summaryStatus = (TextView) findViewById(R.id.summary_status);
        summaryDept = (TextView) findViewById(R.id.summary_dept);


        //cardview details changes
        summaryName.setText(officername);
        summaryDept.setText(dept);
        summaryStatus.setText(status);
        summaryApproveAuth.setText(approvingauth);
        summaryContacts.setText(contactno);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag != 1) {
            ticreated_recyclerview.setVisibility(View.GONE);
        } else {
            ticreated_recyclerview.setVisibility(View.VISIBLE);




        }
        // recyclerview initialization
        ticreated_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ticreated_recyclerview.setLayoutManager(linearLayoutManager);


        // arraylist TODO: Retrieve details from addTI_Form Activity and add it here
        fromplace = intent.getStringExtra("from");
        toplace = intent.getStringExtra("to");
        travelmode = intent.getStringExtra("travelmode");
        dateText = intent.getStringExtra("date");
        timeText = intent.getStringExtra("time");
        purposeText = intent.getStringExtra("purpose");

        int imageres;
        if (TextUtils.equals(travelmode,"Flight"))
        {
            imageres = R.drawable.ic_flight_round;
        }
        else
        {
            imageres = R.drawable.ic_train_round;
        }

        helper = new DatabaseHelper(this);

        List<travelInfoModelClass> recyclermodelList = new ArrayList<>();
        long id = helper.insertTI(tino,fromplace,toplace,imageres,dateText,timeText,purposeText);
        travelInfoModelClass travel = helper.getTravel(id);


        // horizontal line after each list item
        ticreated_recyclerview.addItemDecoration(new DividerItemDecoration(ticreated_recyclerview.getContext(), DividerItemDecoration.VERTICAL));
        recyclermodelList.add(travel);
        recyclermodelList.addAll(helper.getAllTravels());
        // attaching adapter to recycler
        addTI_recyclerAdapter addTI_recyclerAdapter = new addTI_recyclerAdapter(recyclermodelList);

        addTI_recyclerAdapter.notifyDataSetChanged();


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
