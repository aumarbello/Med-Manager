package com.khattabu.med_manager.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.widget.EditText;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.User;
import com.khattabu.med_manager.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/8/18.
 */

public class LoginActivity extends BaseActivity {
    @Inject
    LoginRepository repository;

    @BindView(R.id.email_address)
    EditText emailAddress;

    @BindView(R.id.password)
    EditText userPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
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
