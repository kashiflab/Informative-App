package com.limecoders.informativeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences("com.limecoders.informativeapp",MODE_PRIVATE);


        Log.i("helllllloooo",preferences.getString("hello","0"));
//        Toast.makeText(this, preferences.getString("hello","0"), Toast.LENGTH_SHORT).show();

        Thread timer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent =new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();

    }
}