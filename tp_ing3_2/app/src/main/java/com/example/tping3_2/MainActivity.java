package com.example.tping3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity  extends AppCompatActivity {

    String globalname = "globalname";
    String globalemail = "globalemail";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonp11 = (Button) findViewById(R.id.buttonp11);
        Button buttonp12 = (Button) findViewById(R.id.buttonp12);
        EditText nameT = (EditText) findViewById(R.id.editnom2);
        EditText emailT = (EditText) findViewById(R.id.editemail);
        buttonp11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                String name = nameT.getText().toString();
                String email = emailT.getText().toString();
                intent.putExtra(globalname,name);
                intent.putExtra(globalemail, email);
                startActivity(intent);
            }
        });
        buttonp12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity3.class);
                startActivity(intent);
            }
        });
    }
}
