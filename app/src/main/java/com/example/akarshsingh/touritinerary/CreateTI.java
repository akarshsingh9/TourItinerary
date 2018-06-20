package com.example.akarshsingh.touritinerary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
        tino.setText("2016_123123");
//==================================================================================================================
       //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //hide soft keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//==================================================================================================================
        // Spinner enterBy
        final Spinner enterby_spinner = (Spinner)findViewById(R.id.enterby_spinner);
        final List<String> name_list = new ArrayList<String>();
        name_list.add("Select a name");
        final List<String> authName = new ArrayList<>();
        final List<String> empName = new ArrayList<>();
        final List<String> deptName = new ArrayList<>();
        final List<String> tiForName = new ArrayList<>();
        //JSON read names added here
        String url ="http://10.0.2.2:8080/emp.jsp";

        //Volley used to retrieve JSON
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String officer_name = "";
                    String empname = "";
                    String empno = "";
                    String empgrade = "";
                    String tifor = "";
                    String deptname = "";
                    String auth = "";
                    //JSONArray arr = response.getJSONArray("array");
                    for (int index=0;index<response.length();index++)
                    {
                        JSONObject arr_obj = response.getJSONObject(index);
                        empname = arr_obj.getString("emp_name");
                        empName.add(empname);
                        empno = arr_obj.getString("emp_no");
                        officer_name = empname+" ("+empno+")";
                        name_list.add(officer_name);

                        empgrade = arr_obj.getString("emp_grade");
                        tifor = officer_name + " - "+empgrade;
                        tiForName.add(tifor);
                        deptname = arr_obj.getString("emp_bu") + " - " +arr_obj.getString("emp_budesc");
                        deptName.add(deptname);
                        auth = arr_obj.getString("ro_name") + " ("+arr_obj.getString("ro_grade") +")";
                        authName.add(auth);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(arrayRequest);


        // from array adapter
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,name_list)
        {
            @Override
            public boolean isEnabled(int position) {
                if (position==0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView)view;
                if (position==0)
                {
                    textView.setTextColor(Color.GRAY);
                }
                else
                {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }


        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        enterby_spinner.setAdapter(arrayAdapter);


        // Spinner item selected, text change in TextViews
        enterby_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = position - 1;
                if (pos>=0) {
                    //Approving Authority Textview change
                    approvename.setText(authName.get(pos));
                    //TI Created for Texteview change
                    tifor.setText(tiForName.get(pos));
                    //preferred name TextView change
                    prefname.setText(empName.get(pos));
                    //dept name Textview change
                    dept.setText(deptName.get(pos));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//========================================================================================================================
        //Spinner Travel type
        Spinner travelby = (Spinner)findViewById(R.id.traveltype_spinner);
        List<String> travel_list = new ArrayList<String>();
        travel_list.add("Travel for Meeting");
        travel_list.add("Travel for Training");
        travel_list.add("Other Reason");
        //JSON read travel type added
        /*
        for(int j=0;j<3;j++)
        {
            travel_list.add(traveltype(j));
        }*/
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


//==================================================================================================
} //end of CreateTI Activity
