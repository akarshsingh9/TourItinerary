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
    TextView deptTV,deptTV2,reachTV,reachTV2,prefTV,prefTV2;
    String travelmodeSelected,fromplaceSelected,toplaceSelected, prefTimeSelected, prefDestinationSelected;


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
        deptTV = (TextView)findViewById(R.id.deptText);
        deptTV2 = (TextView)findViewById(R.id.deptText2);
        reachTV = (TextView)findViewById(R.id.reachingText);
        reachTV2 = (TextView)findViewById(R.id.reachingText2);
        prefTV = (TextView)findViewById(R.id.prefText);
        prefTV2 = (TextView)findViewById(R.id.prefText2);
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
        //from spinner
        List<String> fromlist = new ArrayList<String>();
        fromlist.add("Select a City");
        fromlist.add("Ahmedabad");
        fromlist.add("Bangalore");
        fromlist.add("Chennai");
        fromlist.add("Delhi");
        fromlist.add("Kolkata");
        fromlist.add("Mumbai");
        fromlist.add("Patna");
        fromlist.add("Ranchi");


        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,fromlist)
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
        fromAdapter.setDropDownViewResource(R.layout.spinner_item);
        fromplaceSpinner.setAdapter(fromAdapter);

        fromplaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    fromplaceSelected = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//===========================================================================================================================
        //to spinner
        List<String> tolist = new ArrayList<String>();
        fromlist.add("Select a City");
        fromlist.add("Ahmedabad");
        fromlist.add("Bangalore");
        fromlist.add("Chennai");
        fromlist.add("Delhi");
        fromlist.add("Kolkata");
        fromlist.add("Mumbai");
        fromlist.add("Patna");
        fromlist.add("Ranchi");


        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,tolist)
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
        toAdapter.setDropDownViewResource(R.layout.spinner_item);
        toplaceSpinner.setAdapter(fromAdapter);

        toplaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    toplaceSelected = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
 //========================================================================================================================
        //pref time spinner
        List<String> prefList = new ArrayList<String>();
        prefList.add("Select an option");
        prefList.add("0-1");
        prefList.add("1-2");
        prefList.add("2-3");
        prefList.add("3-4");
        prefList.add("5-6");
        prefList.add("6-7");
        prefList.add("7-8");
        prefList.add("8-9");
        prefList.add("9-10");
        prefList.add("10-11");
        prefList.add("11-12");
        prefList.add("12-13");
        prefList.add("13-14");
        prefList.add("14-15");
        prefList.add("15-16");
        prefList.add("16-17");
        prefList.add("17-18");
        prefList.add("18-19");
        prefList.add("19-20");
        prefList.add("20-21");
        prefList.add("21-22");
        prefList.add("22-23");
        prefList.add("23-24");

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,prefList)
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
        timeAdapter.setDropDownViewResource(R.layout.spinner_item);
        prefTimeSpinner.setAdapter(timeAdapter);

        prefTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    prefTimeSelected = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//==========================================================================================================================
        //pref destination spinner
        ArrayAdapter<String> preftimeAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,prefList)
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
        preftimeAdapter.setDropDownViewResource(R.layout.spinner_item);
        prefDestinationSpinner.setAdapter(preftimeAdapter);

        prefDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    prefDestinationSelected = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//===========================================================================================================================
        //dept date Calendar
        deptCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addTI_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        deptDate.setText(""+dayOfMonth+"/"+""+(month+1)+"/"+""+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();
            }
        });
//=============================================================================================================================
        //reaching date Calendar
        reachCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addTI_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        reachDate.setText(""+dayOfMonth+"/"+""+(month+1)+"/"+""+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();
            }
        });
//=============================================================================================================================
        //dept date destination calendar
        deptDestinationCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addTI_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        deptDestinationDate.setText(""+dayOfMonth+"/"+""+(month+1)+"/"+""+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();
            }
        });
//===========================================================================================================================
        //reach date destination calendar
        reachDestinationCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addTI_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        reachDestinationDate.setText(""+dayOfMonth+"/"+""+(month+1)+"/"+""+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();
            }
        });
//=============================================================================================================================
        //travelmode logic on air and other modes in one way and round trip
        travelModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String air = parent.getSelectedItem().toString();
                if (air == "Air")
                {
                    if (oneWay.isChecked())
                    {
                        deptTV2.setVisibility(View.GONE);
                        deptDestinationDate.setVisibility(View.GONE);
                        deptDestinationCal.setVisibility(View.GONE);
                        reachTV2.setVisibility(View.GONE);
                        reachDestinationCal.setVisibility(View.GONE);
                        reachDestinationDate.setVisibility(View.GONE);
                        prefTV.setVisibility(View.VISIBLE);
                        prefTimeSpinner.setVisibility(View.VISIBLE);
                        prefTV2.setVisibility(View.GONE);
                        prefDestinationSpinner.setVisibility(View.GONE);
                    }
                    else if (roundTrip.isChecked())
                    {
                        deptTV2.setVisibility(View.VISIBLE);
                        deptDestinationDate.setVisibility(View.VISIBLE);
                        deptDestinationCal.setVisibility(View.VISIBLE);
                        reachTV2.setVisibility(View.VISIBLE);
                        reachDestinationCal.setVisibility(View.VISIBLE);
                        reachDestinationDate.setVisibility(View.VISIBLE);
                        prefTV.setVisibility(View.VISIBLE);
                        prefTimeSpinner.setVisibility(View.VISIBLE);
                        prefTV2.setVisibility(View.VISIBLE);
                        prefDestinationSpinner.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    if (oneWay.isChecked())
                    {
                        prefTV.setVisibility(View.GONE);
                        prefTimeSpinner.setVisibility(View.GONE);
                        prefTV2.setVisibility(View.GONE);
                        prefDestinationSpinner.setVisibility(View.GONE);
                        deptTV2.setVisibility(View.GONE);
                        deptDestinationDate.setVisibility(View.GONE);
                        deptDestinationCal.setVisibility(View.GONE);
                        reachTV2.setVisibility(View.GONE);
                        reachDestinationCal.setVisibility(View.GONE);
                        reachDestinationDate.setVisibility(View.GONE);

                    }
                    else if (roundTrip.isChecked())
                    {
                        deptTV2.setVisibility(View.VISIBLE);
                        deptDestinationDate.setVisibility(View.VISIBLE);
                        deptDestinationCal.setVisibility(View.VISIBLE);
                        reachTV2.setVisibility(View.VISIBLE);
                        reachDestinationCal.setVisibility(View.VISIBLE);
                        reachDestinationDate.setVisibility(View.VISIBLE);
                        prefTV.setVisibility(View.GONE);
                        prefTimeSpinner.setVisibility(View.GONE);
                        prefTV2.setVisibility(View.GONE);
                        prefDestinationSpinner.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//==========================================================================================================================
        //submit btn onClick
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addTI_Form.this,addTI.class);
                intent.putExtra("flag",1);
                intent.putExtra("from",fromplaceSpinner.getSelectedItem().toString());
                intent.putExtra("to",toplaceSpinner.getSelectedItem().toString());
                intent.putExtra("travelmode",travelModeSpinner.getSelectedItem().toString());
                intent.putExtra("date",deptDate.getText().toString());
                intent.putExtra("time",prefTimeSpinner.getSelectedItem().toString());
                intent.putExtra("purpose",purpose.getText().toString());
                startActivity(intent);

            }
        });
    } //end of onCreate()
//==========================================================================================================================
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
//========================================================================================================================


} //end of addTI_Form
