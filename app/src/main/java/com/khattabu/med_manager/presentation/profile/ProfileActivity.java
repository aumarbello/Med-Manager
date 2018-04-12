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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.User;
import com.khattabu.med_manager.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/10/18.
 */

public class ProfileActivity extends BaseActivity {
    @Inject ProfileRepository repository;

    @BindView(R.id.edit_first_name) TextInputEditText firstName;

    @BindView(R.id.edit_last_name) TextInputEditText lastName;

    @BindView(R.id.edit_email) TextInputEditText emailAddress;

    @BindView(R.id.text_total_medications) TextView totalMedications;

    @BindView(R.id.text_highest_medication) TextView highestMedication;

    @BindView(R.id.text_next_medications) TextView nextMedication;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setUp();
    }

    @OnClick(R.id.button_save_changes)
    public void saveChanges(){

    }

    private void setUp() {
        User user = repository.getUser();

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        emailAddress.setText(user.getEmailAddress());

        totalMedications.setText(repository.getMedicationCount());
    }
}
