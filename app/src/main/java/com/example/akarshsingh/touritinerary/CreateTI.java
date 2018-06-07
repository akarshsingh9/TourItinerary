package com.example.akarshsingh.touritinerary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateTI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ti);
    //**************************************************************************************************************
        TextView tino = (TextView)findViewById(R.id.tino);
        final TextView approvename = (TextView)findViewById(R.id.authority);

        //reading JSON and putting values in Views
        tino.setText(tinum());
   //**************************************************************************************************************
       //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //hide soft keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    //*************************************************************************************************************
        // Spinner Name
        Spinner enterby_spinner = (Spinner)findViewById(R.id.enterby_spinner);
        List<String> name_list = new ArrayList<String>();
        //Static names added, TODO: retrieve name from JSON and add to list
        for(int i=0;i<3;i++)
        {
            name_list.add(enterby(i));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,name_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enterby_spinner.setAdapter(arrayAdapter);
        enterby_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = String.valueOf(approveAuth(position));
                approvename.setText(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    //*************************************************************************************************************
        //Spinner Travel Mode
        Spinner travelby = (Spinner)findViewById(R.id.traveltype_spinner);
        List<String> travel_list = new ArrayList<String>();
        //Static travel type added, TODO: retrieve travel type from JSON and add to list
        for(int j=0;j<3;j++)
        {
            travel_list.add(traveltype(j));
        }
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,travel_list);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelby.setAdapter(arrayAdapter1);

    //***************************************************************************************************************

        //Current Date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        String date = simpleDateFormat.format(c);
        TextView dateTextView = (TextView)findViewById(R.id.date);
        dateTextView.setText(date);

    //***************************************************************************************************************
        //submit button
        Button submit_btn = (Button)findViewById(R.id.submit_button);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent travel_intent = new Intent(CreateTI.this, addTI.class);
                startActivity(travel_intent);
            }
        });
    //***************************************************************************************************************


    } //end of onCreate()

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

//==================================================================================================
    String loadJSON()
    {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    return json;
}
//===================================================================================================
String tinum()
{
    String num = "";
    try{
        JSONObject obj = new JSONObject(loadJSON());
        JSONObject arr = obj.getJSONObject("array");
        JSONArray tino = arr.getJSONArray("ti_no");
        num = tino.getString(0);


    }
    catch (JSONException e)
    {
        e.printStackTrace();
    }

    return num;
}
//==================================================================================================
String enterby(int index)
{
    String officer_name = "";
    try{
        JSONObject object = new JSONObject(loadJSON());
        JSONObject arr = object.getJSONObject("array");
        JSONArray enterby = arr.getJSONArray("enterby");
        officer_name = enterby.getString(index);

    }
    catch (JSONException e)
    {
        e.printStackTrace();
    }

    return officer_name;
}
//==================================================================================================

String traveltype(int index)
{
    String type = "";
    JSONObject object = null;
    try {
        object = new JSONObject(loadJSON());
        JSONObject arr = object.getJSONObject("array");
        JSONArray traveltype = arr.getJSONArray("traveltype");
        type = traveltype.getString(index);

    } catch (JSONException e) {
        e.printStackTrace();
    }

return type;
}
//==================================================================================================
    String approveAuth(int index)
    {
        String approvename = "";
        String officername = "";
        JSONObject object = null;
        try {
            object = new JSONObject(loadJSON());
            JSONObject arr = object.getJSONObject("array");
            JSONArray enterby = arr.getJSONArray("enterby");
            officername = enterby.getString(index);
            JSONObject approve = arr.getJSONObject("approveby");
            approvename = approve.getString(officername);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return approvename;
    }

} //end of CreateTI Activity
