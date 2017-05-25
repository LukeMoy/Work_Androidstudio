package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 * The Verify Application allows users to create account,
 * upload images from gallery, call people, and update user details.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{//}, GoogleMap.OnMapLongClickListener {

    public static GoogleMap mMap;
    //public static myDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //db = new myDatabase(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    /*public void onMapLongClick(LatLng point) {
        AlertDialog.Builder ADB = new AlertDialog.Builder(this);
        ADB.setTitle("Erase Marker Database?");
        ADB.setMessage("Would you like to erase the marker points database. This can not be undone");
        ADB.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Delete Database
                Toast.makeText(getBaseContext(), "All Markers Erased", Toast.LENGTH_LONG).show();
                //db.context.deleteDatabase("MyDB");
                mMap.clear();
            }
        });
        ADB.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do nothing
            }
        });
        ADB.setIcon(android.R.drawable.ic_dialog_alert);
        ADB.create();
        ADB.show();
    }
*/
    /*public void prepopulate(GoogleMap googleMap){
        mMap = googleMap;
        //db = new myDatabase(getBaseContext());
        //db.open();
        try{
            //int lineNum = 1;
            //cursor to hold line position in database and information of database row
            //Cursor c = db.getAccount(lineNum);
            //variable to hold data of first column
            //String num = c.getString(1);
            //while loop continues though all rows in database until
            //username matches or get to end of database (null value)
            while(!num.equals(null)){
                Log.d("Llama Robyn", Double.parseDouble(c.getString(2)) +" " + Double.parseDouble(c.getString(3)));
                LatLng llama = new LatLng(Double.parseDouble(c.getString(2)), Double.parseDouble(c.getString(3)));
                mMap.addMarker(new MarkerOptions().position(llama).title(c.getString(4)));

                lineNum++;
                c = db.getAccount(lineNum);
                num = c.getString(1);
            }
        }
        catch (NullPointerException j){
            Toast.makeText(getBaseContext(), "No Pointers Found", Toast.LENGTH_LONG).show();
        }
        catch(CursorIndexOutOfBoundsException n){
            Toast.makeText(getBaseContext(), "All Pointers Restored", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            //if username does not match with any in the database, return error
            Toast.makeText(getBaseContext(), "Error Pre-Populating", Toast.LENGTH_LONG).show();
            Log.d("Llama Robyn",e.toString());
        }
        //close database
        db.close();
    }*/
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Edgehill University
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng Edgehill = new LatLng(53.5594603, -2.8751051);
        mMap.addMarker(new MarkerOptions().position(Edgehill).title("Marker in Edgehill"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Edgehill));
        //prepopulate(mMap);
        //mMap.setOnMapLongClickListener(this);
        //IncomingSms IS = new  IncomingSms();
        //IS.holdMap(mMap);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    public void addMarker(GoogleMap googleMap, String number,  double lat, double lon, String title){


        //db.open();
        //db.insertContact(number, Double.toString(lat), Double.toString(lon), title);
        //db.close();

        mMap = googleMap;
        LatLng llama = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(llama).title(title));

        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }
}
