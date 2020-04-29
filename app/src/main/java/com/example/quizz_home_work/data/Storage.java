package com.example.quizz_home_work.data;

import android.content.Context;

public interface Storage {

  void add(Context context, String key, Object value);

  Object getObject(Context context, String key, Class klass);

  String getString(Context context, String key);
}
