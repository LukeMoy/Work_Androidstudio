package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
//main activity of the application
public class MainActivity extends AppCompatActivity {
    public my_database db;
    public static final String MyPreferences = "MyPrefs";
    public static final String ROW ="row";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
     db = new my_database(getBaseContext());
        //opens page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiates button and textview
        Button login = (Button)findViewById(R.id.btnlogin);
        Button createAccount = (Button)findViewById(R.id.btnCreateAcc);
        //login button onclick listener
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextView user = (TextView)findViewById(R.id.txtemail);
                TextView pass = (TextView)findViewById(R.id.txtpass);
                //If the username and password equal nothing then do nothing
                if(user.getText().toString().equals("")|| pass.getText().toString().equals(""))
                {
                }
                else {
                    //open database
                    db.open();
                    try{
                        int lineNum = 1;
                        Cursor c = db.getAccount(lineNum);
                        String s = c.getString(1);
                        //While s is not equal to the user or s is null
                        while(!s.equals(user.getText().toString()) || s.equals(null)){
                            //Adds 1 too lineNum
                            lineNum++;
                            //runs getAccount from database class
                            c = db.getAccount(lineNum);
                            s = c.getString(1);
                            Toast.makeText(getBaseContext(), "line",
                                    Toast.LENGTH_LONG).show();
                        }
                        //gets s value
                        s = c.getString(2);
                        //if the value is not equal to the database value - displays toast
                        if(!s.equals(pass.getText().toString())){
                            Toast.makeText(getBaseContext(), "Your Password is Wrong!!!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            //if the password and username are correct - opens the home class
                            startActivity(new Intent(MainActivity.this, home.class));
                            Toast.makeText(getBaseContext(), "Password is Right", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.commit();

                        }
                        Toast.makeText(getBaseContext(), "It is DONE",
                                Toast.LENGTH_LONG).show();

                    }
                    catch(Exception e){
                        //if username does not exist within database - displays toast
                        Toast.makeText(getBaseContext(), "User does not exist",
                                Toast.LENGTH_LONG).show();

                    }
                    //closes database
                    db.close();
                }
            }

        });
        //Button to bring user to the create account page
        createAccount.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, create_account.class));
            }

        });

    }
}
