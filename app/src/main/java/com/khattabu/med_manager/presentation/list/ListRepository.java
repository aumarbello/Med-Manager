package com.khattabu.med_manager.presentation.list;

import android.content.Context;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.local.db.MedicationDatabase;
import com.khattabu.med_manager.data.model.Medication;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */

@Singleton
public class ListRepository {
    private final MedicationDAO dao;

    @Inject
    public ListRepository(Context context) {
        dao = MedicationDatabase.getDatabase(context).medicationDAO();
    }

    List<Medication> getAllMedication(){
        return dao.getAllMedications().getValue();
    }
}
