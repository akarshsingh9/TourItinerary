package com.example.akarshsingh.touritinerary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ti);
        //TODO: pass fields on using SharedPreference

//==================================================================================================================

        final TextView tino = (TextView)findViewById(R.id.tino);
        final TextView approvename = (TextView)findViewById(R.id.authority);
        final TextView tifor = (TextView)findViewById(R.id.TIfor);
        final TextView prefname = (TextView)findViewById(R.id.preferredname);
        final TextView dept = (TextView)findViewById(R.id.dept);
        final EditText contacts = (EditText)findViewById(R.id.contacts);
        final TextView status = (TextView)findViewById(R.id.status);

        //reading JSON and putting values in Views
        tino.setText(tinum());
//==================================================================================================================
       //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //hide soft keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//==================================================================================================================
        // Spinner enterBy
        final Spinner enterby_spinner = (Spinner)findViewById(R.id.enterby_spinner);
        List<String> name_list = new ArrayList<String>();
        //JSON read names added here
        for(int i=0;i<3;i++)
        {
            name_list.add(enterby(i));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,name_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enterby_spinner.setAdapter(arrayAdapter);
        // Spinner item selected, text change in TextViews
        enterby_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Approving Authority Textview change
                String name = String.valueOf(approveAuth(position));
                approvename.setText(name);
                //TI Created for Texteview change
                String nameSelected = enterby_spinner.getSelectedItem().toString()+tiforExtract(position);
                tifor.setText(nameSelected);
                //preferred name TextView change
                String pref_name = String.valueOf(prefname(position));
                prefname.setText(pref_name);
               //dept name Textview change
               dept.setText(deptname(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//========================================================================================================================
        //Spinner Travel type
        Spinner travelby = (Spinner)findViewById(R.id.traveltype_spinner);
        List<String> travel_list = new ArrayList<String>();
        //JSON read travel type added
        for(int j=0;j<3;j++)
        {
            travel_list.add(traveltype(j));
        }
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,travel_list);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelby.setAdapter(arrayAdapter1);

//==========================================================================================================================

        //Current Date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        String date = simpleDateFormat.format(c);
        TextView dateTextView = (TextView)findViewById(R.id.date);
        dateTextView.setText(date);

//==========================================================================================================================

        //submit button
        Button submit_btn = (Button)findViewById(R.id.submit_button);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = contacts.getText().toString().trim();
                if (TextUtils.isEmpty(number))
                {
                    Toast.makeText(CreateTI.this,"Please Enter Your Contact details",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent travel_intent = new Intent(CreateTI.this, addTI.class);
                    sharedPreferences = getApplication().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("tino",tino.getText().toString());
                    editor.putString("officername",enterby_spinner.getSelectedItem().toString());
                    editor.putString("contactno",contacts.getText().toString());
                    editor.putString("status",status.getText().toString());
                    editor.putString("dept",dept.getText().toString());
                    editor.putString("approvingauth",approvename.getText().toString());
                    editor.apply();
                    startActivity(travel_intent);
                }
            }
        });
//========================================================================================================================


    } //end of onCreate()

    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(CreateTI.this, MainActivity.class));
        finish();
        return true;
    }

//==================================================================================================

    //TODO: remove loadJSON() therefore make changes in JSON as well
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
    String loadJSONfromAssets()
    {
        String json = null;
        try {
            InputStream is = getAssets().open("dataset.json");
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
    // retrieve TI number from JSON
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
    // retrieve enterBy name from JSON
    String enterby(int index)
    {
        String officer_name = "";
        String empname = "";
        String empno = "";
        try{
            JSONObject object = new JSONObject(loadJSONfromAssets());
            JSONArray arr = object.getJSONArray("array");
            JSONObject arr_obj = arr.getJSONObject(index);
            empname = arr_obj.getString("emp_name");
            empno = arr_obj.getString("emp_no");
            officer_name = empname+" ("+empno+")";
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return officer_name;
    }
//===================================================================================================
    //retrieve name of the officer whose TI is created from JSON
    String tiforExtract(int index)
    {
        String grade = "";
        String final_grade = "";

        try{
            JSONObject object = new JSONObject(loadJSONfromAssets());
            JSONArray arr = object.getJSONArray("array");
            JSONObject arr_obj = arr.getJSONObject(index);
            grade = arr_obj.getString("emp_grade");
            final_grade = " - "+grade;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return final_grade;
    }
//==================================================================================================

    // retrieve preferred name of the officer from JSON
    String prefname(int index)
    {
        String prefname = "";
        try{
            JSONObject object = new JSONObject(loadJSONfromAssets());
            JSONArray arr = object.getJSONArray("array");
            JSONObject arr_obj = arr.getJSONObject(index);
            prefname = arr_obj.getString("emp_name");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return prefname;
    }
//===================================================================================================
    //retrieve name of the dept from JSON
    String deptname(int index)
    {
        String deptno = "";
        String dept_name = "";
        String final_deptname = "";

        try{
            JSONObject object = new JSONObject(loadJSONfromAssets());
            JSONArray arr = object.getJSONArray("array");
            JSONObject arr_obj = arr.getJSONObject(index);
            deptno = arr_obj.getString("emp_bu");
            dept_name = arr_obj.getString("emp_budesc");
            final_deptname = deptno + " - "+dept_name;

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return final_deptname;
    }

//===================================================================================================

    // retrieve travel type from JSON
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
    // retrieve RO name from JSON as approving authority
    String approveAuth(int index)
    {
        String approvename = "";
        String officername = "";
        String officer_grade = "";
        JSONObject object = null;
        try {
            object = new JSONObject(loadJSONfromAssets());
            JSONArray arr = object.getJSONArray("array");
            JSONObject arr_obj = arr.getJSONObject(index);
            officername = arr_obj.getString("ro_name");
            officer_grade = arr_obj.getString("ro_grade");
            approvename = officername + " ("+officer_grade+")";

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return approvename;
    }
//==================================================================================================
} //end of CreateTI Activity
