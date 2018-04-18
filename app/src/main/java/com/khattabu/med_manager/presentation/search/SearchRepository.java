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

package com.khattabu.med_manager.presentation.search;

import com.khattabu.med_manager.data.local.db.MedicationDAO;
import com.khattabu.med_manager.utils.AppLogger;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 4/18/18.
 */

public class SearchRepository {
    private final MedicationDAO DAO;
    private SearchViewContract viewContract;

    @Inject
    public SearchRepository(MedicationDAO DAO){
        this.DAO = DAO;
    }

    void onAttach(SearchViewContract viewContract){
        this.viewContract = viewContract;
    }

    void searchMedications(String query){
        Disposable disposable = DAO.searchMedications(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(medications -> {
                    if (medications == null)
                        medications = new ArrayList<>();
                    viewContract.onSearchComplete(medications);
                }, throwable -> {
                    viewContract.onError("Search could not be completed, kindly try again");
                    AppLogger.e(throwable);
                });

        viewContract.addDisposable(disposable);
    }
}
