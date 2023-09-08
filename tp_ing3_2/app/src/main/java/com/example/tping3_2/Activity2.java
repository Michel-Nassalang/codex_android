package com.example.tping3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    String globalname = "globalname";
    String globalemail = "globalemail";
    String globaldate;
    @Override
    public void onCreate(@Nullable Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity2);
        Button buttonp21 = (Button) findViewById(R.id.buttonp21);
        Button buttonp22 = (Button) findViewById(R.id.buttonp22);
        TextView name2 = (TextView) findViewById(R.id.editnom2);
        TextView email2 = (TextView) findViewById(R.id.editemail2);
        EditText editdate = (EditText) findViewById(R.id.dateId);
        Intent recapIntent = getIntent();
         if(recapIntent != null){
             String name = recapIntent.getStringExtra(globalname);
             String email = recapIntent.getStringExtra(globalemail);
             String date = recapIntent.getStringExtra(globaldate);
             name2.setText(name);
             email2.setText(email);
             editdate.setText(date);
         }
        buttonp21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity3.class);
                startActivity(intent);
            }
        });
        buttonp22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
