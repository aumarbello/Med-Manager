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
