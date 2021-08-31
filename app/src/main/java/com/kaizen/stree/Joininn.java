package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Joininn extends AppCompatActivity {

    AppCompatButton clickher;
    ImageView backj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joininn);
        getSupportActionBar().hide();

        backj = findViewById(R.id.backj);


        clickher= findViewById(R.id.clickher);

        backj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        clickher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
   Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSegXtwgIYAtu6P7uSelnPdkwc7-NQ3FEE6HeA2op8JImOGtmQ/viewform"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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