package com.example.mypc.project_02;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActSplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ActSplashScreen.this, MainActivity.class);
                ActSplashScreen.this.startActivity(mainIntent);
                ActSplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
