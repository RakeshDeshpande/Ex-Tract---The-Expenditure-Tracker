package com.extracts.rakeshdeshpande.ex_tract;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

   EditText user;
     EditText password;
     Button signing;
    SharedPreferences sharedpref;


    // these are the references for the the sign in page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signing = (Button) findViewById(R.id.signin);
         SharedPreferences sharedpref = getSharedPreferences("loginn",Context.MODE_PRIVATE);
         final String getuser = sharedpref.getString("usernam","");
         final String getpassword = sharedpref.getString("passwor","");
        signing.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        // Checks password if the user enter empty or one space
        if (user.getText().toString().matches("") || password.getText().toString().matches("") || user.getText().toString().matches(" ") || password.getText().toString().matches(" ") )
        {
            Toast.makeText(getApplicationContext(), "Enter the registered crendetials", Toast.LENGTH_SHORT).show();
            user.setText("");
            password.setText("");
        }

        else if(user.getText().toString().matches(getuser.toString()) && password.getText().toString().matches(getpassword.toString()))
        {

            Toast.makeText(getApplicationContext(), "Sign In Successfull", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent main = new Intent(getApplicationContext(),mainpage.class);
            startActivity(main);
            user.setText("");
            password.setText("");
        }else {

            Toast.makeText(getApplicationContext(), "Please register and try again!", Toast.LENGTH_SHORT).show();
            user.setText("");
            password.setText("");
        }

        }
        });
        //the above references should not hold the empty string

        //On clicking signup button , it takes you to signup page.
        final Button signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signp= new Intent(getApplicationContext(),signup.class);
                startActivity(signp);

            }
        });


            }

}

