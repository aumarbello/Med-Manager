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

package com.khattabu.med_manager.di;

import android.app.Application;
import android.content.Context;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.local.db.MedicationDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ahmed on 4/11/18.
 */
@Module
public class AppModule {
    private Application application;
    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    MedicationDAO providesMedicationDAO(){
        return MedicationDatabase.getDatabase(application)
                .medicationDAO();
    }
}
