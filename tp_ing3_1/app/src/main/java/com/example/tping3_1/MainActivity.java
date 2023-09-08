package com.example.tping3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String globalresult = "globalresult";
    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bplus = (Button) findViewById(R.id.bplus);
        Button bmoins = (Button) findViewById(R.id.bmoins);
        Button bmult = (Button) findViewById(R.id.bmult);
        Button bdiv = (Button) findViewById(R.id.bdiv);
        Button begal = (Button) findViewById(R.id.begal);
        EditText edit1 = (EditText) findViewById(R.id.edit1);
        EditText edit2 = (EditText) findViewById(R.id.edit2);

        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "add";
            }
        });
        bmoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "sous";
            }
        });
        bmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "mult";
            }
        });
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "div";
            }
        });
        begal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                String op1 = edit1.getText().toString();
                String op2 = edit2.getText().toString();
                float op11 = Float.parseFloat(op1);
                float op22 = Float.parseFloat(op2);
                float result = 0;
                if(op1!=null & op2!=null){
                    if(oper == "add"){
                        result = op11 + op22;
                    } else if (oper == "sous") {
                        result = op11 - op22;
                    } else if (oper == "mult") {
                        result = op11 * op22;
                    } else if (oper == "div") {
                        result = op11 / op22;
                    }
                    intent.putExtra(globalresult, String.valueOf(result));
                    startActivity(intent);
                }

            }
        });
    }

}