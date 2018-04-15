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
import com.khattabu.med_manager.utils.AppLogger;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 4/10/18.
 */

public class ProfileRepository {
    private final MedicationPreference PREF;
    private final MedicationDAO DAO;
    private ProfileViewContract viewContract;

    @Inject
    ProfileRepository(MedicationPreference PREF, MedicationDAO DAO) {
        this.PREF = PREF;
        this.DAO = DAO;
    }

    void onAttach(ProfileViewContract viewContract){
        this.viewContract = viewContract;
    }

    User getUser(){
        return PREF.getUser();
    }

    void getMedicationCount(){
        Disposable disposable = Single.fromCallable(DAO::getMedicationCount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(integer -> viewContract.setMedicationCount(integer),
                        throwable -> {
                            viewContract.onError(null);
                            AppLogger.e(throwable);
                        });

        viewContract.addDisposable(disposable);
    }

    void setUser(User user){
        PREF.setUser(user);
    }
}
