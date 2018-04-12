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

package com.khattabu.med_manager.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.add.AddMedicationActivity;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.utils.DateUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 4/10/18.
 */

public class DetailActivity extends BaseActivity {
    @Inject DetailRepository repository;

    @BindView(R.id.text_medication_desc) TextView medicationDesc;

    @BindView(R.id.text_period_active) TextView periodActive;

    @BindView(R.id.text_period_left) TextView periodLeft;

    @BindView(R.id.text_start_date) TextView startDate;

    @BindView(R.id.text_end_date) TextView endDate;

    @BindView(R.id.text_interval) TextView interval;

    public static final String EXTRA_MEDICATION = "com.khattabu.med_manager.EXTRA_MEDICATION";
    private Medication medication;

    public static Intent getStartIntent(Context context, Medication medication){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MEDICATION, medication);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_MEDICATION)){
            setUpView((Medication)intent.getSerializableExtra(EXTRA_MEDICATION));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_edit_medication:
                editMedication();
                return true;
            case R.id.menu_delete_medication:
                repository.deletedRepository(medication);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpView(Medication medication) {
        this.medication = medication;
        setAppTitle(medication.getTitle());

        medicationDesc.setText(medication.getDescription());

        startDate.setText(DateUtils.dateLongToString(medication.getStartDate()));
        endDate.setText(DateUtils.dateLongToString(medication.getEndDate()));

        periodActive.setText(DateUtils.compareDates(medication.getStartDate(),
                Calendar.getInstance().getTimeInMillis()));
        periodLeft.setText(DateUtils.compareDates(Calendar.getInstance().getTimeInMillis(),
                medication.getEndDate()));

        interval.setText(DateUtils.getInterval(medication.getIntervalValue(),
                medication.getIntervalType()));
    }

    private void editMedication(){
        startNextActivity(AddMedicationActivity
                .getStartIntent(this, medication));
    }
}
