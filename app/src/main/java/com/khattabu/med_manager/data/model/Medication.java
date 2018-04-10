package com.khattabu.med_manager.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by ahmed on 4/8/18.
 */
@Entity
public class Medication {
    @NonNull
    @PrimaryKey
    private String medicationId;

    private String title;
    private String month;
    private String desc;
    private long dateAdded;
    private long startDate;
    private long endDate;
    private int intervalValue;
    private String intervalType;

    public Medication(@NonNull String medicationId, String title,
                      String month, String desc,
                      long dateAdded, long startDate,
                      long endDate, int intervalValue,
                      String intervalType){
        this.medicationId = medicationId;
        this.title = title;
        this.month = month;
        this.desc = desc;
        this.dateAdded = dateAdded;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intervalValue = intervalValue;
        this.intervalType = intervalType;
    }

    @Ignore
    public Medication(String title,
                      String month, String desc,
                      long dateAdded, long startDate,
                      long endDate, int intervalValue,
                      String intervalType){
        this.medicationId = UUID.randomUUID().toString();
        this.title = title;
        this.month = month;
        this.desc = desc;
        this.dateAdded = dateAdded;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intervalValue = intervalValue;
        this.intervalType = intervalType;
    }


    @NonNull
    public String getMedicationId() {
        return medicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getMonth() {
        return month;
    }

    public String getDesc() {
        return desc;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public int getIntervalValue() {
        return intervalValue;
    }

    public String getIntervalType() {
        return intervalType;
    }
}
