package com.stanislav.hamara.expensesmanager;

import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    List fragmentList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab mTab = mActionBar.newTab();
        mTab.setText("Home");
        mTab.setTabListener(this);
        mActionBar.addTab(mTab);

        ActionBar.Tab mTab2 = mActionBar.newTab();
        mTab2.setText("Expenses");
        mTab2.setTabListener(this);
        mActionBar.addTab(mTab2);

        ActionBar.Tab mTab3 = mActionBar.newTab();
        mTab3.setText("Summary");
        mTab3.setTabListener(this);
        mActionBar.addTab(mTab3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        Fragment mFragment = null;
        TabFragment mTabFragment = null;

        if(fragmentList.size() > tab.getPosition())
            mFragment = (Fragment)fragmentList.get(tab.getPosition());

        if(mFragment == null){
            
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}
