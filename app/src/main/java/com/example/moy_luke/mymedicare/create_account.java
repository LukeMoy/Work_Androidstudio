package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//Create account class were users can create accounts
public class create_account extends AppCompatActivity {
    //Declaring database
    my_database db;
    //Variables
    public static final String MyPreferences = "MyPrefs";
    public static final String ROW ="row";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        db = new my_database(getBaseContext());
        //Opens page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Instantiates
        sharedpreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        Button create = (Button) findViewById(R.id.createBtn);
        //Creates onclicklister for the create button
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Instanitation
                TextView user = (TextView) findViewById(R.id.userTxt);
                TextView password = (TextView) findViewById(R.id.passwordTxt);
                String uName = user.getText().toString();
                String pWord = password.getText().toString();
                //Adds new Users to the database, if the username exists
                // displays a toast
                if (!uName.equals("") & !pWord.equals("")) {
                    //Opens database
                    db.open();
                    int lineNum = 1;
                    try {

                        Cursor c = db.getAccount(lineNum);
                        if (!c.getString(1).equals(null)) {
                            String s = c.getString(1);
                            //While s is not equal to the user or s is null
                            while (!s.equals(user.getText().toString()) || s.equals(null)) {
                                lineNum++;
                                c = db.getAccount(lineNum);
                                s = c.getString(1);
                            }
                        }

                        //Displays a toast when statement is met
                        Toast.makeText(getBaseContext(), "User Already Has An Account",
                                Toast.LENGTH_LONG).show();
                    } catch (CursorIndexOutOfBoundsException w) {
                        //Inserts new username and password in dataabse
                        db.insertContact(uName, pWord);
                        db.close();
                        //When user is inserted displays a toast
                        Toast.makeText(getBaseContext(), "User Has Been inserted",
                                Toast.LENGTH_LONG).show();
                        //Stores a variable in sharedpreferences
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(ROW, lineNum);
                        editor.commit();
                        //Opens user details
                        startActivity(new Intent(create_account.this, user_details.class));
                        //Catch for if an error occurs
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                    //closes database
                    db.close();
                } else {
                    //IF no user is entered, displays a toast
                    Toast.makeText(getBaseContext(), "Please inset Username and/or Password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }}
