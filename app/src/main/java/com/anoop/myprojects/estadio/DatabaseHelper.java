package com.anoop.myprojects.estadio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anoop.myprojects.estadio.DataModels.TurfBookingModel;
import com.anoop.myprojects.estadio.DataModels.TurfListItem;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DataModels.UserModel;

import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 11/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "turf_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TURF = "turfs";

    private static final String TABLE_USER = "users";
    private static final String TABLE_OWNER_USER = "owner_users";
    private static final String TABLE_TURF_BOOKING = "turf_bookings";

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
            "owner_id INTEGER ,"+
            "address TEXT ,"+
            "description TEXT ,"+
            "phone TEXT );";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT ,"+
            "address TEXT ,"+
            "email TEXT NOT NULL UNIQUE ,"+
            "username TEXT NOT NULL UNIQUE ,"+
            "password TEXT ,"+
            "phone TEXT );";

    private static final String CREATE_TABLE_OWNER_USER = "CREATE TABLE "
            + TABLE_OWNER_USER +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT ,"+
            "address TEXT ,"+
            "email TEXT NOT NULL UNIQUE ,"+
            "username TEXT NOT NULL UNIQUE ,"+
            "password TEXT ,"+
            "phone TEXT );";

    private static final String CREATE_TABLE_TURF_BOOKING = "CREATE TABLE "
            + TABLE_TURF_BOOKING +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER , "+
            "turf_id INTEGER , "+
            "approve INTEGER , "+
            "owner_id INTEGER ,"+
            "date_ TEXT NOT NULL,"+
            "time_from TEXT NOT NULL,"+
            "time_to TEXT NOT NULL );";


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
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_OWNER_USER);
        db.execSQL(CREATE_TABLE_TURF_BOOKING);
