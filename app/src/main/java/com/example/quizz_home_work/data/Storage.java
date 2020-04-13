package com.example.quizz_home_work.data;

import android.content.Context;

public interface Storage {

  void save(Context context, String key, String value);

  String get(Context context, String key);

}
