package com.khattabu.med_manager.presentation.detail;

import com.khattabu.med_manager.data.model.Medication;
import com.khattabu.med_manager.presentation.base.BaseViewContract;

/**
 * Created by ahmed on 4/15/18.
 */

public interface DetailViewContract extends BaseViewContract {
    void onMedicationDeleted(Medication medication);
}
