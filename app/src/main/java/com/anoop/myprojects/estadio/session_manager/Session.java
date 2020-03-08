package com.anoop.myprojects.estadio.session_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;


    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setValues(String usename,String id,String isOwner) {
        prefs.edit().putString("username", usename).apply();
        prefs.edit().putString("id", id).apply();
        prefs.edit().putString("isowner", isOwner).apply();
        prefs.edit().putString("islogin", "true").apply();

    }

    public String getusename() {

        String usename = prefs.getString("username","");
        return usename;
    }
    public String getId() {
        String ids = prefs.getString("id","");
        return ids;
    }

    public boolean isIsLogin()
    {
        String islogin = prefs.getString("islogin","");

        if(islogin.equals("true"))
        {
            return true;
        }
        return false;
    }

    public void logOut()
    {
        prefs.edit().putString("islogin", "false").apply();
    }

    public String getIsOwner() {
        String owner = prefs.getString("isowner","");
        return owner;
    }
}