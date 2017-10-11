package com.extracts.rakeshdeshpande.ex_tract;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rakesh.deshpande on 21-09-2017.
 */


class sqlitehelper extends SQLiteOpenHelper {

    public static final String TAG = "sqlitehelper";
    public static final String DB_Name= "entries.db";
    public static final String Table_Name="extract";
    public static final  String ID="id";
    public static final String Items="item";
    public static   String Amounts="amount";
    public sqlitehelper dbhelper;
    private SQLiteDatabase database;
    Context ctxs;
    public sqlitehelper(Context context) {
        super(context, DB_Name, null, 1);
       // SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String table="CREATE TABLE "+Table_Name+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                Items+" TEXT,"+
                Amounts+" INTEGER);";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Steps to upgrade the database for the new version ...
        db.execSQL("DROP IF TABLE EXISTS "+Table_Name);
        onCreate(db);
    }


    public  boolean insertdata(String name, String amount  ){
       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Items,name);
        values.put(Amounts,amount);
        Log.v(TAG,"insertdata: Adding "+name+"to"+Table_Name );

      long result=  db.insert(Table_Name,null,values);
        if(result==-1){

           return false;
        }else
        {
            return true;
        }

    }


    public  Cursor getlistcontents(){

        SQLiteDatabase sdb = this.getWritableDatabase();
        Cursor data= sdb.rawQuery("SELECT * FROM "+Table_Name,null);
        return data;
    }

public  int addAllvalues(int total){


    SQLiteDatabase sdb = this.getReadableDatabase();
    Cursor c = sdb.rawQuery("SELECT SUM(amount) FROM "+Table_Name,null);
    c.moveToFirst();
    //    total = c.getInt(0);
    total += Integer.parseInt(c.getString(0));
    return total;

}

public Integer cls(){


    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete("extract",null,null);

}
}



       /* public  Cursor fetch(){

            database = this.getReadableDatabase();
            String[] cols = new String[]{ sqlitehelper.Items,sqlitehelper.Amounts };
            Cursor cursor =  database.query(sqlitehelper.Table_Name,cols,null,null,null,null,null);

            if(cursor!=null){
                cursor.moveToFirst();
            }
                return cursor;

        }*/



