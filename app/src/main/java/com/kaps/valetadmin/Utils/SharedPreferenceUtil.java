package com.kaps.valetadmin.Utils;



import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private static SharedPreferenceUtil mSharedPreferenceUtil = new SharedPreferenceUtil();

    private static SharedPreferences mPref;
    private static final String SETTINGS_NAME = "valet_admin_prefs";
    private static SharedPreferences.Editor sEditor;


    public static SharedPreferenceUtil getInstance(Context context){
        if( mPref == null) {

            mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
            sEditor = mPref.edit();
        }
        return  mSharedPreferenceUtil;
    }

    public static void putString(String key, String value){

        sEditor.putString(key, value);
        sEditor.apply();
    }

    public static String getString(String key){
        return mPref.getString(key, null);
    }

    public static void removeAll(){
        sEditor.clear();
        sEditor.commit();
    }
}

