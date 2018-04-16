package com.khattabu.med_manager.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ahmed on 4/16/18.
 */

public class NotificationsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        intent.setClass(context, NotificationService.class);
        NotificationService.enqueueWork(context, intent);
    }
}
