package com.khattabu.med_manager.utils;

import com.khattabu.med_manager.BuildConfig;

import timber.log.Timber;

/**
 * Created by ahmed on 4/8/18.
 */

public final class AppLogger {

    public static void init(){
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void d(String message){
        Timber.d(message);
    }

    public static void e(Throwable throwable){
        Timber.e(throwable);
    }

    public static void e(String title, Throwable error){
        Timber.e(error, title);
    }
}