//        db.execSQL(CREATE_TABLE_USER_HOBBY);
//        db.execSQL(CREATE_TABLE_USER_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_TURF + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_OWNER_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_TURF_BOOKING + "'");
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_HOBBY + "'");
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_CITY + "'");
        onCreate(db);
    }

    public long addTurfs(String name, String address, String desc,String phone,int owner_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("owner_id", owner_id);
        values.put("description", desc);
        values.put("phone", phone);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_TURF, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        return id;

    }

    public long addTurfBooking(TurfBookingModel turfBookingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();

        values.put("turf_id", turfBookingModel.getTurf_id());
        values.put("user_id",turfBookingModel.getUser_id());
        values.put("date_",turfBookingModel.getDate());
        values.put("owner_id",turfBookingModel.getOwner_id());
        values.put("time_from",turfBookingModel.getTime_from());
        values.put("time_to",turfBookingModel.getTime_to());
        values.put("approve",0);

        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_TURF_BOOKING, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        return id;

    }

    public long approveTurfBooking(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();

        values.put("approve",1);

        // db.insert(TABLE_USER, null, values);
        long id__ = db.updateWithOnConflict(TABLE_TURF_BOOKING,values,"id="+id,null,SQLiteDatabase.CONFLICT_IGNORE);

        return id__;

    }


    public long addUser(String name, String address, String phone , String username, String password, String email,boolean isOwner) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("username", username);
        values.put("password", password);
        values.put("email", email);
        values.put("phone", phone);
        // db.insert(TABLE_USER, null, values);
        long id;
        if(isOwner)
        {
            id = db.insertWithOnConflict(TABLE_OWNER_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
            return id;
        }

        id = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return id;

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


    public ArrayList<TurfBookingModel> getAllTurfBookingsForTurf(int turf_id) {
        ArrayList<TurfBookingModel> userModelArrayList = new ArrayList<TurfBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF_BOOKING+ " WHERE turf_id = " + String.valueOf(turf_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfBookingModel userModel = new TurfBookingModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setDate(c.getString(c.getColumnIndex("date_")));
                userModel.setTime_from(c.getString(c.getColumnIndex("time_from")));
                userModel.setTime_to(c.getString(c.getColumnIndex("time_to")));
                userModel.setTurf_id(c.getInt(c.getColumnIndex("turf_id")));
                userModel.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));
                userModel.setApprove(c.getInt(c.getColumnIndex("approve")));
                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        c.close();
        return userModelArrayList;
    }

    public ArrayList<TurfBookingModel> getTurfBooking(int id) {
        ArrayList<TurfBookingModel> userModelArrayList = new ArrayList<TurfBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF_BOOKING+ " WHERE id = " + String.valueOf(id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfBookingModel userModel = new TurfBookingModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setDate(c.getString(c.getColumnIndex("date_")));
                userModel.setTime_from(c.getString(c.getColumnIndex("time_from")));
                userModel.setTime_to(c.getString(c.getColumnIndex("time_to")));
                userModel.setTurf_id(c.getInt(c.getColumnIndex("turf_id")));
                userModel.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));
                userModel.setApprove(c.getInt(c.getColumnIndex("approve")));
                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        c.close();
        return userModelArrayList;
    }

    public ArrayList<TurfBookingModel> getAllTurfBookingsForOwner(int owner_id) {
        ArrayList<TurfBookingModel> userModelArrayList = new ArrayList<TurfBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF_BOOKING+ " WHERE owner_id = " + owner_id ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        System.out.println(selectQuery);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfBookingModel userModel = new TurfBookingModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setDate(c.getString(c.getColumnIndex("date_")));
                userModel.setTime_from(c.getString(c.getColumnIndex("time_from")));
                userModel.setTime_to(c.getString(c.getColumnIndex("time_to")));
                userModel.setTurf_id(c.getInt(c.getColumnIndex("turf_id")));
                userModel.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));
                userModel.setApprove(c.getInt(c.getColumnIndex("approve")));
                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();

        System.out.println(userModelArrayList.size());
        return userModelArrayList;
    }

    public ArrayList<TurfBookingModel> getAllTurfBookingsForUser(int user_id) {
        ArrayList<TurfBookingModel> userModelArrayList = new ArrayList<TurfBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF_BOOKING+ " WHERE user_id = " + String.valueOf(user_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfBookingModel userModel = new TurfBookingModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setDate(c.getString(c.getColumnIndex("date_")));
                userModel.setTime_from(c.getString(c.getColumnIndex("time_from")));
                userModel.setTime_to(c.getString(c.getColumnIndex("time_to")));
                userModel.setTurf_id(c.getInt(c.getColumnIndex("turf_id")));
                userModel.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));
                userModel.setApprove(c.getInt(c.getColumnIndex("approve")));
                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();
        return userModelArrayList;
    }

    public ArrayList<TurfBookingModel> getAllTurfBookings() {
        ArrayList<TurfBookingModel> userModelArrayList = new ArrayList<TurfBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF_BOOKING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfBookingModel userModel = new TurfBookingModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setDate(c.getString(c.getColumnIndex("date_")));
                userModel.setTime_from(c.getString(c.getColumnIndex("time_from")));
                userModel.setTime_to(c.getString(c.getColumnIndex("time_to")));
                userModel.setTurf_id(c.getInt(c.getColumnIndex("turf_id")));
                userModel.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));
                userModel.setApprove(c.getInt(c.getColumnIndex("approve")));
                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();
        return userModelArrayList;
    }



    public ArrayList<TurfModel> getAllTurfs() {
        ArrayList<TurfModel> userModelArrayList = new ArrayList<TurfModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfModel userModel = new TurfModel();
                userModel.setId_(c.getInt(c.getColumnIndex("id")));
                userModel.setName(c.getString(c.getColumnIndex("name")));
                userModel.setVersion(c.getString(c.getColumnIndex("description")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));

                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();
        return userModelArrayList;
    }

    public ArrayList<TurfListItem> getAllTurfsList() {
        ArrayList<TurfListItem> userModelArrayList = new ArrayList<TurfListItem>();

        String selectQuery = "SELECT  * FROM " + TABLE_TURF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfListItem userModel = new TurfListItem();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setTableId(c.getInt(c.getColumnIndex("id")));
                userModel.setName(c.getString(c.getColumnIndex("name")));
                userModel.setPhone(c.getString(c.getColumnIndex("phone")));
                userModel.setView(R.drawable.ic_remove_red_eye_black_24dp);
                userModel.setDelete(R.drawable.ic_delete_black_24dp);
                // adding to turfs list
                userModelArrayList.add(userModel);
                System.out.println(userModel.toString());
            } while (c.moveToNext());
        }
        c.close();
        return userModelArrayList;
    }

    public UserModel getUser(int id,boolean isOwner) {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();

        String selectQuery;
        if(isOwner)
        {
            selectQuery = "SELECT  * FROM " + TABLE_OWNER_USER +" WHERE id = " + String.valueOf(id);
        }
        else {
            selectQuery = "SELECT  * FROM " + TABLE_USER +" WHERE id = " + String.valueOf(id);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex("id")));
                userModel.setName(c.getString(c.getColumnIndex("name")));
                userModel.setAddress(c.getString(c.getColumnIndex("address")));
                userModel.setEmail(c.getString(c.getColumnIndex("email")));
                userModel.setPhone(c.getString(c.getColumnIndex("phone")));


                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();
        return userModelArrayList.get(0);
    }

    public TurfModel getTurf(int id) {
        ArrayList<TurfModel> userModelArrayList = new ArrayList<TurfModel>();

        String selectQuery;
        selectQuery = "SELECT  * FROM " + TABLE_TURF +" WHERE id = " + String.valueOf(id);


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TurfModel userModel = new TurfModel();
                userModel.setId_(c.getInt(c.getColumnIndex("id")));
                userModel.setName(c.getString(c.getColumnIndex("name")));
                userModel.setVersion(c.getString(c.getColumnIndex("description")));
                userModel.setOwner_id(c.getInt(c.getColumnIndex("owner_id")));

                // adding to turfs list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }

        c.close();
        return userModelArrayList.get(0);
    }

    public int isValidUser(String username, String password,boolean isOwner) {
        String selectQuery;
        if(isOwner)
        {
            selectQuery = "SELECT  * FROM " + TABLE_OWNER_USER +
                    " WHERE username like  '" +
                    username + "' AND password like '" +
                    password + "'";
        }
        else {
            selectQuery = "SELECT  * FROM " + TABLE_USER +
                    " WHERE username like  '" +
                    username + "' AND password like '" +
                    password + "'";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (c.moveToFirst()) {
            System.out.println("name" + c.getString(c.getColumnIndex("name")));
            System.out.println("id" + c.getString(c.getColumnIndex("id")));
            return Integer.parseInt(c.getString(c.getColumnIndex("id")));
        }

        c.close();
        return -1;
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
