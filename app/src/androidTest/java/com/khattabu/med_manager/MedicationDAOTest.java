package com.khattabu.med_manager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.khattabu.med_manager.data.local.db.MedicationDatabase;
import com.khattabu.med_manager.data.model.Medication;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ahmed on 4/18/18.
 */
@RunWith(AndroidJUnit4.class)
public class MedicationDAOTest {
    @Rule
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    private MedicationDatabase medicationDatabase;

    private Medication medication = new Medication(
            "Panadol", "Pain Relief", "May",
            1524069715, 1524069715, 1524242514,
            2, "Days"
    );

    @Before
    public void createDb() throws Exception{
        Context context = InstrumentationRegistry.getContext();

        medicationDatabase = Room.inMemoryDatabaseBuilder
                (context, MedicationDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @Test
    public void getMedicationsFromEmptyDb(){
        medicationDatabase.medicationDAO()
                .getAllMedications()
                .test()
                .assertValue(medications -> medications.size() == 0);

    }

    @Test
    public void addAndConfirmCount(){
        medicationDatabase.medicationDAO().insertMedication(medication);

        assertEquals(1, medicationDatabase.medicationDAO().getMedicationCount());
    }

    @Test
    public void insertAndRetrieve(){
        medicationDatabase.medicationDAO().insertMedication(medication);

        medicationDatabase.medicationDAO()
                .getAllMedications()
                .test()
                .assertValue(medications -> medications.size() == 1
                        && medications.get(0).getTitle().equals(this.medication.getTitle()));
    }

    @Test
    public void updateAndRetrieve(){
        String change = "Apache";
        medicationDatabase.medicationDAO().insertMedication(medication);
        medication.setTitle(change);
        medicationDatabase.medicationDAO().updateMedication(medication);

        medicationDatabase.medicationDAO()
                .getAllMedications()
                .test()
                .assertValue(medications -> medications.size() == 1
                        && medications.get(0).getTitle().equals(change));
    }

    @Test
    public void addAndDelete(){
        medicationDatabase.medicationDAO().insertMedication(medication);
        medicationDatabase.medicationDAO().deleteMedication(medication);

        medicationDatabase.medicationDAO()
                .getAllMedications()
                .test()
                .assertValue(medications -> medications.size() == 0);
    }

    @After
    public void closeDb() throws Exception{
        medicationDatabase.close();
    }
}
