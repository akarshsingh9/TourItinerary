package com.example.akarshsingh.touritinerary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
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

    Button setbtn,setbtn1;
    EditText timedept,datedept,purposeEditText;
    Spinner travelmode,fromspinner, tospinner;
    FloatingActionButton submitfab;
    SharedPreferences preferences;

    public int counterRecieved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ti_form);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setbtn = (Button)findViewById(R.id.setbtn);
        setbtn1 = (Button)findViewById(R.id.setbtn1);
        timedept = (EditText)findViewById(R.id.timedept);
        datedept = (EditText)findViewById(R.id.datedept);
        travelmode = (Spinner)findViewById(R.id.travelmode_spinner);
        fromspinner = (Spinner)findViewById(R.id.from_spinner);
        tospinner = (Spinner)findViewById(R.id.to_spinner);
        submitfab = (FloatingActionButton)findViewById(R.id.submit_fab);
        purposeEditText = (EditText)findViewById(R.id.purpose);

        //travel mode spinner
        List<String> travelmode_list = new ArrayList<String>();
        travelmode_list.add("Rail");
        travelmode_list.add("Flight");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,travelmode_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelmode.setAdapter(arrayAdapter);

        //from spinner
        List<String> fromplaces = new ArrayList<String>();
        fromplaces.add("Delhi");
        fromplaces.add("Mumbai");
        fromplaces.add("Ahmedabad");
        fromplaces.add("Chennai");
        ArrayAdapter<String> fromarrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fromplaces);
        fromarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromspinner.setAdapter(fromarrayAdapter);

        //To spinner
        List<String> toplaces = new ArrayList<String>();
        toplaces.add("Delhi");
        toplaces.add("Mumbai");
        toplaces.add("Ahmedabad");
        toplaces.add("Chennai");
        ArrayAdapter<String> toarrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,toplaces);
        toarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tospinner.setAdapter(toarrayAdapter);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addTI_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datedept.setText(""+dayOfMonth+"/"+""+(month+1)+"/"+""+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();

            }
        });

        setbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(travelmode.getSelectedItem().toString(),"Flight"))
                {
                    final Calendar calendar = Calendar.getInstance();
                    int mMinute = calendar.get(calendar.MINUTE);
                    int mHour = calendar.get(calendar.HOUR_OF_DAY);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(addTI_Form.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            timedept.setText(""+hourOfDay+":"+""+minute);
                        }
                    },mHour,mMinute,false);

                    timePickerDialog.show();

                }
                else
                {
                    Toast.makeText(addTI_Form.this,"Applicable only on Flight",Toast.LENGTH_SHORT).show();
                }
                            }

        });

        submitfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(addTI_Form.this,addTI.class);
                intent.putExtra("flag",1);
                intent.putExtra("from",fromspinner.getSelectedItem().toString());
                intent.putExtra("to",tospinner.getSelectedItem().toString());
                intent.putExtra("travelmode",travelmode.getSelectedItem().toString());
                intent.putExtra("date",datedept.getText().toString());
                intent.putExtra("time",timedept.getText().toString());
                intent.putExtra("purpose",purposeEditText.getText().toString());


                startActivity(intent);
            }

        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
