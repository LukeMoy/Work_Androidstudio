package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


public class profile_image extends AppCompatActivity {
    my_database db;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String ROW = "row";
    SharedPreferences sharedpreferences;
    int row;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new my_database(getBaseContext());
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.profile_image);
        //Sets row to get int
        row = sharedpreferences.getInt(ROW, 0);
        try {
            //Opens database
            db.open();
            //sets cursor at the row
            Cursor c = db.getAccountImage(row);
            imgView = (ImageView) findViewById(R.id.imgView);
            if (c.getString(8) != null) {
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(c.getString(8)));
            }
            db.close();

        }
        catch(Exception e){
            Toast.makeText(getBaseContext(),"we did not add image", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
               /* db = new my_database(getBaseContext());
                db.open();
                Cursor cs = db.getAccount(row);
                db.insertDetails(cs.getString(3),cs.getString(4),cs.getString(5),cs.getString(6),imgDecodableString, row);
                db.close();*/

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG)
                    .show();
        }

    }

}
