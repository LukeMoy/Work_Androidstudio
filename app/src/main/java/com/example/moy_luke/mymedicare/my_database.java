package com.example.moy_luke.mymedicare;
/**
 * Created by Luke Moy on 01/012/2016.
 * Verify Application
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//Class for the creation of the database
public class my_database {
    //variables for the database columns/rows
    public static final String KEY_ROWID = "_id";
    public static final String KEY_userNAME = "uName";
    public static final String KEY_password = "password";
    public static final String KEY_Name = "name";
    public static final String KEY_Address = "address";
    public static final String KEY_Age = "age";
    public static final String KEY_GPname = "gpname";
    public static final String KEY_GPnumber = "gpnumber";
    //public static final String KEY_ProfileImage ="profileimage";
    private static final String TAG = "MyDBHelper";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "login";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE = "create table login(_id integer primary key autoincrement, "+"uName text not null, password text not null, name, address, age, gpname, gpnumber);";

    //variables
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public my_database(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    //database helper class
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                //excutes SQL
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        //when the database is upgarded
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int
                newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS login");
            onCreate(db);
        }
    }
    //Opens the database
    public my_database open() throws SQLException
    {
        //opens database
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //closes database
    public void close()
    {
        DBHelper.close();
    }

   //Inserts users + passwords
    public void insertContact(String name, String pass)
    {
        //inserts new contacts
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_userNAME, name);
        initialValues.put(KEY_password, pass);
        db.insert(DATABASE_TABLE, null, initialValues);
    }

    //inserts the users details into the database
    public void insertDetails(String name, String address, String age, String gpname, String gpnumber, int row)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_Name, name);
        initialValues.put(KEY_Address, address);
        initialValues.put(KEY_Age, age);
        initialValues.put(KEY_GPname, gpname);
        initialValues.put(KEY_GPnumber, gpnumber);
        //initialValues.put(KEY_ProfileImage, profileimage);
        db.update(DATABASE_TABLE, initialValues, KEY_ROWID + " = " + row  ,null );
    }

   /** public void insertImage(String profileimage, int row) {
        ContentValues intialValues = new ContentValues();
        intialValues.put(KEY_ProfileImage, profileimage);
        db.update(DATABASE_TABLE, intialValues, KEY_ROWID + " = " + row ,null );
    }*/

    //Retrieves the contacts from database
    public Cursor getAllContacts()
    {
        //runs query to get contacts
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_userNAME,
                KEY_password}, null, null, null, null, null);
    }
    public Cursor getAccountImage(int i) throws SQLException {

        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_userNAME, KEY_password, KEY_Name, KEY_Address, KEY_Age, KEY_GPname, KEY_GPnumber}, KEY_ROWID + " = " + i, null,
                null, null, null, null);
        //If the pointer is not null
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //Retrieves a specfic account
    public Cursor getAccount(int i) throws SQLException {

        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_userNAME, KEY_password, KEY_Name, KEY_Address, KEY_Age, KEY_GPname, KEY_GPnumber}, KEY_ROWID + " = " + i, null,null,
                null, null, null, null);
        //If the pointer is not null
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }}