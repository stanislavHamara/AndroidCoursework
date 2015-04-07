package com.stanislav.hamara.expensesmanager;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by stan on 06/04/15.
 */
public class TabFragment extends Fragment {

    private int mTabID;
    private SpinnerActivity mSpinnerActivity;
    private Button mAddButton;

    private String category;
    private String subcategory;
    private String description;
    private String currency;
    private int whole_currency;
    private int small_currency;
    private boolean receipt_retianed;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        mTabID = mBundle.getInt("tabID");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        switch (mTabID){
            case 0:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_tab_expenses, null);
                //initialize the main spinner
                mSpinnerActivity = new SpinnerActivity();
                mSpinnerActivity.initializeMainSpinner(view);
                mAddButton = (Button) view.findViewById(R.id.addButton);

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

                            Log.e("Output: ",category + " , " + subcategory + " , " + description + " , " + currency + whole_currency + "." + small_currency);

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
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_tab_summary, null);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_tab_home, null);
                break;
        }
        return view;
    }
}

