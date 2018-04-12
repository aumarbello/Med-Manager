package com.khattabu.med_manager.presentation.login;

import com.khattabu.med_manager.data.local.pref.MedicationPreference;
import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/11/18.
 */
@Singleton
public class LoginRepository {
    private final MedicationPreference PREF;

    @Inject
    public LoginRepository(MedicationPreference PREF) {
        this.PREF = PREF;
    }

    void saveUser(User user){
        PREF.setUser(user);
    }
}
