package com.extracts.rakeshdeshpande.ex_tract;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rakesh.deshpande on 21-09-2017.
 */

public class finalpage extends AppCompatActivity {

    SQLiteDatabase database;
    sqlitehelper db;
public  int sum,sum1;

     String[] from = new String[] {
            db.Items,db.Amounts};

   int[] to =new int[] {R.id.istitem,R.id.amount};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finall);
        sqlitehelper sq= new sqlitehelper(this);
       /* sum1 = db.addAllvalues(sum);
        TextView tv= (TextView)findViewById(R.id.totalamot);
        tv.setText(sum1);*/

        Button clear = (Button) findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deleterows = db.cls();
                if(deleterows!=0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Your list is cleared!", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(finalpage.this,mainpage.class);
                    startActivity(i);

                }else{

                    Toast.makeText(getApplicationContext(), "No Expenditure to erase", Toast.LENGTH_SHORT).show();
                }
                //Intent i=new Intent(finalpage.this,mainpage.class);
                //startActivity(i);
            }
        });


        final ListView listView = (ListView) findViewById(R.id.tlist);
        db = new sqlitehelper(this);


        ArrayList<String> lit = new ArrayList<>();
        Cursor data = db.getlistcontents();


        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "The Expenditure list is Empty", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                lit.add(data.getString(1));
                lit.add((data.getString(2)));
                ListAdapter listadap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lit);

                listView.setAdapter(listadap);
            }

        }



        //Generate ListView from SQLite Database


    }




}

    //  TextView totalmessage = (TextView)findViewById(R.id.total);
       // todisplayamount=(TextView)findViewById(R.id.amountdisplayed);
     /* ListView list=(ListView)findViewById(R.id.itemsview);
        db = new sqlitehelper(this);
        ArrayList<String> lista = new ArrayList<>();
        Cursor dataa= db.getdata();

        if(dataa.getCount()==0){
            Toast.makeText(getApplicationContext(),"No data available, store the expenditure!",Toast.LENGTH_SHORT).show();
        }else{

            while(dataa.moveToNext()){
                lista.add(dataa.getString(dataa.getColumnIndex("item")));
                lista.add(dataa.getString(dataa.getColumnIndex("amount")));
                ListAdapter listad= new ArrayAdapter<>(finalpage.this,android.R.layout.simple_list_item_1,lista);
                list.setAdapter(listad);
            }
        }*/
