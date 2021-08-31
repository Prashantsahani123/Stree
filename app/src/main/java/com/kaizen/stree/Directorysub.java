package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Directorysub extends AppCompatActivity {

    AppCompatButton directry, searchh ;
    ImageView back;
    Databasehelper databasehelper= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorysub);
        getSupportActionBar().hide();


        databasehelper = new Databasehelper(getApplicationContext());

        back = findViewById(R.id.bacm);

        directry = findViewById(R.id.diretoryy);

        searchh = findViewById(R.id.searchh);

        if(databasehelper.getcount() <= 0)
        {
            loaddirect();
        }



        directry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Directory.class);

                startActivity(intent);

            }
        });

        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Searchdirect.class);

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

    public void loaddirect() {

        // dialog = new Dialog(Gallery.this, android.R.style.Theme_Translucent);
        final Dialog dialog = new Dialog(Directorysub.this, android.R.style.Theme_Translucent);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.endt_popup);
        dialog.setCancelable(false);

        final RecyclerView ongrecyl;
        TextView pro_title;
        TextView tv_yes = (TextView) dialog.findViewById(R.id.yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Directory.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });


        dialog.show();
    }

}