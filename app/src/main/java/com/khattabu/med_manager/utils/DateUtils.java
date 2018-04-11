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

    public static String getInterval(int intervalValue, String intervalType){
        return String.format(Locale.getDefault(), "%d %s",
                intervalValue, intervalType);
    }

    public static String compareDates(long begin, long end){
        Calendar beginCalender = Calendar.getInstance();
        beginCalender.setTimeInMillis(begin);

        Calendar endCalender = Calendar.getInstance();
        endCalender.setTimeInMillis(end);

        if (end < begin){
            return "--";
        }

        int beginWeek = beginCalender.get(Calendar.WEEK_OF_YEAR);
        int endWeek = endCalender.get(Calendar.WEEK_OF_YEAR);

        if (beginWeek - endWeek < 1){
            int beginDay = beginCalender.get(Calendar.DAY_OF_YEAR);
            int endDay = endCalender.get(Calendar.DAY_OF_YEAR);

            return getComparedDate(endDay - beginDay, " Days");
        }

        return getComparedDate(endWeek - beginWeek, " Weeks");
    }

    private static String getComparedDate(int diff, String factor){
        String format = "%d %s";
        return String.format(Locale.getDefault(), format, diff, factor);
    }
}
