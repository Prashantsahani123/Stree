package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {



                Intent i = new Intent( getApplicationContext(), Splass.class );

                i.putExtra( "fromApp", "yes" );  // fromApp is added to SplashImageSlider is launched from this point.

                startActivity( i );

                finish();



            }



        }, SPLASH_TIME_OUT );

    }
}
