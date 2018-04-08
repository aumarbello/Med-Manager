package com.khattabu.med_manager.presentation;

import android.app.Application;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.utils.AppLogger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ahmed on 4/8/18.
 */

public class MedManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppLogger.init();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Thin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
