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
    private final MedicationDAO DAO;

    @Inject
    public  AddMedicationRepository(MedicationDAO DAO){
        this.DAO = DAO;
    }

    void addMedication(Medication medication){
        DAO.insertMedication(medication);
    }

    void updateMedication(Medication medication){
        DAO.updateMedication(medication);

    }
}
