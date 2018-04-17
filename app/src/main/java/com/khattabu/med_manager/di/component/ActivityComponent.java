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

package com.khattabu.med_manager.di.component;

import com.khattabu.med_manager.di.PerActivity;
import com.khattabu.med_manager.di.modules.ActivityModule;
import com.khattabu.med_manager.presentation.add.AddMedicationActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;
import com.khattabu.med_manager.presentation.list.MedicationList;
import com.khattabu.med_manager.presentation.login.LoginActivity;
import com.khattabu.med_manager.presentation.profile.ProfileActivity;
import com.khattabu.med_manager.utils.NotificationService;

import dagger.Component;

/**
 * Created by ahmed on 4/13/18.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity activity);
    void inject(MedicationList medicationList);
    void inject(DetailActivity detailActivity);
    void inject(ProfileActivity profileActivity);
    void inject(AddMedicationActivity addMedicationActivity);
    void inject(NotificationService service);
}
