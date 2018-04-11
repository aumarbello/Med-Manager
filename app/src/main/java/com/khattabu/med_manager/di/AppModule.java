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
