package com.kaizen.stree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class Databasehelper extends SQLiteOpenHelper {



    public static List<Parking> data;
    Parking referalmodel = null;

    // Table Name
    public static final String TABLE_NAME = "NotificationDetails";

    // Table columns
    public static final String _ID = "_id";
    public static final String name_ = "name";
    public static final String dist_ = "distr";
    public static final String phon_ = "phone";
    public static final String clubn_ = "clubn";
    public static final String emaill_ = "emaill";
    public static final String city_ = "city";
    public static final String bus_are_ = "businessare";



    // Database Information
    static final String DB_NAME = "NotificationRecords.DB";

    // database version
    static final int DB_VERSION = 1;

    //Constructor
    public Databasehelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //Creating  QUERY


    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY, " + name_ + " TEXT , "+ dist_ + " TEXT, " + phon_ + " TEXT, " + clubn_ + " TEXT, " + emaill_ + " TEXT, "  + city_ + " TEXT, " + bus_are_ + " TEXT);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
//(firstName,club,mobile,emaill,Areab);
    public void insert(String firstName, String club, String mobile, String emaill, String Areab,String cty, String distt) {

        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();

        db.isOpen();
        try {


            values.put(name_, firstName);
            values.put(phon_, mobile);//for message use description
          //  values.put(dist_, fname);
            values.put(clubn_, club);
            values.put(emaill_, emaill);
            values.put(city_, cty);
            values.put(dist_, distt);
          //  values.put(city_, lname);
            values.put(bus_are_, Areab);




            // insert row in CheckInOutDetails table
            long insert = db.insert(TABLE_NAME, null, values);

            db.close();


            String ss = String.valueOf(insert);
            Log.d("rrr", firstName+" "+mobile+" "+club+" "+emaill+ " "+bus_are_);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }




//    public boolean updateData(String id,String email, String fname, String lname, String image) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        // contentValues.put(FLAG_FOR_NOTIFICATION_BACKGROUND, "1");
//
//        ContentValues values = new ContentValues();
//        try {
//
//
//            values.put(name_, id);
//            values.put(phon_, email);//for message use description
//            values.put(dist_, fname);
//            values.put(emaill_, lname);
//            values.put(bus_are_,image);
//
//            db.update(TABLE_NAME, values, "_id= ? ", new String[]{id});
//
//
//            return db.update(TABLE_NAME, values, id_ + "=" + id, null)>0;
//            //    return true;
//            //return db.delete(TABLE_NAME,"NOTIFICATION_EXPIRED_DATE = ?",new String[]{currentDate});
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return false;
//    }






    public List<Parking> fetch()
    {
        String selectQuery = "SELECT * " + " FROM " + TABLE_NAME ;

        Log.d("select query", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        c.getCount();

        Log.d("count ", String.valueOf(c.getCount()));
        // referalmodel = new modelclass();
        data = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                referalmodel = new Parking();

                referalmodel.setName(c.getString(c.getColumnIndex(name_)));
                referalmodel.setEmail(c.getString(c.getColumnIndex(emaill_)));
                referalmodel.setMobile(c.getString(c.getColumnIndex(phon_)));
                referalmodel.setClubname(c.getString(c.getColumnIndex(clubn_)));
                referalmodel.setClasification(c.getString(c.getColumnIndex(bus_are_)));

                data.add(referalmodel);
                Log.d("data",data.toString());

            } while (c.moveToNext());
            // Log.d("notificationList is: ", notificationList.toString());
        }
        return data;
    }
  //  (nm,cls,cit,club,dist,phon);

    public List<Parking> fetchsearch(String nam , String cls, String cit, String clb, String dist, String phone)
    {

//  ('%I%');
       //  String selectQuery =  "SELECT * FROM "+TABLE_NAME+ " WHERE ("+name_+ " like ('%"+nam+"%')"+" AND "+bus_are_+" like ('%"+cls+"%')"+" AND "+city_+" like ('%"+cit+"%')"+" AND "+clubn_+" like ('%"+clb+"%')"+" AND "+dist_+" like ('%"+dist+"%')"+" AND "+phon_+" like ('%"+phone+"%'))";
       // String selectQuery = "SELECT * " + " FROM " + TABLE_NAME ;

        String queryString="";
  //      queryString = name_ + " LIKE '%" + nam + "%' AND "+bus_are_ + " LIKE '%" + cls + "%' AND "+city_ + " LIKE '%" + cit + "%' AND "+clubn_ + " LIKE '%" + clb + "%' AND "+dist_ + " LIKE '%" + dist + "%' AND "+phon_ + " LIKE '%" + phone + "%'";

       // queryString = name_ + " LIKE '%" + nam + "%' AND "+bus_are_ + " LIKE '%" + cls + "%'";

        // queryString += name_ + " LIKE '%" + nam + "%' AND "+bus_are_ + " LIKE '%" + cls + "%' AND "+city_ + " LIKE '%" + cit + "%'";
       // queryString += bus_are_ + " LIKE '%" + cls + "%'";

        queryString += name_ + " LIKE '%" + nam + "%' AND ";
        queryString += bus_are_ + " LIKE '%" + cls + "%' AND ";
        queryString += city_ + " LIKE '%" + cit + "%' AND ";
        queryString += clubn_ + " LIKE '%" + clb + "%' AND ";
        queryString += dist_ + " LIKE '%" + dist + "%' AND ";
        queryString += phon_ + " LIKE '%" + phone + "%'";
      //  queryString += city_ + " LIKE '%" + cit + "%' AND ";
      //  queryString += clubn_ + " LIKE '%" + clb + "%' ";
      //  queryString += dist_ + " LIKE '%" + dist + "%' AND ";
       // queryString += phon_ + " LIKE '%" + phone + "%'";


      //  Log.d("select query", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c= db.query(true, TABLE_NAME,
                new String[] { name_,bus_are_,clubn_,city_,emaill_,phon_},
                queryString, null, null, null, null, null);

   //     Cursor c = db.rawQuery(selectQuery, null);
       // c.getCount();

        Log.d("count ", String.valueOf(c.getCount()));
        // referalmodel = new modelclass();
        data = new ArrayList<>();
        try {

            if (c.moveToFirst()) {
                do {
                    referalmodel = new Parking();

                    referalmodel.setName(c.getString(c.getColumnIndex(name_)));
                    referalmodel.setEmail(c.getString(c.getColumnIndex(emaill_)));
                    referalmodel.setMobile(c.getString(c.getColumnIndex(phon_)));
                    referalmodel.setClubname(c.getString(c.getColumnIndex(clubn_)));
                    referalmodel.setClasification(c.getString(c.getColumnIndex(bus_are_)));

                    data.add(referalmodel);
                    Log.d("data", data.toString());

                } while (c.moveToNext());

                // Log.d("notificationList is: ", notificationList.toString());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public int getcount()
{

   // String selectQueryr =  "SELECT * FROM"+TABLE_NAME+ "WHERE ("+name_+ "="+ nam+"AND"+clubn_+"="+club+")";

    String selectQuery = "SELECT * " + " FROM " + TABLE_NAME ;

    Log.d("select query", selectQuery);

    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);
    int i= c.getCount();

    return  i;
}
public void deletee()
{
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("delete from "+ TABLE_NAME);
}

}

