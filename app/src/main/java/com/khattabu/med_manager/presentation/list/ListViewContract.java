package com.khattabu.med_manager.presentation.list;

import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseViewContract;

import java.util.List;

/**
 * Created by ahmed on 4/15/18.
 */

public interface ListViewContract extends BaseViewContract {
    void setMedicationList(List<Medication> medicationList);
}
