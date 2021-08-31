package com.kaizen.stree;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfileAdapter  extends RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>{


    private Context context;
    private List<Parking> scheduledEvents;
    private ArrayList<Parking> arraylist;
    String currentloc;
    private  double dis;
    String dis1;
    private int type;

    public ProfileAdapter(Context context, List<Parking> scheduledEvents){
        this.context = context;
        this.scheduledEvents = scheduledEvents;
        this.arraylist = new ArrayList<Parking>();
        this.arraylist.addAll(scheduledEvents);
        this.currentloc = currentloc;
        this.type = type;
    }

  /*  public ParkingAdapter(Context context, List<Parking> scheduledEvents){
        this.context = context;
        this.scheduledEvents = scheduledEvents;
      //  this.currentloc = currentloc;
    }*/



    @NonNull
    @Override
    public ProfileAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProfileAdapter.CustomViewHolder holder = null;

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilelist, parent, false);

        holder = new ProfileAdapter.CustomViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.CustomViewHolder eventHolder, int position) {

        final Parking parking = scheduledEvents.get(position);

        eventHolder.tv_name.setText(parking.getName());
        eventHolder.tv_mb.setText(parking.getClubname());
//
//        eventHolder.tv_address.setText(parking.getAddrs());
//
//        eventHolder.tv_mb.setText(parking.getMb());
//        eventHolder.tv_cnt.setText(parking.getCtp());
//        eventHolder.tv_pin.setText(parking.getPin());
//        eventHolder.tv_sta.setText(parking.getSta());
//        eventHolder.tv_distr.setText(parking.getDist());


        //  eventHolder.tv_distance.setText(parking.getDistance());


      //  DecimalFormat df = new DecimalFormat();
      //  df.setMaximumFractionDigits(1);
        //  eventHolder.tv_distance.setText(df.format(dis));

        eventHolder.name_add_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Viewprofile.class);
                intent.putExtra("name",parking.getName());
                intent.putExtra("clubn",parking.getClubname());
                intent.putExtra("mobile",parking.getMobile());
                intent.putExtra("email",parking.getEmail());
                intent.putExtra("classi",parking.getClasification());



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return scheduledEvents.size();
    }

    public static  class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_address,tv_distance;
        TextView tv_mb, tv_cnt, tv_pin, tv_sta, tv_distr;

        LinearLayout direction_layout,name_add_layout;
        ImageView direction_img;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tv_name =  itemView.findViewById(R.id.txt_store_title);
         //   tv_address =  itemView.findViewById(R.id.txt_store_desc);
           // tv_distance = itemView.findViewById(R.id.txt_dist);
         //   direction_layout = (LinearLayout) itemView.findViewById(R.id.ll_storeLocation);
            name_add_layout = (LinearLayout) itemView.findViewById(R.id.name_add_layout);
           // direction_img = (ImageView) itemView.findViewById(R.id.img_dire);


            tv_mb =   itemView.findViewById(R.id.txt_mb);
           // tv_pin =   itemView.findViewById(R.id.txt_pin);
            //tv_cnt =  itemView.findViewById(R.id.txt_cnt);
        //    tv_sta =   itemView.findViewById(R.id.txt_sta);
        //    tv_distr =  itemView.findViewById(R.id.txt_distt);






        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        scheduledEvents.clear();
        if (charText.length() == 0) {
            scheduledEvents.addAll(arraylist);
        }
        else
        {
            for (Parking wp : arraylist)
            {
                //  try {


//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
            }
        }
        notifyDataSetChanged();
    }

}

