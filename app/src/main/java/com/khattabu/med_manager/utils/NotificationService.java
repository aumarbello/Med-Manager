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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.local.pref.MedicationPreference;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.detail.DetailActivity;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by ahmed on 4/16/18.
 */

public class NotificationService extends BaseService {
    @Inject MedicationPreference preference;

    @Inject MedicationDAO dao;

    static final int JOB_ID = 312;

    static void enqueueWork(Context context,Intent work){
        enqueueWork(context, NotificationService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getComponent().inject(this);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Bundle args = intent.getBundleExtra(AlarmUtils.MEDIC_EXTRA);

        Medication medication = (Medication) args.getSerializable(DetailActivity.EXTRA_MEDICATION);

        if (DateUtils.endDatePast(medication)){
            deleteMedication(medication);
        }else
            sendNotification(medication);
    }

    private void deleteMedication(Medication medication) {
        dao.deleteMedication(medication);

        AlarmUtils.removeAlarm(this, medication);
    }

    private void sendNotification(Medication medication) {
        String medId = "Medication";
        Notification.Builder notificationBuilder;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notificationBuilder = new Notification.Builder(this, medId);
        }else {
            notificationBuilder = new Notification.Builder(this);
        }

        Intent intent = DetailActivity.getStartIntent(this, medication);
        PendingIntent pI = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentTitle("Time For Medication")
                .setContentText(getNotificationText(medication))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_medication)
                .setContentIntent(pI);

        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null){
            manager.notify(JOB_ID, notificationBuilder.build());
        }
    }

    private String getNotificationText(Medication medication){
        String format = "Hey %s, it's time for your %s medication!!!";

        String firstName = preference.getUser().getFirstName();
        String medicationTitle = medication.getTitle();
        return String.format(Locale.getDefault(), format, firstName, medicationTitle);
    }
}
