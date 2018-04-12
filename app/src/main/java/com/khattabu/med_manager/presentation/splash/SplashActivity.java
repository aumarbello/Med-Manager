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
        finish();
    }
}
