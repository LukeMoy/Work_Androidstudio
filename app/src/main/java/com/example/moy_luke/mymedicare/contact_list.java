package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.TextView;

//Contact List Class, users can browse contact list and call/text the selected user
public class contact_list extends AppCompatActivity {
    //Vairables
    static final int PICK_CONTACT_REQUEST = 1;
    private String numberPicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Opens PAge
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__list);
        //Instantiating the Buttons
        Button Call = (Button) findViewById(R.id.btnCall);
        Button b3 = (Button) findViewById(R.id.btnContact);
        //On click for button for opening Contact List
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact = new Intent(android.content.Intent.ACTION_PICK, Uri.parse("content://contacts"));
                contact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(contact, PICK_CONTACT_REQUEST);
            }
        });
        // THe selected number will be called on button press
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_DIAL);
                myIntent.setData(Uri.parse("tel:" + numberPicked));
                startActivity(myIntent);
            }
        }
        );
        //Instantiates button
        Button btntext = (Button) findViewById(R.id.btnText);
        //The selected number will be texted on button press
        btntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms=SmsManager.getDefault();
                if(!numberPicked.equals("")){
                    sms.sendTextMessage(numberPicked, null, "Message from myMediCare application", null, null);
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request is being responded to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Checks to see if the request was is successful
            if (resultCode == RESULT_OK) {
                // Points to selected conatct
                Uri contactUri = data.getData();
               //Gets number column
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                //Querys database
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Gets the number from the column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                numberPicked = cursor.getString(column);

                //displays the number in plaintext view
                TextView txtContactNumber = (TextView) findViewById(R.id.txtContactNum1);
                txtContactNumber.setText(numberPicked.toString());

            }
        }
    }
}