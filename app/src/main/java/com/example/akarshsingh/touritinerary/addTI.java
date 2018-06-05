package com.example.akarshsingh.touritinerary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class addTI extends AppCompatActivity {

    CardView officer_cardView;
    FloatingActionButton add_fab;
    RelativeLayout enclosingLayout, nameHeaderLayout, expandLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ti);
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        officer_cardView = (CardView)findViewById(R.id.officer_cardView);
        add_fab = (FloatingActionButton)findViewById(R.id.add_fab);

        enclosingLayout = (RelativeLayout)findViewById(R.id.enclosing_layout);
        nameHeaderLayout = (RelativeLayout)findViewById(R.id.nameHeader_layout);
        expandLayout = (RelativeLayout)findViewById(R.id.expand_officerInfo);

        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addTI.this, addTI_Form.class);
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
