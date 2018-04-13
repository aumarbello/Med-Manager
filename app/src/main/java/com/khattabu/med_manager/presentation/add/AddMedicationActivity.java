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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/10/18.
 */

public class AddMedicationActivity extends BaseActivity {
    @Inject AddMedicationRepository repository;

    @BindView(R.id.edit_title) TextInputEditText title;

    @BindView(R.id.edit_desc) TextInputEditText desc;

    @BindView(R.id.text_start_date) TextView startDate;

    @BindView(R.id.text_end_date) TextView endDate;

    public static Intent getStartIntent(Context context, Medication medication){
        Intent intent = new Intent(context, AddMedicationActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MEDICATION, medication);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_medication);
        ButterKnife.bind(this);
        getAppComponent().inject(this);
    }

    @OnClick(R.id.text_select_date)
    public void selectDate(){

    }

    @OnClick(R.id.fab_add_medication)
    public void addMedication(){

    }
}
