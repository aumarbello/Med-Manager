package com.khattabu.med_manager.presentation.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/10/18.
 */

public class AddMedicationActivity extends BaseActivity {
    @Inject
    AddMedicationRepository repository;

    @BindView(R.id.title)
    TextInputEditText title;

    @BindView(R.id.desc)
    TextInputEditText desc;

    @BindView(R.id.start_date)
    TextView startDate;

    @BindView(R.id.end_date)
    TextView endDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_medication);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.select_date)
    public void selectDate(){

    }

    @OnClick(R.id.add_medication)
    public void addMedication(){

    }
}
