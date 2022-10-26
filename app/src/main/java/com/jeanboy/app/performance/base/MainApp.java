package com.jeanboy.app.performance.base;

import android.app.Application;

import com.github.anrwatchdog.ANRWatchDog;
import com.github.moduth.blockcanary.BlockCanary;
import com.jeanboy.app.performance.helper.AppBlockCanaryContext;

/**
 * Created by jeanboy on 2021/4/11 21:15.
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        new ANRWatchDog().start();
    }
}
