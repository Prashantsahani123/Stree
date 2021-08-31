package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class Searchdirect extends AppCompatActivity {

    AppCompatButton  search;
    EditText namee, clssf, cty, clu, dist,mbl;
    ImageView bk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdirect);

        getSupportActionBar().hide();

        search = findViewById(R.id.search);

        namee = findViewById(R.id.namee);
        clssf = findViewById(R.id.clssf);
        cty = findViewById(R.id.cty);
        clu = findViewById(R.id.clu);
        dist = findViewById(R.id.dist);
        mbl = findViewById(R.id.mbl);

        bk = findViewById(R.id.bk);

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nm = namee.getText().toString().trim();
                String clasf = clssf.getText().toString().trim();
                String ctyy = cty.getText().toString().trim();
                String clb = clu.getText().toString().trim();
                String dst = dist.getText().toString().trim();
                String mb = mbl.getText().toString().trim();

                if(nm.equalsIgnoreCase("") && clasf.equalsIgnoreCase("") && ctyy.equalsIgnoreCase("")
                && clb.equalsIgnoreCase("") && dst.equalsIgnoreCase("") && mb.equalsIgnoreCase(""))
                {
                    Toast.makeText(Searchdirect.this, "Please enter search key", Toast.LENGTH_SHORT).show();
                }
                else {
                    new AsyncTaskExample(nm, clasf, ctyy, clb, dst, mb).execute();
                }
              // List<Parking> data =  new Databasehelper(getApplicationContext()).fetchsearch("sonia","inne");

            }
        });


    }

    private class AsyncTaskExample extends AsyncTask<String, String, String> {
        String nm= "";
        String cls = "";
        String cit = "";
        String club = "";
        String dist = "";
        String phon = "";

        public  AsyncTaskExample(String nam , String clss, String citt, String clubb, String distt, String phone)
        {
            this.nm = nam;
            this.cls = clss;
            this.cit = citt;
            this.club = clubb;
            this.dist = distt;
            this.phon = phone;
        }


        String ret = "";
        ProgressDialog p;
        List<Parking> data= null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(Searchdirect.this);
            p.setMessage("Please wait...");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected String doInBackground(String... strings) {

             data =  new Databasehelper(getApplicationContext()).fetchsearch(nm,cls,cit,club,dist,phon);


            return ret;
        }
        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);
            p.dismiss();


            if(data.size()==0)
            {
                Toast.makeText(getApplicationContext(),"No record found ",Toast.LENGTH_SHORT).show();
            }
            else {

                Intent intent = new Intent(getApplicationContext(), Directory.class);
                intent.putExtra("list", (Serializable) data);
                startActivity(intent);
            }


          //  Intent intent = new Intent(getApplicationContext(),Directory.class);
         //   startActivity(intent);



        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}