package com.limecoders.informativeapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private SharedPreferences preferences;
    private Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("com.limecoders.informativeapp",Context.MODE_PRIVATE);
    }

    public void setButtonClicked(int clicked){
        editor = preferences.edit();
        editor.putInt("clicked",clicked);
        editor.apply();
    }

    public int getButtonClicked(){
        return preferences.getInt("clicked",0);
    }

}
