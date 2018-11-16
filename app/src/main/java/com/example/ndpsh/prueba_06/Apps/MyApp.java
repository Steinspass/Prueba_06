package com.example.ndpsh.prueba_06.Apps;

import android.app.Application;
import android.os.SystemClock;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(3000);

    }
}
