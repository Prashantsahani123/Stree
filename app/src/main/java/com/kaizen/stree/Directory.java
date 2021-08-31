package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kaizen.stree.Databasehelper.TABLE_NAME;

public class Directory extends AppCompatActivity {
    public static List<Parking> data;

    RecyclerView recyclerView ;
    ProfileAdapter adaptor;
    String moduleflag = "";
    String mobile = "";
    String email = "";
    ImageView back;
//dfd
    ImageView dict_back;
    ArrayList<Parking> parkingArrayList = new ArrayList<>();

    private ProfileAdapter parkingAdapter;

    private  int type = 1;
     ImageView refre;

    Databasehelper databasehelper= null;
    ProgressDialog progressDialog ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        getSupportActionBar().hide();

        refre = findViewById(R.id.refres);


         back = findViewById(R.id.bckk);
         databasehelper = new Databasehelper(getApplicationContext());




        recyclerView = findViewById(R.id.recycler);
       // dict_back = findViewById(R.id.dict_back);

      //  databasehelper.deletee();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(Directory.this);




//prashant

     if(databasehelper.getcount() > 0)

         {


             try {

                 data = ((List<Parking>) getIntent().getExtras().getSerializable("list"));
                 recyclerView.clearFocus();
                 adaptor = new ProfileAdapter(Directory.this, data);
                 RecyclerView.LayoutManager reLayoutManager2 = new LinearLayoutManager(getApplicationContext());
                 recyclerView.setLayoutManager(reLayoutManager2);
                 recyclerView.setItemAnimator(new DefaultItemAnimator());
                 recyclerView.setAdapter(adaptor);

                 if(data.size() == 0)
                 {

                     ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                     NetworkInfo netInfo = conMgr.getActiveNetworkInfo();


                         new AsyncTaskExample().execute();

                 }

             }
             catch (Exception e)
             {


                     new AsyncTaskExample().execute();

                 e.printStackTrace();
             }




        }
        else {
            getdriverDetail();
        }



        refre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if (netInfo == null){
                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

                }
                else {
                    databasehelper.deletee();
                    getdriverDetail();
                }
            }
        });




        //new ReadFile().execute("");




