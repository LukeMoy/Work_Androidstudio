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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//USer details page for collecting user details
public class
user_details extends AppCompatActivity {
    my_database db;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String ROW = "row";
    SharedPreferences sharedpreferences;
    public int row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Database
        db = new my_database(getBaseContext());
        //Opens page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        //Instantiates button
        Button create = (Button)findViewById(R.id.btnSave);
        //instantiates shared preferences
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //Sets row to get int
        row = sharedpreferences.getInt(ROW, 0);
        //Opens database
        db.open();
        //sets cursor at the row
        Cursor c = db.getAccount(row);
        db.close();
        //Instantiates textviews
        TextView name = (TextView)findViewById(R.id.eUser);
        TextView address = (TextView)findViewById(R.id.eAddress);
        TextView age = (TextView)findViewById(R.id.eAge);
        TextView occupation = (TextView)findViewById(R.id.eOccupation);
        TextView gpnumber = (TextView)findViewById(R.id.eGPNumber);
        ImageView verify = (ImageView) findViewById(R.id.ivVerify);
        TextView verified = (TextView) findViewById(R.id.txtVerified);
        verify.setVisibility(View.INVISIBLE);
        //Sets check to c
        String check = c.getString(3);
        //if check is not equal to null
        if(check != null)
        {
            //Sets values of variables
            name.setText(c.getString(3));
            address.setText(c.getString(4));
            age.setText(c.getString(5));
            occupation.setText(c.getString(6));
            gpnumber.setText(c.getString(7));
        }

        if(!gpnumber.getText().toString().equals("1234"))
        {Toast.makeText(getBaseContext(),"not verified", Toast.LENGTH_LONG).show();
        }
        else
        {
            db.open();
            try{
                int LineNum = 1;
                Cursor d = db.getAccount(LineNum);
                String v = d.getString(7);
                Toast.makeText(getBaseContext(),"This" + v, Toast.LENGTH_LONG).show();
                if (v.equals("1234")){
                }
                    verify.setVisibility(View.VISIBLE);
                    verified.setText("                This Account Has Been Verifed!");

            }
            catch(Exception e){
                Toast.makeText(getBaseContext(),e.toString(), Toast.LENGTH_LONG).show();
        }

            db.close();
        }



        //button for Create on click listener
        create.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextView name = (TextView)findViewById(R.id.eUser);
                TextView address = (TextView)findViewById(R.id.eAddress);
                TextView age = (TextView)findViewById(R.id.eAge);
                TextView occupation = (TextView)findViewById(R.id.eOccupation);
                TextView gpnumber = (TextView)findViewById(R.id.eGPNumber);
                //sets values to that of the text views
                String sName, sAddress, sAge,  sOccupation, sCode;
                sName = name.getText().toString();
                sAddress = address.getText().toString();
                sAge = age.getText().toString();
                sOccupation = occupation.getText().toString();
                sCode = gpnumber.getText().toString();
                //if one of teh fields is empty - displays toast
                if(name.getText().toString().equals("")|| age.getText().toString().equals("")||address.getText().toString().equals("")||occupation.getText().toString().equals("")||gpnumber.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(), "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    //opens database
                    db.open();
                    //inserts the entered details into the database and stores them
                    db.insertDetails(sName, sAddress, sAge, sOccupation, sCode, row);
                    //displays toast
                    Toast.makeText(getBaseContext(), "Details Saved", Toast.LENGTH_LONG).show();
                    //closes database
                    db.close();
                    //opens home activity when this is completed
                    startActivity(new Intent(user_details.this, home.class));
                }
            }
        }
        );
    }
}