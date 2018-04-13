/*
 * Copyright 2018 Ahmed, Umar Bello.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.khattabu.med_manager.presentation;

import android.app.Application;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.di.component.AppComponent;
import com.khattabu.med_manager.di.component.DaggerAppComponent;
import com.khattabu.med_manager.di.modules.AppModule;
import com.khattabu.med_manager.utils.AppLogger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ahmed on 4/8/18.
 */

public class MedManager extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        AppLogger.init();

        component = init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Thin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public AppComponent init(MedManager application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
