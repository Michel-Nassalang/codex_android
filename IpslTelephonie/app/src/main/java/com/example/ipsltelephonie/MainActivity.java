package com.example.ipsltelephonie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String sms = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttoncall = (Button) findViewById(R.id.buttoncall);
        EditText editphone = (EditText) findViewById(R.id.editphone);
        Button buttonsms = (Button) findViewById(R.id.buttonsms);
        EditText editsms = (EditText) findViewById(R.id.editsms);

        buttoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(view, editphone);
            }
        });
        buttonsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view, editphone, editsms);
            }
        });

    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void call(View view, EditText editphone){
        String numcall = editphone.getText().toString();
        Uri telipsl = Uri.parse("tel:"+numcall);
        Intent ipslcall = new Intent(Intent.ACTION_DIAL, telipsl);
        startActivity(ipslcall);
    }
    public void sendMessage(View view, EditText editphone, EditText editsms){
        String phoneNumber = editphone.getText().toString();
        String message = editsms.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        sms = message;
        if(phoneNumber!=null & message!=null){
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            toast("SMS envoyé !");
        }else{
            toast("SMS non envoyé !");
        }

        Intent intent = new Intent(getApplicationContext(), SmsActivity.class);
        intent.putExtra("sms", message);
        startActivity(intent);
    }
}