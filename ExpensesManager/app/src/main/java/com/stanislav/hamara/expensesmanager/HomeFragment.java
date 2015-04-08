package com.stanislav.hamara.expensesmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by stan on 08/04/15.
 */
public class HomeFragment {

    private static String PREFS_NAME = "my_preferences";
    ExpenseDataSource mDatasource;
    View view;

    TextView currentJourney;

    public HomeFragment(View view, ExpenseDataSource mDatasource){
        this.mDatasource = mDatasource;
        this.view = view;
        initHome();
    }

    public void initHome(){
        Button deleteButton = (Button) view.findViewById(R.id.delete_button);
        Button addButton = (Button) view.findViewById(R.id.add_button);
        final EditText journeyName = (EditText) view.findViewById(R.id.journeyName);
        currentJourney = (TextView) view.findViewById(R.id.current_display);

        //get the name of the journey
        SharedPreferences prefs = view.getContext().getSharedPreferences(PREFS_NAME, 0);
        String name = prefs.getString("name", "None");
        currentJourney.setText(name);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Delete Journey")
                        .setMessage("You are about to delete your journey. There might be some unclaimed expenses. Are you sure you want to continue?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatasource.close();
                                mDatasource.deleteDatabase();
                                mDatasource = new ExpenseDataSource(view.getContext());
                                mDatasource.open();
                                Toast.makeText(view.getContext(), "Journey Deleted",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(view.getContext(), "Deletion canceled",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = view.getContext().getSharedPreferences(PREFS_NAME, 0).edit();
                if(journeyName.getText().toString() != "")
                    editor.putString("name", journeyName.getText().toString());

                editor.commit();

                SharedPreferences prefs = view.getContext().getSharedPreferences(PREFS_NAME, 0);
                String name = prefs.getString("name", "None");
                currentJourney.setText(name);

            }
        });
    }
}
