package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class sheprenus extends AppCompatActivity {

    AppCompatButton emb , myst, ideas, govern, wrk, digit, facb, ecom;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheprenus);
        getSupportActionBar().hide();

        emb= findViewById(R.id.embr);
        myst = findViewById(R.id.mystr);
        ideas = findViewById(R.id.ideas);
        govern = findViewById(R.id.gover);
        wrk  = findViewById(R.id.work);
        digit = findViewById(R.id.digit);
        facb = findViewById(R.id.faceb);
        ecom = findViewById(R.id.eccom);
        back = findViewById(R.id.backs);
//prashantg

        emb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Embrace-the-Challenges.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        myst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/My_Stories_session.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Ideas_Concept-&-Insights.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        govern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Government_Benefits.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        wrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Work_life_balance.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        digit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Digitalmarketing.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        facb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Facebook-for-Business.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://projectshare.rotaryindia.org/CovidExcelData/Ecommerce%20_with_Amazon.jpeg"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}