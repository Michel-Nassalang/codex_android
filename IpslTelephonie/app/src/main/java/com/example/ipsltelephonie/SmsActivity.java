package com.example.ipsltelephonie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {
    String sms = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_smsactivity);
        Button btn = (Button) findViewById(R.id.buttonreset);
        EditText editSms = (EditText) findViewById(R.id.recapsms);
        Intent intentSms = getIntent();
        if(intentSms !=null){
            String message = intentSms.getStringExtra(sms);
            editSms.setText(message);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreset = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentreset);
            }
        });
    }
}
