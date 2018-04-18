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

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.model.Medication;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 4/18/18.
 */

public class RestartAlarmService extends BaseService {
    @Inject MedicationDAO dao;

    static final int JOB_ID = 124;

    static void enqueueWork(Context context, Intent work){
        enqueueWork(context, RestartAlarmService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getComponent().inject(this);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        dao.getAllMedications()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(medications -> {
                    for (Medication medication : medications) {
                        AlarmUtils.setAlarm(this, medication);
                    }
                }, AppLogger::e);
    }
}
