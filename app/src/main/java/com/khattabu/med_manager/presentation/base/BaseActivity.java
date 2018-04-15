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

package com.khattabu.med_manager.presentation.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.khattabu.med_manager.R;
import com.khattabu.med_manager.di.component.ActivityComponent;
import com.khattabu.med_manager.di.component.DaggerActivityComponent;
import com.khattabu.med_manager.di.modules.ActivityModule;
import com.khattabu.med_manager.presentation.MedManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ahmed on 4/8/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewContract{
    private ActivityComponent component;
    private AlertDialog progressDialog;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .appComponent(((MedManager)getApplication()).getComponent())
                .build();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();

        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, android.R.anim.fade_out);
    }

    @Override
    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onError(@Nullable String msg) {
        msg = msg == null ? "An error occurred please try again"
                : msg;
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT);

        hideLoadingState();
        snackbar.show();
    }

    protected void showLoadingState(@Nullable String text){
        View view = View.inflate(this, R.layout.dialog_progress, null);

        if (text != null){
            TextView title = view.findViewById(R.id.text_title);
            title.setText(text);
        }

        progressDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        progressDialog.show();
    }

    protected void hideLoadingState(){
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    protected void showMessage(int message) {
        String msg = getString(message);
        showMessage(msg);
    }


    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setAppTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(title);
        }
    }

    public void startNextActivity(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, android.R.anim.fade_out);
    }

    protected void startNextActivityForResult(Intent intent, int requestCode){
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.left_in, android.R.anim.fade_out);
    }

    protected void shouldShowBackButton(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected ActivityComponent getAppComponent(){
        return component;
    }
}
