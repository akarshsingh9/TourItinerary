package com.example.akarshsingh.touritinerary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        TextView tino;
       //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Spinner Name
        Spinner enterby_spinner = (Spinner)findViewById(R.id.enterby_spinner);
        List<String> name_list = new ArrayList<String>();
        name_list.add("Arun Kumar Panda (31919150)");
        name_list.add("Mohan Lal (31919151)");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,name_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enterby_spinner.setAdapter(arrayAdapter);

        //Spinner Travel Mode
        Spinner travelby = (Spinner)findViewById(R.id.traveltype_spinner);
        List<String> travel_list = new ArrayList<String>();
        travel_list.add("Travel for Training");
        travel_list.add("Travel for Meeting");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,travel_list);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelby.setAdapter(arrayAdapter1);

        //Current Date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        String date = simpleDateFormat.format(c);
        TextView dateTextView = (TextView)findViewById(R.id.date);
        dateTextView.setText(date);

        Button submit_btn = (Button)findViewById(R.id.submit_button);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent travel_intent = new Intent(CreateTI.this, addTI.class);
                startActivity(travel_intent);
            }
        });

        /*
        String name = enterby_spinner.getSelectedItem().toString();
        String travelmode = travelby.getSelectedItem().toString();
*/

       /* Intent intent = getIntent();
        int id = intent.getIntExtra("click",1);
        try {
            JSONObject object = new JSONObject(loadJSON());
            JSONArray tno = object.getJSONArray("tino");
            int i = 0;
            if(id==1){
                i++;
                tino = (TextView)findViewById(R.id.tino);
                tino.setText(tno.getString(i));
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
*/
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //not working
  String loadJSON()
  {
      String json = null;
      try {
          InputStream is = getAssets().open("locations.json");
          int size = is.available();
          byte[] buffer = new byte[size];
          is.read(buffer);
          is.close();
          json = new String(buffer, "UTF-8");


      } catch (IOException e) {
          e.printStackTrace();
      }
      return json;

  }




}
