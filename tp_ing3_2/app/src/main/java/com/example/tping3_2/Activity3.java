package com.example.tping3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Activity3 extends AppCompatActivity {
    String globaldate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        Button buttonp31 = (Button) findViewById(R.id.buttonp31);
        Button buttonp32 = (Button) findViewById(R.id.buttonp32);
        Button buttonp33 = (Button) findViewById(R.id.buttonp33);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        buttonp32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String date = dateFormat.format(calendar.getDate());
                intent.putExtra(globaldate, date);
                startActivity(intent);
            }
        });
        buttonp33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
