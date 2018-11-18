package com.example.ndpsh.prueba_06.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }
    public static String getUserPassPrefs(SharedPreferences preferences) {
        return preferences.getString("pass", "");
    }
    public static void RemoveSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.apply();
    }


}
