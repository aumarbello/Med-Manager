package com.khattabu.med_manager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ahmed on 4/10/18.
 */

public final class DateUtils {
    public static String dateLongToString(long dateInMilliseconds){
        String pattern = "EEE, d MMM yyyy";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateInMilliseconds);

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}
