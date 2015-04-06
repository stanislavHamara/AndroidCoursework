package com.stanislav.hamara.expensesmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by stan on 06/04/15.
 */
public class TabFragment extends Fragment {

    private int mTabID;
    private Spinner mDescSpinner;
    private Spinner mSubDescSpinner;
    private SpinnerActivity mSpinnerActivity = new SpinnerActivity();
    private int subcategory;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        subcategory = mSpinnerActivity.subcategory;
        mTabID = mBundle.getInt("tabID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        switch (mTabID){
            case 0:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_tab_expenses, null);
                //initialize the main spinner
                initializeMainSpinner(view);
                //initialize the subcategory spinner based on what is selected in the first one
                initializeSecondarySpinner(view, subcategory);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_tab_summary, null);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
        }
        return view;
    }

    public void initializeMainSpinner(View view){
        mDescSpinner = (Spinner) view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDescSpinner.setAdapter(adapter);
        mDescSpinner.setOnItemSelectedListener(mSpinnerActivity);
    }

    public void initializeSecondarySpinner(View view, int sub){
        mSubDescSpinner = (Spinner) view.findViewById(R.id.subcategory_spinner);
        ArrayAdapter<CharSequence> adapter;
        switch (sub) {
            case 0:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                    R.array.meal_array, android.R.layout.simple_spinner_item);
                break;
            case 1:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.drink_array, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.transport_array, android.R.layout.simple_spinner_item);
                break;
            case 3:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.accomodation_array, android.R.layout.simple_spinner_item);
                break;
            case 4:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.accomodation_array, android.R.layout.simple_spinner_item);
                mSubDescSpinner.setEnabled(false);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.category_array, android.R.layout.simple_spinner_item);
                break;

        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubDescSpinner.setAdapter(adapter);
    }
}

