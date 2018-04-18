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

import android.support.v4.app.JobIntentService;

import com.khattabu.med_manager.di.component.ActivityComponent;
import com.khattabu.med_manager.di.component.DaggerActivityComponent;
import com.khattabu.med_manager.di.modules.ActivityModule;
import com.khattabu.med_manager.presentation.MedManager;

/**
 * Created by ahmed on 4/18/18.
 */

public abstract class BaseService extends JobIntentService {
    private ActivityComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .appComponent(((MedManager)getApplication()).getComponent())
                .build();
    }

    protected ActivityComponent getComponent(){
        return component;
    }
}
