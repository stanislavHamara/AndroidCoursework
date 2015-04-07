package com.stanislav.hamara.expensesmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by stan on 07/04/15.
 */
public class ExpensesFragment {

    private View view;
    private ExpenseDataSource mDatasource;

    Spinner categorySpinner;
    Spinner subcategorySpinner;
    EditText descriptionEditText;
    CheckBox cb;
    Spinner currencySpinner;
    EditText wholeCurrencyET;
    EditText smallCurrencyET;


    public ExpensesFragment(View view, ExpenseDataSource mDatasource){
        this.view = view;
        this.mDatasource = mDatasource;
        initExpenses();

    }

    private void initExpenses(){
        //initialize the main categorySpinner
        SpinnerActivity mSpinnerActivity = new SpinnerActivity();
        mSpinnerActivity.initializeMainSpinner(view);
        Button mAddButton = (Button) view.findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign relevant values from UI to variables
                categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
                subcategorySpinner = (Spinner) view.findViewById(R.id.subcategory_spinner);
                descriptionEditText = (EditText) view.findViewById(R.id.descriptionText);
                cb = (CheckBox) view.findViewById(R.id.retained_receipt);
                currencySpinner = (Spinner) view.findViewById(R.id.currency_spinner);
                //amount of money
                try {
                    wholeCurrencyET = (EditText) view.findViewById(R.id.whole_currency);
                    smallCurrencyET = (EditText) view.findViewById(R.id.small_currency);

                    int wCurrency = Integer.parseInt(wholeCurrencyET.getText().toString());
                    int sCurrency = Integer.parseInt(smallCurrencyET.getText().toString());

                    if(wCurrency > 999 || sCurrency > 99 || descriptionEditText.getText().toString().length() == 0)
                        throw new NumberFormatException();

                    //add to database
                    mDatasource.createExpense(categorySpinner.getSelectedItem().toString(),
                            subcategorySpinner.getSelectedItem().toString(),
                            descriptionEditText.getText().toString(),
                            currencySpinner.getSelectedItem().toString(),
                            wCurrency,
                            sCurrency,
                            cb.isSelected());

                    Toast.makeText(view.getContext(), "Expense added",
                            Toast.LENGTH_LONG).show();

                    clearUI();

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

    private void clearUI(){
        categorySpinner.setSelection(0);
        subcategorySpinner.setSelection(0);
        descriptionEditText.setText("");
        cb.setSelected(false);
        currencySpinner.setSelection(0);
        wholeCurrencyET.setText("");
        smallCurrencyET.setText("");
    }
}
