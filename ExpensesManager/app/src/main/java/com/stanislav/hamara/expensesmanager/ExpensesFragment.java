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
                Spinner spinner = (Spinner) view.findViewById(R.id.category_spinner);
                Spinner spinner2 = (Spinner) view.findViewById(R.id.subcategory_spinner);
                EditText editText = (EditText) view.findViewById(R.id.descriptionText);
                CheckBox cb = (CheckBox) view.findViewById(R.id.retained_receipt);
                Spinner spinner3 = (Spinner) view.findViewById(R.id.currency_spinner);
                //amount of money
                try {
                    EditText editText2 = (EditText) view.findViewById(R.id.whole_currency);
                    EditText editText3 = (EditText) view.findViewById(R.id.small_currency);
                    Expense expense = new Expense(spinner.getSelectedItem().toString(),
                            spinner2.getSelectedItem().toString(),
                            editText.getText().toString(),
                            spinner3.getSelectedItem().toString(),
                            Integer.parseInt(editText2.getText().toString()),
                            Integer.parseInt(editText3.getText().toString()),
                            cb.isSelected());

                    Log.e("Output: ", expense.toString());

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
