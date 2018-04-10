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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 4/10/18.
 */

class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationHolder>{
    private final List<Medication> medicationList;
    private final MedicationCallBack callBack;

    MedicationAdapter(List<Medication> medicationList, MedicationCallBack callBack) {
        this.medicationList = medicationList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public MedicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.medication_item, parent, false);

        view.setOnClickListener(someView -> {
            Medication medication = (Medication) someView.getTag();
            callBack.openDetailedMedication(medication);
        });

        return new MedicationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationHolder holder, int position) {
        holder.bindMedication(medicationList.get(position));
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    class MedicationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.medication_title)
        TextView medicationTitle;

        @BindView(R.id.medication_desc)
        TextView medicationDesc;

        @BindView(R.id.date_added)
        TextView dateAdded;

        @BindView(R.id.interval)
        TextView interval;

        MedicationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindMedication(Medication medic){
            itemView.setTag(medic);

            medicationTitle.setText(medic.getTitle());

            medicationDesc.setText(medic.getDesc());

            dateAdded.setText(DateUtils.dateLongToString(medic.getDateAdded()));

            String intervalString = String.format(Locale.getDefault(), "%d %s",
                    medic.getIntervalValue(), medic.getIntervalType());
            interval.setText(intervalString);
        }
    }
}
