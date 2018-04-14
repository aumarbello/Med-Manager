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

import java.text.ParseException;
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

    public static long dateStringToLong(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());

        try {
            return dateFormat.parse(text).getTime();
        }catch (ParseException ex){
            return Calendar.getInstance().getTimeInMillis();
        }
    }

    public static String getMonthString(long startDateLong) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startDateLong);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM",
                Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}
