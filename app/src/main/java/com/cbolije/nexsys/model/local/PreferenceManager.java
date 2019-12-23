package com.cbolije.nexsys.model.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.cbolije.nexsys.utils.NexsysApp;


public class PreferenceManager  {

    private final static String PREFS_NAME = "Nexsys_Preferences";
    private static PreferenceManager prefs;
    private SharedPreferences mPrefs;

    public enum Key {
        USER_DATA, IS_LOGGED
    }

    private PreferenceManager() {
        mPrefs = NexsysApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance () {
        if (prefs == null) {
            prefs = new PreferenceManager();
        }
        return prefs;
    }


    public String getString(Key key) {
        return getString(key, null);
    }

    public String getString(Key key, String defaultValue) {
        return mPrefs.getString(key.name(), defaultValue);
    }

    public boolean getBoolean(Key key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(Key key, boolean defaultValue) {
        return mPrefs.getBoolean(key.name(), defaultValue);
    }

    public int getInt(Key key) {
        return getInt(key, 0);
    }

    public int getInt(Key key, int defaultValue) {
        return mPrefs.getInt(key.name(), defaultValue);
    }

    public void put(Key key, String value) {
        mPrefs.edit().putString(key.name(), value).apply();
    }

    public void put(Key key, int value) {
        mPrefs.edit().putInt(key.name(), value).apply();
    }

    public void put(Key key, boolean value) {
        mPrefs.edit().putBoolean(key.name(), value).apply();
    }

    public boolean putAndWait(Key key, String value) {
        return mPrefs.edit().putString(key.name(), value).commit();
    }

    public void remove(Key... keys) {
        for (Key key : keys) {
            mPrefs.edit().remove(key.name()).apply();
        }
    }

    public void clear() {
        mPrefs.edit().clear().apply();
    }

}
