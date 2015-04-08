package com.stanislav.hamara.expensesmanager;


import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    private List fragmentList = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab mTab = mActionBar.newTab();
        mTab.setText("Home");
        mTab.setTabListener(this);
        mActionBar.addTab(mTab, 0, true);

        ActionBar.Tab mTab2 = mActionBar.newTab();
        mTab2.setText("Expenses");
        mTab2.setTabListener(this);
        mActionBar.addTab(mTab2, 1 , false);

        ActionBar.Tab mTab3 = mActionBar.newTab();
        mTab3.setText("Summary");
        mTab3.setTabListener(this);
        mActionBar.addTab(mTab3, 2 , false);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        Fragment mFragment = null;
        TabsActivity mTabFragment = null;



        if(fragmentList.size() > tab.getPosition())
            mFragment = (Fragment)fragmentList.get(tab.getPosition());

        if(mFragment == null){
            mTabFragment = new TabsActivity();
            Bundle mBundle = new Bundle();
             //control for different tabs
            switch (tab.getPosition()){
                case 0:
                    mBundle.putInt("tabID", 0);
                    break;
                case 1:
                    mBundle.putInt("tabID", 1);

                    break;
                case 2:
                    mBundle.putInt("tabID", 2);
                    break;
                default:
                    break;
            }

            mTabFragment.setArguments(mBundle);
            fragmentList.add(mTabFragment);
        } else {
            mTabFragment = (TabsActivity) mFragment;

        }

        fragmentTransaction.replace(android.R.id.content, mTabFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        if(fragmentList.size() > tab.getPosition())
            fragmentTransaction.remove((Fragment)fragmentList.get(tab.getPosition()));
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}
