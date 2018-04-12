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

package com.khattabu.med_manager.presentation.profile;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.data.local.pref.MedicationPreference;
import com.khattabu.med_manager.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ahmed on 4/10/18.
 */
@Singleton
public class ProfileRepository {
    private final MedicationPreference PREF;
    private final MedicationDAO DAO;

    @Inject
    ProfileRepository(MedicationPreference PREF, MedicationDAO DAO) {
        this.PREF = PREF;
        this.DAO = DAO;
    }

    User getUser(){
        return PREF.getUser();
    }

    int getMedicationCount(){
        return DAO.getMedicationCount();
    }

    void setUser(User user){
        PREF.setUser(user);
    }
}
