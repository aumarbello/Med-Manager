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
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.add.AddMedicationActivity;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;
import com.khattabu.med_manager.presentation.login.LoginActivity;
import com.khattabu.med_manager.presentation.profile.ProfileActivity;
import com.khattabu.med_manager.presentation.search.SearchActivity;

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

    @BindView(R.id.empty_view) ConstraintLayout emptyView;

    private MedicationAdapter adapter;
    private GoogleSignInClient client;

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

        GoogleSignInOptions options = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_medication_list, menu);

        MenuItem item = menu.findItem(R.id.menu_search_medication);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint(getString(R.string.title_search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = SearchActivity.getStartIntent(MedicationList.this, query);
                startNextActivity(intent);

                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);

                item.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_open_profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startNextActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.menu_sign_out){
            Intent intent = new Intent(this, LoginActivity.class);
            invalidateUser();
            startNextActivity(intent);
            finish();
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
        if (medicationList.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
            return;
        }

        emptyView.setVisibility(View.GONE);
        adapter.setList(medicationList);
    }

    @OnClick(R.id.fab_add_medication)
    public void addMedication(){
        Intent intent = new Intent(this, AddMedicationActivity.class);
        startNextActivity(intent);
    }

    private void invalidateUser() {
        client.signOut();
        repository.invalidateUser();
    }
}
