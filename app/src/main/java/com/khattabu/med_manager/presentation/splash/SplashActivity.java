package com.khattabu.med_manager.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.login.LoginActivity;

/**
 * Created by ahmed on 4/8/18.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent loginActivity = new Intent(this, LoginActivity.class);
        startNextActivity(loginActivity);
    }
}
