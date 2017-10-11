package com.extracts.rakeshdeshpande.ex_tract;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.extracts.rakeshdeshpande.ex_tract.R.layout.mainpage;


/**
 * Created by rakesh.deshpande on 20-09-2017.
 */

public class mainpage extends LoginActivity {

    EditText amountspent, spentamount;
    Button datee, save, clean, prevex;
    private int cyear, cday, cmonth;
    TextView tv;
    Date date,dt;
    Boolean isinserted;
    float amounts;
    Context context;
    int dayOfMonth,monthOfYear,year;
    String store;
    ListView listview;
    SQLiteDatabase dbs;
    sqlitehelper db;
    private SimpleCursorAdapter dataAdapter;
    private ListView list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        db = new sqlitehelper(this);

        amountspent = (EditText) findViewById(R.id.as);
         spentamount = (EditText) findViewById(R.id.sa);
        save = (Button) findViewById(R.id.save);


        //setting up the date and displaying on the edittext
      /*   datee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // calender implementation
               final Calendar c = Calendar.getInstance();
                cyear = c.get(Calendar.YEAR);
                cmonth = c.get(Calendar.MONTH);
                cday = c.get(Calendar.DAY_OF_MONTH);

                pickdate = new DatePickerDialog(mainpage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                datetext.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                j        }, cyear, cmonth, cday);
                pickdate.show();
            }
        });*/

        Button prev = (Button) findViewById(R.id.pse);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mainpage.this,finalpage.class);
                startActivity(i);




            }
        });


        Button clean = (Button) findViewById(R.id.cancel);

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               amountspent.setText("");
                spentamount.setText("");
            }
        });


    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String args1= amountspent.getText().toString();
            String args2= spentamount.getText().toString();

            if ((amountspent.length()!=0)&&(spentamount.length()!=0)){

                String regexStr = "^[1-9]\\d*(\\.\\d+)?$";
                if(spentamount.getText().toString().trim().matches(regexStr)){
                    AddData(args1,args2);
                    amountspent.setText("");
                    spentamount.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Amount should be only in DIGITS!",Toast.LENGTH_LONG).show();
                    spentamount.setText("");
                }


            }else {

                Toast.makeText(getApplicationContext(),"You must enter the missed field!",Toast.LENGTH_LONG).show();
            }

        }
    });


    }




    public  void AddData(String args1, String args2){

        sqlitehelper sq= new sqlitehelper(this);
        boolean insertdata1 = sq.insertdata(args1, args2);

        if (insertdata1){
            Toast.makeText(getApplicationContext(),"Awesome, You just added an expenditure",Toast.LENGTH_LONG).show();
        }
        else {

            Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_LONG).show();
        }


    }
}






