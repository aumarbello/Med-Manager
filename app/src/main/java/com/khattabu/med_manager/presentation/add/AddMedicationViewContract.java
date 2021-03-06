package com.khattabu.med_manager.presentation.add;

import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseViewContract;

/**
 * Created by ahmed on 4/15/18.
 */

public interface AddMedicationViewContract extends BaseViewContract {
    void onMedicationAdded(Medication medication);
    void onMedicationUpdated(Medication medication);
}
