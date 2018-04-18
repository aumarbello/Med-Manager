/*
 * Copyright 2018 Ahmed, Umar Bello.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.khattabu.med_manager.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;

/**
 * Created by ahmed on 4/10/18.
 */

public class MedicationPreference {
    private final SharedPreferences PREFERENCES;

    private static final String PREF_EMAIL = "emailAddress";
    private static final String PREF_FIRST_NAME = "firstName";
    private static final String PREF_LAST_NAME = "lastName";

    @Inject
    public MedicationPreference(Context context){
        PREFERENCES = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public User getUser(){
        User user = new User();
        user.setEmailAddress(getEmailAddress());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        return user;
    }

    public void setUser(User user){
        if (user == null){
            setEmailAddress(null);
            setFirstName(null);
            setLastName(null);
            return;
        }
        setEmailAddress(user.getEmailAddress());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
    }

    private String getEmailAddress(){
        return PREFERENCES.getString(PREF_EMAIL, "");
    }

    private String getFirstName(){
        return PREFERENCES.getString(PREF_FIRST_NAME, "");
    }

    private String getLastName(){
        return PREFERENCES.getString(PREF_LAST_NAME, "");
    }

    private void setEmailAddress(String email) {
        PREFERENCES.edit().putString(PREF_EMAIL, email).apply();
    }

    private void setFirstName(String firstName){
        PREFERENCES.edit().putString(PREF_FIRST_NAME, firstName).apply();
    }

    private void setLastName(String lastName){
        PREFERENCES.edit().putString(PREF_LAST_NAME, lastName).apply();
    }
}
