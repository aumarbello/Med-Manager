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

package com.khattabu.med_manager.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 4/10/18.
 */

class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationHolder>{
    private final List<Medication> MEDICATION_LIST;
    private final MedicationCallBack CALL_BACK;

    MedicationAdapter(List<Medication> medicationList, MedicationCallBack callBack) {
        MEDICATION_LIST = medicationList;
        CALL_BACK = callBack;
    }

    @NonNull
    @Override
    public MedicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_medication, parent, false);

        view.setOnClickListener(someView -> {
            Medication medication = (Medication) someView.getTag();
            CALL_BACK.openDetailedMedication(medication);
        });

        return new MedicationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationHolder holder, int position) {
        holder.bindMedication(MEDICATION_LIST.get(position));
    }

    @Override
    public int getItemCount() {
        return MEDICATION_LIST.size();
    }

    class MedicationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_medication_title) TextView medicationTitle;

        @BindView(R.id.text_medication_desc) TextView medicationDesc;

        @BindView(R.id.text_date_added) TextView dateAdded;

        @BindView(R.id.text_interval) TextView interval;

        MedicationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindMedication(Medication medic){
            itemView.setTag(medic);

            medicationTitle.setText(medic.getTitle());

            medicationDesc.setText(medic.getDescription());

            dateAdded.setText(DateUtils.dateLongToString(medic.getDateAdded()));

            interval.setText(DateUtils.getInterval(medic.getIntervalValue(),
                    medic.getIntervalType()));
        }
    }
}
