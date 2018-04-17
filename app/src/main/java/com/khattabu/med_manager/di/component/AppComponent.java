package com.khattabu.med_manager.di.component;

import android.content.Context;

import com.khattabu.med_manager.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ahmed on 4/13/18.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();
}
