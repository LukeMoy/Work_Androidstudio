package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class home extends AppCompatActivity {
    //Home class, here the user can navigate through three sections of the application: Readings, Details and Contacts
public void home()
{}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Opens page on create
        setContentView(R.layout.content_home);
        //Instantiates buttons
        Button readingsPage = (Button)findViewById(R.id.btnReadingsPage);
        Button detailsPage = (Button)findViewById(R.id.btnDetailsPage);
        Button contactsPage = (Button)findViewById(R.id.btnContactsPage);
        Button profileImage = (Button)findViewById(R.id.btnImage);
        //on click listener
        //opens MapsActivity page
            readingsPage.setOnClickListener(new View.OnClickListener()
            {
                 public void onClick(View v)
                    {
                    startActivity(new Intent(home.this, MapsActivity.class));
                    }
                }
            );
        //on click listener
        //opens User details page page
            detailsPage.setOnClickListener(new View.OnClickListener()
            {
                 public void onClick(View v)
                 {
                    startActivity(new Intent(home.this, user_details.class));
                 }
                }
            );
        //on click listener
        //opens contact list page
            contactsPage.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    startActivity(new Intent(home.this, contact_list.class));
                }
            }
            );
        profileImage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(home.this, profile_image.class));
                }
        }
        );

        }
    }



