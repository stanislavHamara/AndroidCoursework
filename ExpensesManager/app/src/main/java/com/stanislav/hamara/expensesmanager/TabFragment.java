package com.stanislav.hamara.expensesmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Stanislav Hamara on 06/04/15.
 *
 * TabFragment controlls the tab functionality and switches to the relevant layout based on
 * users choice
 */
public class TabFragment extends Fragment {

    private int mTabID;
    private ExpenseDataSource mDatasource;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        mTabID = mBundle.getInt("tabID");
        mDatasource = new ExpenseDataSource(getActivity().getBaseContext());
        mDatasource.open();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        switch (mTabID){
            case 0:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                HomeController home = new HomeController(view, mDatasource);

                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_tab_expenses, null);
                ExpensesController expenses = new ExpensesController(view, mDatasource);
                break;

            case 2:
                view = inflater.inflate(R.layout.fragment_tab_summary, null);
                SummaryController summary = new SummaryController(view, getActivity(), mDatasource);
                break;

            default:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
        }
        return view;
    }
}

