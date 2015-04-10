package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Stanislav Hamara on 06/04/15.
 *
 * SpinnerActivity implements spinner functionality and controls mainly the content
 * of the second spinner based on the selection in the first spinner
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int subcategory;
    private View mView;

    public Spinner mCurrencySpinner;
    public Spinner mDescSpinner;
    public Spinner mSubDescSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subcategory = position;
        initializeSecondarySpinner(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void initializeMainSpinner(View view){
        mView = view;
        mDescSpinner = (Spinner) view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDescSpinner.setAdapter(adapter);
        mDescSpinner.setOnItemSelectedListener(this);

        //initialize the subcategory categorySpinner based on what is selected in the first one
        initializeSecondarySpinner(subcategory);
        initializeCurrencySpinner();

    }

    public void initializeSecondarySpinner(int sub){
        mSubDescSpinner = (Spinner) mView.findViewById(R.id.subcategory_spinner);
        ArrayAdapter<CharSequence> adapter;
        mSubDescSpinner.setEnabled(true);
        switch (sub) {
            case 0:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.meal_array, android.R.layout.simple_spinner_item);
                break;
            case 1:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.drink_array, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.transport_array, android.R.layout.simple_spinner_item);
                break;
            case 3:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.accomodation_array, android.R.layout.simple_spinner_item);
                break;
            case 4:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.accomodation_array, android.R.layout.simple_spinner_item);
                mSubDescSpinner.setEnabled(false);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(mView.getContext(),
                        R.array.category_array, android.R.layout.simple_spinner_item);
                break;

        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubDescSpinner.setAdapter(adapter);
    }

    public void initializeCurrencySpinner(){
        mCurrencySpinner = (Spinner) mView.findViewById(R.id.currency_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mView.getContext(),
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCurrencySpinner.setAdapter(adapter);
    }
}
