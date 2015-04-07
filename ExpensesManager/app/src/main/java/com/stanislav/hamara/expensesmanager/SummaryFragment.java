package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * Created by stan on 07/04/15.
 */
public class SummaryFragment {

    private Activity activity;
    ExpenseDataSource mDatasource;
    public final ListView listView;

    public SummaryFragment(View view, Activity activity, ExpenseDataSource mDatasource){

        this.activity = activity;
        listView  = (ListView) view.findViewById(R.id.purchases_list);
        this.mDatasource = mDatasource;
        initSummary();
    }

    private void initSummary(){

        List<Expense> values = mDatasource.getAllTasks();

        // Use an ArrayAdapter to bind to the elements in the ListView
        ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(activity,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);

    }
}
