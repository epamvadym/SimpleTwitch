package com.vadym_horiainov.simpletwitch.mvvm.base;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.reactivex.disposables.CompositeDisposable;

public abstract class ActivityViewModel extends ViewModel {
    protected static final String TAG = "ActivityViewModel";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
    }



    /**
     * Activity lifecycle
     */
    public boolean onBackKeyPress() {
        return false;
    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onDestroy() {
        //realm.close();
    }

    public void onPause() {

    }

    public void onResume() {

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }

    public void onOptionsItemSelected(MenuItem item) {

    }

    public void onConfigurationChanged(Configuration newConfig) {

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onCreateOptionsMenu(Menu menu) {

    }

    public void onPrepareOptionsMenu(Menu menu) {

    }

    public void onWindowFocusChanged(boolean hasFocus) {

    }

}