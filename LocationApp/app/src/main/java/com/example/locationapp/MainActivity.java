package com.example.locationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, (float) 0, (android.location.LocationListener) locationListener);
                }
            } else {
                // La permission a été refusée, faites quelque chose ici
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btngps = (Button) findViewById(R.id.btngps);
        Button btngprs = (Button) findViewById(R.id.btngprs);
        TextView textlat = (TextView) findViewById(R.id.textlat);
        TextView textlong = (TextView) findViewById(R.id.textlong);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPSLocator gpsLocator = new GPSLocator(getApplicationContext());
        Location location = gpsLocator.GetLocation();
        if(location != null){
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            textlat.setText(String.valueOf(latitude));
            textlong.setText(String.valueOf(location.getLongitude()));
        }

        btngps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSLocator gpsLocator = new GPSLocator(getApplicationContext());
                Location location = gpsLocator.GetLocation();
                if(location != null){
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();

                    textlat.setText(String.valueOf(latitude));
                    textlong.setText(String.valueOf(location.getLongitude()));
                }
            }
        });

    }


}