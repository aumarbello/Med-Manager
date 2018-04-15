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
import com.khattabu.med_manager.utils.AppLogger;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 4/10/18.
 */

public class AddMedicationRepository {
    private final MedicationDAO DAO;
    private AddMedicationViewContract viewContract;

    @Inject
    public  AddMedicationRepository(MedicationDAO medicationDAO){
        DAO = medicationDAO;
    }

    void onAttach(AddMedicationViewContract viewContract){
        this.viewContract = viewContract;
    }

    void addMedication(Medication medication){
        Disposable disposable = Single.fromCallable(() -> DAO.insertMedication(medication))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aVoid -> viewContract.onMedicationAdded(),
                        throwable -> {
                            viewContract.onError(null);
                            AppLogger.e("Failed to add Medication", throwable);
                        });

        viewContract.addDisposable(disposable);
    }

    void updateMedication(Medication medication){
        Disposable disposable = Single.fromCallable(() -> DAO.updateMedication(medication))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aVoid -> viewContract.onMedicationUpdated(),
                        throwable -> {
                            viewContract.onError(null);
                            AppLogger.e("Failed to update Medication", throwable);
                        });

        viewContract.addDisposable(disposable);
    }
}
