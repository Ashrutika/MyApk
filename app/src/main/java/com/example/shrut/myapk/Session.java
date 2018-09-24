package com.example.shrut.myapk;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root1 on 24/9/18.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("MyApk",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode",false);

    }


}
