package com.khattabu.med_manager.presentation.list;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
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
    public ListRepository(MedicationDAO dao) {
        this.dao = dao;
    }

    List<Medication> getAllMedication(){
        return dao.getAllMedications().getValue();
    }
}
