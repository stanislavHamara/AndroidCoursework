package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by stan on 06/04/15.
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public int subcategory;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subcategory = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
