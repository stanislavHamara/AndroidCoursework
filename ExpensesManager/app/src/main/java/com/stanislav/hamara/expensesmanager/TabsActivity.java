package com.stanislav.hamara.expensesmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by stan on 06/04/15.
 */
public class TabsActivity extends Fragment {

    private int mTabID;
    private ExpenseDataSource mDatasource;
    private Button deleteButton;

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
                deleteButton = (Button) view.findViewById(R.id.delete_button);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatasource.close();
                        mDatasource.deleteDatabase();
                        mDatasource = new ExpenseDataSource(getActivity().getBaseContext());
                        mDatasource.open();
                    }
                });
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_tab_expenses, null);
                ExpensesFragment expenses = new ExpensesFragment(view, mDatasource);
                break;

            case 2:
                view = inflater.inflate(R.layout.fragment_tab_summary, null);
                SummaryFragment summary = new SummaryFragment(view, getActivity(), mDatasource);
                break;

            default:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
        }
        return view;
    }
}

