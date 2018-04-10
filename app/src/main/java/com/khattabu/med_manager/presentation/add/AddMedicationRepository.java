package com.khattabu.med_manager.presentation.add;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.model.Medication;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */

@Singleton
public class AddMedicationRepository {
    private final MedicationDAO dao;

    @Inject
    public  AddMedicationRepository(MedicationDAO dao){
        this.dao = dao;
    }

    void addMedication(Medication medication){
        dao.insertMedication(medication);
    }

    void updateMedication(Medication medication){
        dao.updateMedication(medication);

    }
}
