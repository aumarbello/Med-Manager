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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.detail.DetailActivity;

/**
 * Created by ahmed on 4/16/18.
 */

public final class AlarmUtils {
    public static void setAlarm(Context context, Medication medication){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (manager != null){
            Intent intent = new Intent(context, NotificationsReceiver.class);
            intent.putExtra(DetailActivity.EXTRA_MEDICATION, medication);
            intent.setAction(medication.getMedicationId());

            PendingIntent pI = PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            manager.setRepeating(AlarmManager.RTC_WAKEUP,
                    medication.getStartDate(), getInterval(medication), pI);
        }
    }

    public static void removeAlarm(Context context, Medication medication){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (manager != null){
            Intent intent = new Intent(context, NotificationsReceiver.class);
            intent.putExtra(DetailActivity.EXTRA_MEDICATION, medication);
            intent.setAction(medication.getMedicationId());

            PendingIntent pI = PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            manager.cancel(pI);
        }
    }

    private static long getInterval(Medication medication){
        switch (medication.getIntervalType()){
            case "Days":
                return AlarmManager.INTERVAL_DAY * medication.getIntervalValue();
            case "Hours":
                return AlarmManager.INTERVAL_HOUR * medication.getIntervalValue();
            case "Minutes":
                long minuteInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15;
                return minuteInterval * medication.getIntervalValue();
        }

        return 0;
    }
}
