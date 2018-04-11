package com.khattabu.med_manager.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.utils.DateUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 4/10/18.
 */

public class DetailActivity extends BaseActivity {
    @Inject
    DetailRepository repository;

    @BindView(R.id.medication_desc)
    TextView medicationDesc;

    @BindView(R.id.period_active)
    TextView periodActive;

    @BindView(R.id.period_left)
    TextView periodLeft;

    @BindView(R.id.start_date)
    TextView startDate;

    @BindView(R.id.end_date)
    TextView endDate;

    @BindView(R.id.interval)
    TextView interval;

    public static final String EXTRA = "medication_extra";
    private Medication medication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.medication_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA)){
            setUpView((Medication)intent.getSerializableExtra(EXTRA));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_medication:
                editMedication();
                return true;
            case R.id.delete_medication:
                repository.deletedRepository(medication);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpView(Medication medication) {
        this.medication = medication;
        setAppTitle(medication.getTitle());

        medicationDesc.setText(medication.getDesc());

        startDate.setText(DateUtils.dateLongToString(medication.getStartDate()));
        endDate.setText(DateUtils.dateLongToString(medication.getEndDate()));

        periodActive.setText(DateUtils.compareDates(medication.getStartDate(),
                Calendar.getInstance().getTimeInMillis()));
        periodLeft.setText(DateUtils.compareDates(Calendar.getInstance().getTimeInMillis(),
                medication.getEndDate()));

        interval.setText(DateUtils.getInterval(medication.getIntervalValue(),
                medication.getIntervalType()));
    }

    private void editMedication(){

    }
}
