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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.User;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.list.MedicationList;
import com.khattabu.med_manager.utils.AppLogger;

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

    @BindView(R.id.button_google_login) SignInButton googleSignIn;

    private GoogleSignInClient client;
    private static final int GOOGLE_SIGN_IN = 41;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getAppComponent().inject(this);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            startApp(accountToUser(account));
            return;
        }

        googleSignIn.setSize(SignInButton.SIZE_STANDARD);
        googleSignIn.setOnClickListener(view ->
                startNextActivityForResult(client.getSignInIntent(), GOOGLE_SIGN_IN));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            handleGoogleIn(task);
        }
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

        startApp(user);
    }

    private void handleGoogleIn(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            startApp(accountToUser(account));
        }catch (ApiException ex){
            showMessage("Unable to sign in with Google, kindly use alternative login.");
            AppLogger.d("Sign in failed with code " + ex.getStatusCode());
        }
    }

    private void startApp(User user){
        repository.saveUser(user);

        Intent intent = new Intent(this, MedicationList.class);
        startNextActivity(intent);
        finish();
    }

    private User accountToUser(GoogleSignInAccount account) {
        User user = new User();
        user.setEmailAddress(account.getEmail());
        user.setFirstName(account.getGivenName());
        user.setLastName(account.getFamilyName());
        return user;
    }
}
