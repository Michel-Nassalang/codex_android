package com.example.tping3_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    String globalresult = "globalresult";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity2);

        TextView textResult = (TextView) findViewById(R.id.textresult);

        Intent recapIntent = getIntent();
        if(recapIntent != null){
            String result = recapIntent.getStringExtra(globalresult);
            textResult.setText(result);
        }
    }
}
