package com.khattabu.med_manager.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.khattabu.med_manager.data.model.Medication;

/**
 * Created by ahmed on 4/10/18.
 */
@Database(entities = Medication.class, version = 1, exportSchema = false)
public abstract class MedicationDatabase extends RoomDatabase {
    private static MedicationDatabase INSTANCE;
    public abstract MedicationDAO medicationDAO();

    public static MedicationDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MedicationDatabase.class, "medication-database")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
