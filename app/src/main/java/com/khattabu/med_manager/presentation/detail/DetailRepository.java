package com.khattabu.med_manager.presentation.detail;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.model.Medication;

import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */
@Singleton
public class DetailRepository{
    private final MedicationDAO dao;

    public DetailRepository(MedicationDAO dao) {
        this.dao = dao;
    }

    void deletedRepository(Medication medication){
        dao.deleteMedication(medication);
    }
}
