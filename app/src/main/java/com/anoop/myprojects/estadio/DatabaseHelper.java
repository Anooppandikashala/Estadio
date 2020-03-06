package com.anoop.myprojects.estadio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 11/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "turf_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TURF = "turfs";
//    private static final String TABLE_USER_HOBBY = "users_hobby";
//    private static final String TABLE_USER_CITY = "users_city";
//    private static final String KEY_ID = "id";
//    private static final String KEY_FIRSTNAME = "name";
//    private static final String KEY_HOBBY = "hobby";
//    private static final String KEY_CITY = "city";

    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_TURF = "CREATE TABLE "
            + TABLE_TURF +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT ,"+
            "address TEXT ,"+
            "description TEXT ,"+
            "phone TEXT );";


//    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
//            + TABLE_USER + "(" + KEY_ID
//            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT );";
//
//    private static final String CREATE_TABLE_USER_HOBBY = "CREATE TABLE "
//            + TABLE_USER_HOBBY + "(" + KEY_ID + " INTEGER,"+ KEY_HOBBY + " TEXT );";
//
//    private static final String CREATE_TABLE_USER_CITY = "CREATE TABLE "
//            + TABLE_USER_CITY + "(" + KEY_ID + " INTEGER,"+ KEY_CITY + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

       // Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TURF);
//        db.execSQL(CREATE_TABLE_USER_HOBBY);
//        db.execSQL(CREATE_TABLE_USER_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_TURF + "'");
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_HOBBY + "'");
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_CITY + "'");
        onCreate(db);
    }

    public void addTurfs(String name, String address, String desc,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("description", desc);
        values.put("phone", phone);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_TURF, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }

//        //adding user hobby in users_hobby table
//        ContentValues valuesHobby = new ContentValues();
//        valuesHobby.put("ID", id);
//        valuesHobby.put(KEY_HOBBY, hobby);
//        db.insert(TABLE_USER_HOBBY, null, valuesHobby);
//
//        //adding user city in users_city table
//        ContentValues valuesCity = new ContentValues();
//        valuesCity.put(KEY_ID, id);
//        valuesCity.put(KEY_CITY, city);
//        db.insert(TABLE_USER_CITY, null, valuesCity);






    public ArrayList<DataModel> getAllTurfs() {
        ArrayList<DataModel> userModelArrayList = new ArrayList<DataModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DataModel userModel = new DataModel();
                userModel.setId_(c.getInt(c.getColumnIndex("id")));
                userModel.setName(c.getString(c.getColumnIndex("name")));
                userModel.setVersion(c.getString(c.getColumnIndex("description")));

                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }


/*
    public void updateUser(int id, String name, String hobby, String city) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, name);
        db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        // updating hobby in users_hobby table
        ContentValues valuesHobby = new ContentValues();
        valuesHobby.put(KEY_HOBBY, hobby);
        db.update(TABLE_USER_HOBBY, valuesHobby, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        // updating city in users_city table
        ContentValues valuesCity = new ContentValues();
        valuesCity.put(KEY_CITY, city);
        db.update(TABLE_USER_CITY, valuesCity, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_USER, KEY_ID + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_USER_HOBBY, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        //deleting from users_city table
        db.delete(TABLE_USER_CITY, KEY_ID + " = ?",new String[]{String.valueOf(id)});
    }
*/
}
