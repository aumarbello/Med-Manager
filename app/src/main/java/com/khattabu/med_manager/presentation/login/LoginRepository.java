package com.khattabu.med_manager.presentation.login;

import com.khattabu.med_manager.data.local.pref.MedicationPref;
import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/11/18.
 */
@Singleton
public class LoginRepository {
    private final MedicationPref pref;

    @Inject
    public LoginRepository(MedicationPref pref) {
        this.pref = pref;
    }

    void saveUser(User user){
        pref.setUser(user);
    }
}
