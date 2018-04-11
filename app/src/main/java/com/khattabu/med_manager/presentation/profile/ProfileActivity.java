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
    @Inject
    ProfileRepository repository;

    @BindView(R.id.first_name)
    TextInputEditText firstName;

    @BindView(R.id.last_name)
    TextInputEditText lastName;

    @BindView(R.id.user_email)
    TextInputEditText emailAddress;

    @BindView(R.id.total_medications)
    TextView totalMedications;

    @BindView(R.id.highest_medication)
    TextView highestMedication;

    @BindView(R.id.next_medications)
    TextView nextMedication;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);

        setUp();
    }

    @OnClick(R.id.save_changes)
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
