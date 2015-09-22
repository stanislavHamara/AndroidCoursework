package com.stanislav.hamara.expensesmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by Stanislav Hamara on 07/04/15.
 *
 * ExpensesController controls the functionality of expenses tab, which allows the user to create new expenses
 */
public class ExpensesController {

    private View view;
    private ExpenseDataSource mDatasource;

    private Spinner categorySpinner;
    private Spinner subcategorySpinner;
    private EditText descriptionEditText;
    private CheckBox cb;
    private Spinner currencySpinner;
    private EditText wholeCurrencyET;
    private EditText smallCurrencyET;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");


    public ExpensesController(View view, ExpenseDataSource mDatasource){
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
                try {

                    //find out if any journey si created at all
                    SharedPreferences prefs = view.getContext().getSharedPreferences(HomeController.PREFS_NAME, 0);
                    String name = prefs.getString("name", "None");

                    if(name.isEmpty() || name.contains("None"))
                        throw new DataFormatException();

                    //assign relevant values from UI to variables
                    categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
                    subcategorySpinner = (Spinner) view.findViewById(R.id.subcategory_spinner);
                    descriptionEditText = (EditText) view.findViewById(R.id.descriptionText);
                    cb = (CheckBox) view.findViewById(R.id.retained_receipt);
                    currencySpinner = (Spinner) view.findViewById(R.id.currency_spinner);

                    //amount of money
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
                            cb.isSelected(),
                            sdf.format(new Date()));


                    Toast.makeText(view.getContext(), "Expense added",
                            Toast.LENGTH_LONG).show();

                    clearUI();

                } catch (NumberFormatException e){
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Alert")
                            .setMessage("Invalid format. Currency must be a number in range 0-99 and description can't be empty!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } catch (DataFormatException e){
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("You can't add expense if there is no journey")
                            .setMessage("Please go to the home tab and create a journey first")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
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
