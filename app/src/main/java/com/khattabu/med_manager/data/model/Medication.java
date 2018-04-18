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

package com.khattabu.med_manager.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ahmed on 4/8/18.
 */
@Entity
public class Medication implements Serializable{

    @NonNull @PrimaryKey private String medicationId;

    private String title;
    private String month;
    private String description;
    private long dateAdded;
    private long startDate;
    private long endDate;
    private int intervalValue;
    private String intervalType;

    public Medication(@NonNull String medicationId, String title,
                      String month, String description,
                      long dateAdded, long startDate,
                      long endDate, int intervalValue,
                      String intervalType){
        this.medicationId = medicationId;
        this.title = title;
        this.month = month;
        this.description = description;
        this.dateAdded = dateAdded;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intervalValue = intervalValue;
        this.intervalType = intervalType;
    }

    @Ignore
    public Medication(String title,
                      String month, String description,
                      long dateAdded, long startDate,
                      long endDate, int intervalValue,
                      String intervalType){
        this.medicationId = UUID.randomUUID().toString();
        this.title = title;
        this.month = month;
        this.description = description;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getMonth() {
        return month;
    }

    public String getDescription() {
        return description;
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
