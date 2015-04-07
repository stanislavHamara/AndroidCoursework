package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

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

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(activity,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
