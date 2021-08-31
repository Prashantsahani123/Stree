package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    ImageView direct, about ,ourl, goal, joiniin, shepre;
    ImageView refreshh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        /////////////////////////////////////

        direct = findViewById(R.id.Dir);
        about = findViewById(R.id.about);
        ourl = findViewById(R.id.ourl);
        joiniin = findViewById(R.id.joini);
        shepre = findViewById(R.id.spen);
        goal = findViewById(R.id.goals);


        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Directorysub.class);
                startActivity(intent);

            }
        });



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Abouts.class);
                startActivity(intent);

            }
        });

        ourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Ourlder.class);
                startActivity(intent);

            }
        });
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //https://projectshare.rotaryindia.org/CovidExcelData/finalgoal.pdf

                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/GOALS.pdf"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        joiniin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Joininn.class);
                startActivity(intent);

            }
        });
        shepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),sheprenus.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   finish();
        finishAffinity();
    }
}