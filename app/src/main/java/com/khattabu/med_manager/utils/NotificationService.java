package com.khattabu.med_manager.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.local.pref.MedicationPreference;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.MedManager;
import com.khattabu.med_manager.presentation.detail.DetailActivity;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by ahmed on 4/16/18.
 */

public class NotificationService extends JobIntentService {
    @Inject
    MedicationPreference preference;

    static final int JOB_ID = 312;

    static void enqueueWork(Context context,Intent work){
        enqueueWork(context, NotificationService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((MedManager)getApplicationContext()).getComponent().inject(this);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Medication medication = (Medication) intent
                .getSerializableExtra(DetailActivity.EXTRA_MEDICATION);


        sendNotification(medication);
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
    }

    private String getNotificationText(Medication medication){
        String format = "Hey %s, it's time for your %s medication!!!";

        String firstName = preference.getUser().getFirstName();
        String medicationTitle = medication.getTitle();
        return String.format(Locale.getDefault(), format, firstName, medicationTitle);
    }
}
