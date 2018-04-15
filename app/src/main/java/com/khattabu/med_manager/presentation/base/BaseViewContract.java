package com.khattabu.med_manager.presentation.base;

import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;

/**
 * Created by ahmed on 4/15/18.
 */

public interface BaseViewContract {
    void addDisposable(Disposable disposable);
    void onError(@Nullable String msg);
}
