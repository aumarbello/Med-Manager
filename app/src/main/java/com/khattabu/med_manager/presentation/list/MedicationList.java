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

package com.khattabu.med_manager.presentation.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.data.model.MedicationStatus;
import com.khattabu.med_manager.presentation.add.AddMedicationActivity;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;
import com.khattabu.med_manager.presentation.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/11/18.
 */

public class MedicationList extends BaseActivity
        implements MedicationCallBack, ListViewContract{

    @Inject ListRepository repository;

    @BindView(R.id.recycler_view_medication) RecyclerView medicationList;

    private MedicationAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medication_list);
        ButterKnife.bind(this);
        getAppComponent().inject(this);
        repository.onAttach(this);
        repository.getAllMedication();

        adapter = new MedicationAdapter(
                new ArrayList<>(), this);
        medicationList.setAdapter(adapter);
        medicationList.setLayoutManager(new LinearLayoutManager(this));
        setAppTitle("Home");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_medication_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_open_profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startNextActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openDetailedMedication(Medication medication) {
        startNextActivity(DetailActivity.getStartIntent(this, medication));
    }

    @Override
    public void setMedicationList(List<Medication> medicationList) {
        adapter.setList(medicationList);
    }

    @Override
    public void onMedicationChanged(Medication medication, MedicationStatus status) {

    }

    @OnClick(R.id.fab_add_medication)
    public void addMedication(){
        Intent intent = new Intent(this, AddMedicationActivity.class);
        startNextActivity(intent);
    }
}