//        dict_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
              //  Intent intent= new Intent(getApplicationContext(), MainActivity.class);
              //  startActivity(intent);
            }
        });

    }

    private void getdriverDetail() {
       // ProgressDialog progressDialog = new ProgressDialog(Directory.this);

//        if ((progressDialog != null) && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Loading Directory...");
        progressDialog.show();


        try {

            ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null){
              progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
//
            }
            else {

                JSONObject requestData = new JSONObject();

                // requestData.put("profileID", "21");


                SharedPreferences sp = getSharedPreferences( "trucklogin", MODE_PRIVATE );

                String  p_id = sp.getString("p_id","");

              //  Log.d("Response", "PARAMETERS " + Constant.Driverdetail + " :- " + requestData.toString());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://spreadsheets.google.com/feeds/list/1uXnn87uEy9z-H_8q2XSxQG0ffNfYa6fKtJES_lhn6yA/od6/public/values?alt=json", requestData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
/////

                        Parking parking = null;
                        data = new ArrayList<>();
                        try {
                            JSONObject feedObj = response.getJSONObject("feed");
                            JSONArray entryArray = feedObj.getJSONArray("entry");
                            for(int i=0; i<entryArray.length(); i++){
                                parking = new Parking();

                                JSONObject entryObj = entryArray.getJSONObject(i);//Name of the Applicant//District Number
                                String firstName = entryObj.getJSONObject("gsx$nameoftheapplicant").getString("$t");

                               // String firstName = entryObj.getJSONObject("gsx$//Name of the Applicant").getString("$t");
                                String district = entryObj.getJSONObject("gsx$districtnumber").getString("$t");
                                String club = entryObj.getJSONObject("gsx$clubname").getString("$t");
                                String city = entryObj.getJSONObject("gsx$city").getString("$t");
                                String mobile = entryObj.getJSONObject("gsx$phonenumber").getString("$t");
                                String emaill = entryObj.getJSONObject("gsx$businessventureofficialemailid").getString("$t");
                                String Areab = entryObj.getJSONObject("gsx$areaofbusiness").getString("$t");
//                                String firstName = entryObj.getJSONObject("gsx$//Name of the Applicant").getString("$t");
//                                String district = entryObj.getJSONObject("gsx$District").getString("$t");
//                                String club = entryObj.getJSONObject("gsx$Club Name").getString("$t");
//                                String city = entryObj.getJSONObject("gsx$City").getString("$t");
//                                String mobile = entryObj.getJSONObject("gsx$Phone Number").getString("$t");
//                                String emaill = entryObj.getJSONObject("gsx$Business /Venture Official Email ID").getString("$t");
//                                String Areab = entryObj.getJSONObject("gsx$areaofbusiness").getString("$t");

                                if(!firstName.equalsIgnoreCase(""))
                                {

                                    parking.setName(firstName);
                                    parking.setClubname(club);
                                    parking.setMobile(mobile);
                                    parking.setEmail(emaill);
                                    parking.setClasification(Areab);
                                    parking.setCityy(city);
                                    parking.setDistr(district);

                                    data.add(parking);
                                    databasehelper.insert(firstName,club,mobile,emaill,Areab,city,district);

                                }


                               // userModalArrayList.add(new UserModal(firstName, lastName, email, avatar));

                                // passing array list to our adapter class.
                               // userRVAdapter = new UserRVAdapter(userModalArrayList, MainActivity.this);

                                // setting layout manager to our recycler view.
                               // userRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                                // setting adapter to our recycler view.
                              //  userRV.setAdapter(userRVAdapter);
                            }

                            if ((progressDialog != null) && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            recyclerView.clearFocus();
                            adaptor = new ProfileAdapter(Directory.this, data);
                            RecyclerView.LayoutManager reLayoutManager2 = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(reLayoutManager2);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adaptor);

                        } catch (JSONException e) {
                            if ((progressDialog != null) && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                        ////////////













//                        if ((progressDialog != null) && progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }

                        JsonObject result;
                       // showdriverdetails(response);
                        //Utils.log(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        if ((progressDialog != null) && progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                          //  getdriverDetail();
//                        }
                        getdriverDetail();
                       // Utils.log("Volley Error" + error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        String credentials = "prashant.sahani@kaizeninfotech.com:Prashantsahani@456";
                        String auth = "Basic "
                                + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", auth);
                        return headers;
                    }
                };



                request.setRetryPolicy(new DefaultRetryPolicy(120000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                AppController.getInstance().addToRequestQueue(getApplicationContext(), request);
            }
//            if ((progressDialog != null) && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void showdriverdetails(JSONObject response) {
//
//        try {
//
//            JSONObject ShowCheckDetails = response;
//            String status = ShowCheckDetails.getString("status");
//
//            if (status.equalsIgnoreCase("0")) {
//
//                notrip.setVisibility(View.GONE);
//                visibility.setVisibility(View.VISIBLE);
//
//
//                if ((progressDialog != null) && progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//
//                String msg = ShowCheckDetails.getString("message");
//                Log.d("Kaizen", "*************** " + status);
//                Log.d("Kaizen", "*************** " + msg);
//
//
//                JSONObject Result = ShowCheckDetails.getJSONObject("ResultSet");
//
//                JSONArray CheckInOut = Result.getJSONArray("Table");
//
//                String date = "";
//                String d_status = "";
//                String P_id = "";
//                String flag = "";
////                String filepath = "";
////                int D_id1;
//
//
//                arrayList = new ArrayList<>();
//                data = new ArrayList<>();
//                Dashbd_model dashbd_model = null;
//
//                for (int i = 0; i < CheckInOut.length(); i++) {
//                    dashbd_model = new Dashbd_model();
//                    JSONObject c = CheckInOut.getJSONObject(i);
//                    //  {"Pk_trip_id":4,"BookginDate":"19-05-2021","status":"Completed","statusflag":2}]}}
//                    date = String.valueOf(c.getString("BookginDate"));
//                    d_status = String.valueOf(c.getString("status"));
//                    P_id = String.valueOf(c.getString("Pk_trip_id"));
//                    flag = String.valueOf(c.getString("statusflag"));
//
//                    // String[] d = postedDate.split("T");
//                    //String d1 = d[0];
//
////
////                    SimpleDateFormat dateFormattime = new SimpleDateFormat( "yyyy-MM-dd" );
////                    Date objDatetime = dateFormattime.parse(d1);
////                    SimpleDateFormat dateFormattimegggg = new SimpleDateFormat( "dd MMM yyyy" );
////                    postedDate = dateFormattimegggg.format(objDatetime);
////
//
//
//
//
//
//
//
//
//
//                    dashbd_model.setBookginDate(date);
//                    dashbd_model.setStatus(d_status);
//                    dashbd_model.setPk_trip_id(P_id);
//                    dashbd_model.setStatusflag(flag);
//
//
//                    //  knowldgbasemodel.setFilepath(filepath);
//
//
//                    data.add(dashbd_model);
//
////                    m_email.put(dpt_name, dpt_m_email);
////                    D_id.put(dpt_name,D_id1);
//
//                }
////                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item
////                        , arrayList);
//
//                // data.clear();
//                if(data.size()<= 0)
//                {
//                    ref_nonet.setVisibility(View.VISIBLE);
//                    ref_nonet.setText("No records");
//                    visibility.setVisibility(View.GONE);
//
//                }
//
//
//                adaptor = new dashbd_adpter(Dashboard.this, data);
//                RecyclerView.LayoutManager reLayoutManager2 = new LinearLayoutManager(getApplicationContext());
//                Recylerv.setLayoutManager(reLayoutManager2);
//                Recylerv.setItemAnimator(new DefaultItemAnimator());
//                Recylerv.setAdapter(adaptor);
//
//
//            } else {
//                notrip.setVisibility(View.VISIBLE);
//                visibility.setVisibility(View.GONE);
//                if ((progressDialog != null) && progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//
//                    notrip.setVisibility(View.VISIBLE);
//                    visibility.setVisibility(View.GONE);
//
//
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        }
//    }
private class AsyncTaskExample extends AsyncTask<String, String, String> {
        String ret = "";
    ProgressDialog p;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        p = new ProgressDialog(Directory.this);
        p.setMessage("Please wait...");
        p.setIndeterminate(false);
        p.setCancelable(false);
        p.show();
    }
    @Override
    protected String doInBackground(String... strings) {

        adaptor = new ProfileAdapter(Directory.this, databasehelper.fetch());




        return ret;
    }
    @Override
    protected void onPostExecute(String bitmap) {
        super.onPostExecute(bitmap);
        p.dismiss();
       // adaptor = new ProfileAdapter(Directory.this, databasehelper.fetch());


        recyclerView.clearFocus();
        RecyclerView.LayoutManager reLayoutManager2 = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(reLayoutManager2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);

    }
}
}


