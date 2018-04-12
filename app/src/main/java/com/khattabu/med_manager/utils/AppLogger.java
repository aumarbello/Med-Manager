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
