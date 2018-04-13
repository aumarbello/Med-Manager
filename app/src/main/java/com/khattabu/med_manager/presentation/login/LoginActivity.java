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

package com.khattabu.med_manager.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.widget.EditText;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.User;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.list.MedicationList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/8/18.
 */

public class LoginActivity extends BaseActivity {
    @Inject LoginRepository repository;

    @BindView(R.id.edit_email_address) EditText emailAddress;

    @BindView(R.id.edit_password) EditText userPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getAppComponent().inject(this);
    }

    @OnClick(R.id.button_login)
    public void logUserIn(){
        String email = emailAddress.getText().toString();
        String password = userPassword.getText().toString();
        if (email.trim().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
                password.isEmpty()){
            showMessage("Invalid input");
            return;
        }

        User user = new User();
        user.setEmailAddress(emailAddress.getText().toString());


        repository.saveUser(user);

        Intent intent = new Intent(this, MedicationList.class);
        startNextActivity(intent);
    }
}
