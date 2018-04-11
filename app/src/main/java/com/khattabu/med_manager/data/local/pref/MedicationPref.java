package com.khattabu.med_manager.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */
@Singleton
public class MedicationPref {
    private final SharedPreferences preferences;

    private final String email = "emailAddress";
    private final String first = "firstName";
    private final String last = "lastName";

    @Inject
    public MedicationPref(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public User getUser(){
        User user = new User();
        user.setEmailAddress(getEmailAddress());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        return user;
    }

    public void setUser(User user){
        setEmailAddress(user.getEmailAddress());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
    }

    private String getEmailAddress(){
        return preferences.getString(email, "");
    }

    private String getFirstName(){
        return preferences.getString(first, "");
    }

    private String getLastName(){
        return preferences.getString(last, "");
    }

    private void setEmailAddress(String email) {
        preferences.edit().putString(this.email, email).apply();
    }

    private void setFirstName(String firstName){
        preferences.edit().putString(first, firstName).apply();
    }

    private void setLastName(String lastName){
        preferences.edit().putString(last, lastName).apply();
    }
}
