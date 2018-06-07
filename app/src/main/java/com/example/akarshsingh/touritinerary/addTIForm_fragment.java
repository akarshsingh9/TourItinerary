package com.example.akarshsingh.touritinerary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class addTIForm_fragment extends Fragment {

    RelativeLayout resLayout;
    //private OnFragmentInteractionListener mListener;

    public addTIForm_fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_tiform,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button setbtn,setbtn1;
        final EditText timedept,datedept;
        Spinner travelmode,fromspinner, tospinner;
        FloatingActionButton submitfab;


        setbtn = (Button)view.findViewById(R.id.frag_setbtn);
        setbtn1 = (Button)view.findViewById(R.id.frag_setbtn1);
        timedept = (EditText)view.findViewById(R.id.frag_timedept);
        datedept = (EditText)view.findViewById(R.id.frag_datedept);
        travelmode = (Spinner)view.findViewById(R.id.frag_travelmode_spinner);
        fromspinner = (Spinner)view.findViewById(R.id.frag_from_spinner);
        tospinner = (Spinner)view.findViewById(R.id.frag_to_spinner);
        submitfab = (FloatingActionButton)view.findViewById(R.id.frag_submit_fab);
        resLayout = (RelativeLayout)view.findViewById(R.id.reslayout);

        //travel mode spinner
        List<String> travelmode_list = new ArrayList<String>();
        travelmode_list.add("Rail");
        travelmode_list.add("Flight");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,travelmode_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelmode.setAdapter(arrayAdapter);

        //from spinner
        List<String> fromplaces = new ArrayList<String>();
        fromplaces.add("Delhi");
        fromplaces.add("Mumbai");
        fromplaces.add("Ahmedabad");
        fromplaces.add("Chennai");
        ArrayAdapter<String> fromarrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,fromplaces);
        fromarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromspinner.setAdapter(fromarrayAdapter);

        //To spinner
        List<String> toplaces = new ArrayList<String>();
        toplaces.add("Delhi");
        toplaces.add("Mumbai");
        toplaces.add("Ahmedabad");
        toplaces.add("Chennai");
        ArrayAdapter<String> toarrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,toplaces);
        toarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tospinner.setAdapter(toarrayAdapter);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDay = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
                final Calendar calendar = Calendar.getInstance();
                int mMinute = calendar.get(calendar.MINUTE);
                int mHour = calendar.get(calendar.HOUR_OF_DAY);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timedept.setText(""+hourOfDay+":"+""+minute);
                    }
                },mHour,mMinute,false);

                timePickerDialog.show();
            }

        });

        submitfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


}
