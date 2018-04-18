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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;
import com.khattabu.med_manager.presentation.list.MedicationAdapter;
import com.khattabu.med_manager.presentation.list.MedicationCallBack;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 4/18/18.
 */

public class SearchActivity extends BaseActivity
        implements SearchViewContract, MedicationCallBack {

    @Inject SearchRepository repository;

    @BindView(R.id.recycler_view_medication) RecyclerView medicationList;

    @BindView(R.id.fab_add_medication) FloatingActionButton addMedication;

    private MedicationAdapter adapter;
    private static final String EXTRA_SEARCH = "com.khattabu.med_manager.EXTRA_SEARCH";

    public static Intent getStartIntent(Context context, String query){
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_SEARCH, query);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_list);
        ButterKnife.bind(this);
        getAppComponent().inject(this);
        repository.onAttach(this);

        adapter = new MedicationAdapter(new ArrayList<>(), this);
        medicationList.setLayoutManager(new LinearLayoutManager(this));
        medicationList.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_SEARCH)){
            String searchQuery = intent.getStringExtra(EXTRA_SEARCH);
            showLoadingState("Searching for " + searchQuery + "...");
            repository.searchMedications(searchQuery);
        }

        shouldShowBackButton();
        setAppTitle("Search Results");

        addMedication.setVisibility(View.GONE);
    }

    @Override
    public void onSearchComplete(List<Medication> medicationList) {
        hideLoadingState();
        if (medicationList.isEmpty()){
            onError("Search Parameter does not match any medication");
            return;
        }
        adapter.setList(medicationList);
    }

    @Override
    public void openDetailedMedication(Medication medication) {
        Intent intent = DetailActivity.getStartIntent(this, medication);
        startNextActivity(intent);
    }
}
