package com.kaizen.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Viewprofile extends AppCompatActivity {

    ImageView back,ivNewCallButton,ivNewMessage,ivWhatsApp,ivNewMail;
    TextView memn , memclu, clas , emaill , mobil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        back = findViewById(R.id.prof);
        getSupportActionBar().hide();


        ivNewCallButton = findViewById(R.id.ivNewCallButton);
        ivNewMessage = findViewById(R.id.ivNewMessage);
        ivWhatsApp = findViewById(R.id.ivWhatsApp);
        ivNewMail = findViewById(R.id.ivNewMail);



        memn = findViewById(R.id.tv_member_name);
        memclu = findViewById(R.id.tvClubName);
        mobil = findViewById(R.id.mobil);
        emaill = findViewById(R.id.emal);
        clas  = findViewById(R.id.clas);





try {

    String nam = getIntent().getExtras().getString("name");
    String clbn = getIntent().getExtras().getString("clubn");
    String mob = getIntent().getExtras().getString("mobile");
    String eml = getIntent().getExtras().getString("email");
    String cls = getIntent().getExtras().getString("classi");


    memn.setText(nam);
    memclu.setText(clbn);
    mobil.setText(mob);
    emaill.setText(eml);
    clas.setText(cls);
}
catch (Exception e)
{
    e.printStackTrace();
}




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

ivNewCallButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent callIntent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel: " + mobil.getText().toString().trim()) );
        startActivity( callIntent );

    }
});

ivNewMessage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String url = "https://api.whatsapp.com/send?phone=" + mobil.getText().toString().trim();;//"https://wa.me/"+profileData.getMobile()+"/?text=hii";

        Intent i = new Intent( Intent.ACTION_VIEW );
        i.setData( Uri.parse( url ) );
        startActivity( i );

    }
});

ivWhatsApp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        try {

            Intent msgIntent = new Intent(Intent.ACTION_VIEW);
//                        msgIntent.setType("vnd.android-dir/mms-sms");
//                        msgIntent.putExtra("address", myMsgList.get(0).getNumber());
            msgIntent.setData(Uri.parse("smsto: " + mobil.getText().toString().trim()));
            startActivity(msgIntent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
});
ivNewMail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final Intent emailIntent = new Intent( Intent.ACTION_SENDTO );
        emailIntent.setType( "plain/text" );
        emailIntent.setData( Uri.parse( "mailto:" ) );
        emailIntent.putExtra( Intent.EXTRA_EMAIL, emaill.getText().toString().trim() );
        emailIntent.putExtra( Intent.EXTRA_SUBJECT, "" );
        emailIntent.putExtra( Intent.EXTRA_TEXT, "" );
        startActivity( emailIntent );
    }
});







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}