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

package com.khattabu.med_manager.presentation.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseActivity;
import com.khattabu.med_manager.presentation.detail.DetailActivity;
import com.khattabu.med_manager.utils.DateUtils;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed on 4/10/18.
 */

public class AddMedicationActivity extends BaseActivity
        implements DatePickerDialog.OnDateSetListener {

    @Inject AddMedicationRepository repository;

    @BindView(R.id.edit_title) TextInputEditText title;

    @BindView(R.id.edit_desc) TextInputEditText desc;

    @BindView(R.id.text_start_date) TextView startDate;

    @BindView(R.id.text_end_date) TextView endDate;

    @BindView(R.id.edit_value) EditText intervalValue;

    @BindView(R.id.spinner_interval) Spinner intervalSpinner;

    private DatePickerDialog datePicker;
    private TextView textView;

    public static Intent getStartIntent(Context context, Medication medication){
        Intent intent = new Intent(context, AddMedicationActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MEDICATION, medication);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_medication);
        ButterKnife.bind(this);
        getAppComponent().inject(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);

        if (textView != null){
            textView.setText(dateFormat.format(selectedDate.getTime()));
        }
    }

    @OnClick(R.id.text_start_date)
    public void selectStartDate(){
        showDateDialog(startDate);
    }

    @OnClick(R.id.text_end_date)
    public void selectEndDate(){
        showDateDialog(endDate);
    }

    @OnClick(R.id.button_add_medication)
    public void addMedication(){
        if (validateInput()){
            String medicTitle = title.getText().toString();
            String medicDesc = desc.getText().toString();

            long startDateLong = DateUtils.dateStringToLong(startDate.getText().toString());
            long endDateLong = DateUtils.dateStringToLong(endDate.getText().toString());

            Medication medication = new Medication(
                    medicTitle, DateUtils.getMonthString(startDateLong),
                    medicDesc, Calendar.getInstance().getTimeInMillis(),
                    startDateLong, endDateLong, Integer.valueOf(intervalValue.getText().toString()),
                    intervalSpinner.getSelectedItem().toString()
            );

            repository.addMedication(medication);
        }
    }

    private void showDateDialog(TextView textView) {
        this.textView = textView;
        if (datePicker != null && datePicker.isShowing())
            return;

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePicker = new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(this)
                .defaultDate(year, month, day)
                .build();

        datePicker.show();
    }

    private boolean validateInput(){
        if (title.getText().toString().trim().isEmpty()){
            showMessage("Medication title can't be empty.");
            return false;
        }

        if (desc.getText().toString().trim().isEmpty()){
            showMessage("Medication Description can't be empty.");
            return false;
        }

        if (startDate.getText().equals(getString(R.string.add_start_date))){
            showMessage("Start Date not selected.");
            return false;
        }

        if (endDate.getText().equals(getString(R.string.add_end_date))){
            showMessage("End Date not selected.");
            return false;
        }

        if (intervalValue.getText().toString().isEmpty()){
            showMessage("Medication interval not inputted");
            return false;
        }

        return true;
    }
}
