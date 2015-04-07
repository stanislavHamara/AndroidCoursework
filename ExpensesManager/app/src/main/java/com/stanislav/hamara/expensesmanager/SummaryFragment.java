package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;



/**
 * Created by stan on 07/04/15.
 */
public class SummaryFragment {
    private View view;
    private Activity activity;

    public SummaryFragment(View view, Activity activity){
        this.view = view;
        this.activity = activity;
        initSummary();

    }

    private void initSummary(){
        final ListView listView = (ListView) view.findViewById(R.id.purchases_list);


    }
}
