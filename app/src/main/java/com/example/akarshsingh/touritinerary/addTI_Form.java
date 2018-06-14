package com.example.akarshsingh.touritinerary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addTI_Form extends AppCompatActivity {

// Views variables declared
    Button submit;
    RadioGroup radioGroup;
    RadioButton oneWay, roundTrip;
    Spinner travelModeSpinner, fromplaceSpinner, toplaceSpinner, prefTimeSpinner, prefDestinationSpinner;
    EditText deptDate, reachDate, deptDestinationDate, reachDestinationDate, daysStayed, purpose, remarks;
    ImageButton deptCal, reachCal, deptDestinationCal, reachDestinationCal;
    String travelmodeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ti_form);
//===========================================================================================================
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //hide soft keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//==============================================================================================================
        //view initialization
        submit = (Button)findViewById(R.id.submitFormBtn);
        radioGroup = (RadioGroup)findViewById(R.id.tripRadioGroup);
        oneWay = (RadioButton)findViewById(R.id.oneway);
        roundTrip = (RadioButton)findViewById(R.id.roundtrip);
        travelModeSpinner = (Spinner)findViewById(R.id.travelmodeSpinner);
        fromplaceSpinner = (Spinner)findViewById(R.id.fromSpinner);
        toplaceSpinner = (Spinner)findViewById(R.id.toSpinner);
        prefTimeSpinner = (Spinner)findViewById(R.id.prefTime);
        prefDestinationSpinner = (Spinner)findViewById(R.id.prefTime2);
        deptDate = (EditText)findViewById(R.id.deptEditText);
        deptDestinationDate = (EditText)findViewById(R.id.deptEditText2);
        reachDate = (EditText)findViewById(R.id.reachEditText);
        reachDestinationDate = (EditText)findViewById(R.id.reachEditText2);
        daysStayed = (EditText)findViewById(R.id.stayEditText);
        purpose = (EditText)findViewById(R.id.purposeEditText);
        remarks = (EditText)findViewById(R.id.remarksEditText);
        deptCal = (ImageButton)findViewById(R.id.deptDateImageButton);
        deptDestinationCal = (ImageButton)findViewById(R.id.deptDateImageButton2);
        reachCal = (ImageButton)findViewById(R.id.reachImageButton);
        reachDestinationCal = (ImageButton)findViewById(R.id.reachImageButton2);
//===================================================================================================================
        //travelmode Spinner
        List<String> travelMode_list = new ArrayList<>();
        travelMode_list.add("Select a Travel Mode");
        travelMode_list.add("Air");
        travelMode_list.add("Rail");
        travelMode_list.add("Own Car");
        travelMode_list.add("Others");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,travelMode_list)
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
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        travelModeSpinner.setAdapter(spinnerAdapter);

        travelModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    travelmodeSelected = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//=========================================================================================================================


//=============================================================================================================================
    } //end of onCreate()
//==========================================================================================================================
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
//========================================================================================================================


} //end of addTI_Form
