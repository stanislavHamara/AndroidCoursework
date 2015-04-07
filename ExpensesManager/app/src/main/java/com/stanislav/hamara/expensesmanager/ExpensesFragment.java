package com.stanislav.hamara.expensesmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by stan on 07/04/15.
 */
public class ExpensesFragment {

    private View view;
    private String category;
    private String subcategory;
    private String description;
    private String currency;
    private int whole_currency;
    private int small_currency;
    private boolean receipt_retianed;

    public ExpensesFragment(View view){
        this.view = view;
        initExpenses();

    }

    private void initExpenses(){
        //initialize the main spinner
        SpinnerActivity mSpinnerActivity = new SpinnerActivity();
        mSpinnerActivity.initializeMainSpinner(view);
        Button mAddButton = (Button) view.findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign relevant values from UI to variables

                //Main category
                Spinner spinner = (Spinner) view.findViewById(R.id.category_spinner);
                category = spinner.getSelectedItem().toString();

                //subcategory
                spinner = (Spinner) view.findViewById(R.id.subcategory_spinner);
                subcategory = spinner.getSelectedItem().toString();

                //description
                EditText editText = (EditText) view.findViewById(R.id.descriptionText);
                description = editText.getText().toString();

                //retained receipt
                CheckBox cb = (CheckBox) view.findViewById(R.id.retained_receipt);
                receipt_retianed = cb.isChecked();

                //currency
                spinner = (Spinner) view.findViewById(R.id.currency_spinner);
                currency = spinner.getSelectedItem().toString();

                //amount of money
                try {
                    editText = (EditText) view.findViewById(R.id.whole_currency);
                    whole_currency = Integer.parseInt(editText.getText().toString());
                    editText = (EditText) view.findViewById(R.id.small_currency);
                    small_currency = Integer.parseInt(editText.getText().toString());

                    Log.e("Output: ", category + " , " + subcategory + " , " + description + " , " + currency + whole_currency + "." + small_currency);

                } catch (NumberFormatException e){
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Alert")
                            .setMessage("Invalid format. Currency must be a number in range 0-99.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }
}
