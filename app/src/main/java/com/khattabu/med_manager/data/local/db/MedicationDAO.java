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

package com.khattabu.med_manager.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.khattabu.med_manager.data.model.Medication;

import java.util.List;

/**
 * Created by ahmed on 4/10/18.
 */
@Dao
public interface MedicationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedication(Medication medication);

    @Update
    void updateMedication(Medication medication);

    @Delete
    void deleteMedication(Medication medication);

    @Query("SELECT * FROM medication")
    List<Medication> getAllMedications();

    @Query("SELECT * FROM medication WHERE title LIKE :searchQuery" +
            " OR month LIKE :searchQuery")
    List<Medication> searchMedications(String searchQuery);

    @Query("SELECT COUNT (*) FROM medication")
    int getMedicationCount();
}
