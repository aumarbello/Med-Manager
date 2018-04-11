package com.khattabu.med_manager.presentation.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.add.AddMedicationActivity;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/11/18.
 */

public class MedicationList extends BaseActivity
        implements MedicationCallBack{
    @Inject
    ListRepository repository;

    @BindView(R.id.medication_list)
    RecyclerView medicationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_activity);
        ButterKnife.bind(this);
        MedicationAdapter adapter = new MedicationAdapter(
                repository.getAllMedication(), this);
        medicationList.setAdapter(adapter);
        medicationList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void openDetailedMedication(Medication medication) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA, medication);
    }

    @OnClick(R.id.add_medication)
    public void addMedication(){
        Intent intent = new Intent(this, AddMedicationActivity.class);
        startNextActivity(intent);
    }
}
