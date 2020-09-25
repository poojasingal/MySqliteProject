package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Currency;


public class SqLiteHelper {

   DbHelper dbHelper;

    public SqLiteHelper(Context context){
        dbHelper = new DbHelper(context);
    }

    public SqLiteHelper open(Context context) throws  Exception{

        dbHelper = new DbHelper(context);
        SQLiteDatabase database =  dbHelper.getWritableDatabase();

        return this;

    }

    public void close(){

        if (dbHelper!=null){
            dbHelper.close();
        }
    }


    public static class DbHelper extends SQLiteOpenHelper {

        Context context;

        // contact
        private static final int DATABASE_VERSION = 4;
        private static final String DATABASE_NAME = "contactsManager";
        public static final String KEY_ID = "id";

        public static final String TABLE_CONTACTS = "contacts";
        public static final String KEY_NAME = "name";
        public static final String KEY_PH_NO = "phone_number";

        // users
        public static final String TABLE_USERS = "users";
        public static final String KEY_USER_NAME = "username";
        public static final String KEY_USER_LOC = "userloc";
        public static final String KEY_USER_DESG = "userdesg";


      public static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_CONTACTS +"("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " VARCHAR(255), "
                + KEY_PH_NO + " VARCHAR(255))";
      public static final String DROP_CONTACT_TABLE = "DROP TABLE IF EXISTS "+ TABLE_CONTACTS;

      public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
              + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_NAME + " VARCHAR(255), "
              + KEY_USER_LOC + " VARCHAR(255), " + KEY_USER_DESG + " VARCHAR(255))";
      public static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS;



        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }


        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_CONTACT_TABLE);
            db.execSQL(CREATE_USER_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(DROP_CONTACT_TABLE);
            db.execSQL(DROP_USERS_TABLE);

        }


    }


    // cantact
    public  long insertContact (String name, String phoneno){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelper.KEY_NAME, name);
        contentValues.put(DbHelper.KEY_PH_NO, phoneno);

        long id = sqLiteDatabase.insert(DbHelper.TABLE_CONTACTS, null, contentValues);
        return id;
    }


    public Cursor getAllContactData(){
        SQLiteDatabase  sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(" SELECT * FROM "+ DbHelper.TABLE_CONTACTS, null);
        return c;
    }

    public Cursor getPhonno(String name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT DISTINCT " + DbHelper.KEY_PH_NO + "FROM"+
                DbHelper.TABLE_CONTACTS + "WHERE" + DbHelper.KEY_NAME + " = '" + name +" ' ", null);

        return c;
    }


    public int DeleteContact(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(DbHelper.TABLE_CONTACTS, null, null);
        return count;
    }


    //users
    public long insertUsers(String name, String loc, String desg){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_USER_NAME, name);
        contentValues.put(DbHelper.KEY_USER_LOC, loc);
        contentValues.put(DbHelper.KEY_USER_DESG, desg);

        long id = db.insert(DbHelper.TABLE_USERS, null, contentValues);

        return id;
    }

    public Cursor getAllUserDetails(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM "+ DbHelper.TABLE_USERS, null);

        return c;
    }


    public int DeleteUsers(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(DbHelper.TABLE_USERS, null, null);
        return count;
    }

}
