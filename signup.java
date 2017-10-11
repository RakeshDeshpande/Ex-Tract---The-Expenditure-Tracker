package com.extracts.rakeshdeshpande.ex_tract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rakesh.deshpande on 19-09-2017.
 */

public class signup extends LoginActivity {

    EditText repassword;
    EditText password;
    EditText username;
   SharedPreferences sharedpref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // Created references for Edit texts in Signup Activity....
        final   EditText username = (EditText) findViewById(R.id.usersignup);
        final EditText password = (EditText) findViewById(R.id.passwordsignup);
         final EditText repassword = (EditText) findViewById(R.id.againpasswordsignup);
        //Shared preferences to store the above data
        SharedPreferences sharedpref = getSharedPreferences("loginn",Context.MODE_PRIVATE);

        // Sign  up Button implementations.
        Button signup = (Button) findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((username.getText().toString().matches("")|| password.getText().toString().matches("") || repassword.getText().toString().matches(""))||
                (username.getText().toString().matches(" ")||  password.getText().toString().matches(" ") ||  repassword.getText().toString().matches(" ")))
                {
                    Toast.makeText(getApplicationContext(),"Checking...Please wait...",Toast.LENGTH_SHORT).show();
                    if(username.getText().toString().matches("") || username.getText().toString().matches(" ")) {
                        Toast.makeText(getApplicationContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                    else if(password.getText().toString().matches("")||password.getText().toString().matches(" "))
                        {
                            Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                        }
                    else if(repassword.getText().toString().matches("")||repassword.getText().toString().matches(" "))
                    {
                        Toast.makeText(getApplicationContext(),"Re-entered Password cannot be empty",Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    //saves the data locally using shared preferences

                    if(password.getText().toString().matches(repassword.getText().toString()))
                    {
                        SharedPreferences sharedpref = getSharedPreferences("loginn",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpref.edit();

                       //SharedPreferences.Editor use = editor.putString("username",username.getText().toString());
                       // SharedPreferences.Editor pass=editor.putString("password",password.getText().toString());
                    // SharedPreferences.Editor repass=editor.putString("password",repassword.getText().toString());

                        //store the data

                        editor.putString("usernam",username.getText().toString());
                        editor.putString("passwor",password.getText().toString());
                        editor.putString("repasswor",repassword.getText().toString());

                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sign Up Successfull! Your crendentials is data is stored",Toast.LENGTH_SHORT).show();

                    }

                    else{

                        Toast.makeText(getApplicationContext(),"Passwords do not Match, Try again!",Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                        repassword.setText("");
                    }

                }
                //re-sets the screen


            }


        });










    }
}
