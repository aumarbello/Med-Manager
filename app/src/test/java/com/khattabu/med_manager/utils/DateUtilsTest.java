package com.khattabu.med_manager.utils;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by ahmed on 4/18/18.
 */
public class DateUtilsTest {
    @Test
    public void dateStringCorrect(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1524065614000L);

        String expectedDate = "Wed, 18 Apr 2018";
        String actualDate = DateUtils.dateLongToString(calendar.getTimeInMillis());

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void dateStringInCorrect(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1524065614000L);

        String expectedDate = "Thur, 19 Apr 2018";
        String actualDate = DateUtils.dateLongToString(calendar.getTimeInMillis());

        assertNotEquals(expectedDate, actualDate);
    }

    @Test
    public void intervalCorrect(){
        int interval = 1;
        String type = "Days";
        String expectedIntervalString = interval + " " + type;
        String actualIntervalString = DateUtils.getInterval(interval, type);

        assertEquals(expectedIntervalString, actualIntervalString);
    }


    @Test
    public void intervalInCorrect(){
        int interval = 1;
        String type = "Days";
        String expectedIntervalString = interval + "" + type;
        String actualIntervalString = DateUtils.getInterval(interval, type);

        assertNotEquals(expectedIntervalString, actualIntervalString);
    }

    @Test
    public void endDatePast(){
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        begin.setTimeInMillis(1524065614000L);
        end.setTimeInMillis(1521387214000L);

        String expected = "--";
        String actual = DateUtils.compareDates(begin.getTimeInMillis(), end.getTimeInMillis());

        assertEquals(expected, actual);
    }

    @Test
    public void compareDatesWeeks(){
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        begin.setTimeInMillis(1521387214000L);
        end.setTimeInMillis(1524065614000L);

        int beginWeek = begin.get(Calendar.WEEK_OF_YEAR);
        int endWeek = end.get(Calendar.WEEK_OF_YEAR);

        String expected = endWeek - beginWeek + " Weeks";
        String actual = DateUtils.compareDates(begin.getTimeInMillis(), end.getTimeInMillis());

        assertEquals(expected, actual);
    }

    @Test
    public void compareDatesDays(){
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        begin.setTimeInMillis(1521387214000L);
        end.setTimeInMillis(1524065614000L);

        int beginWeek = begin.get(Calendar.DAY_OF_YEAR);
        int endWeek = end.get(Calendar.DAY_OF_YEAR);

        String expected = endWeek - beginWeek + " Days";
        String actual = DateUtils.compareDates(begin.getTimeInMillis(), end.getTimeInMillis());

        assertNotEquals(expected, actual);
    }

    @Test
    public void monthString(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1521387214000L);

        assertEquals("March", DateUtils.getMonthString(calendar.getTimeInMillis()));
    }
}