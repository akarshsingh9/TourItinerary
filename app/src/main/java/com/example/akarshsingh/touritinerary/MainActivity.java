package com.example.akarshsingh.touritinerary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*Documentation:
* MainActivity => is the starting activity in Tour Itinerary Android App
* MainActivity has 2 main functionality that can be accessed using 2 Buttons in the beginning
* 2 Buttons are:-
* CreateTI - This will help you to create Tour Itinerary
* ListTI - This will list all your TI's according to the TI number. Will also show status and name of the officer
* Intent passed to another activity when CreateTI and ListTI is pressed*/


public class MainActivity extends AppCompatActivity {

    //Initializing Button variables
    Button createTI_button, listTI_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewByID for buttons initialized
        createTI_button = (Button)findViewById(R.id.createTI);
        listTI_button = (Button)findViewById(R.id.listTI);

        // defining onClickListener() for the buttons and performing intent to another activity
        createTI_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTI.class);
                //intent.putExtra("click",1);
                startActivity(intent);
            }
        });

        listTI_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListTI.class);
                startActivity(intent);
            }
        });
    }
}
