package com.example.quizz_home_work.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class StorageImplementation implements Storage {

    private static final String LOG_DATA = "quiz_log";
    private static final String TOP_SCORE="top_score";
    public static final String QUESTIONS="questions";
    public static final String HISTORY="history";
    @Override
    public void add(Context context, String key, Object value) {
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, new Gson().toJson(value));
        editor.apply();
    }

    @Override
    public Object getObject(Context context, String key, Class klass) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(key, null);
        return data == null ? null : new Gson().fromJson(data, klass);
    }

    public String getTopScore(Context context) {
        SharedPreferences sharedPref = getInstance(context);
        return sharedPref.getString(TOP_SCORE, null);
    }

    public void saveTopScore(Context context,  String value) {
        SharedPreferences sharedPref = getInstance(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOP_SCORE, value);
        editor.apply();
    }


    @Override
    public String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getInstance(context);
        return sharedPreferences.getString(key, null);
    }

    private SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences(LOG_DATA, Context.MODE_PRIVATE);
    }
}
