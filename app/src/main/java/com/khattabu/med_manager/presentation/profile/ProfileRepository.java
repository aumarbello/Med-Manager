package com.khattabu.med_manager.presentation.profile;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.local.pref.MedicationPref;
import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */
@Singleton
public class ProfileRepository {
    private final MedicationPref pref;
    private final MedicationDAO dao;

    @Inject
    ProfileRepository(MedicationPref pref, MedicationDAO dao) {
        this.pref = pref;
        this.dao = dao;
    }

    User getUser(){
        return pref.getUser();
    }

    int getMedicationCount(){
        return dao.getMedicationCount();
    }

    void setUser(User user){
        pref.setUser(user);
    }
}
