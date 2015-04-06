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
    private SpinnerActivity mSpinnerActivity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
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
                mSpinnerActivity = new SpinnerActivity();
                mSpinnerActivity.initializeMainSpinner(view);
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
}

