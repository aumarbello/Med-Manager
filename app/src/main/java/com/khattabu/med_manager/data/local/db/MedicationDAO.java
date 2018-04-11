package com.khattabu.med_manager.data.local.db;

import android.arch.lifecycle.LiveData;
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
    LiveData<List<Medication>> getAllMedications();

    @Query("SELECT * FROM medication WHERE title LIKE :searchQuery" +
            " OR month LIKE :searchQuery")
    LiveData<List<Medication>> searchMedications(String searchQuery);

    @Query("SELECT COUNT (*) FROM medication")
    int getMedicationCount();
}
