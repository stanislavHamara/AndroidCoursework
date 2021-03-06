package com.stanislav.hamara.expensesmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Stanislav Hamara on 07/04/15.
 *
 * SummaryController controlls the functionality of summary tab, which allows the user to view
 * the list of the expenses and allows the user to claim the expenses back
 */
public class SummaryController {

    private Activity activity;
    private List<Expense> values;
    private ExpenseListAdapter expensesAdapter;
    public final ListView listView;

    ExpenseDataSource mDatasource;

    private TextView poundExpenses;
    private TextView dollarExpenses;
    private TextView euroExpenses;

    public SummaryController(View view, Activity activity, final ExpenseDataSource mDatasource){

        this.activity = activity;
        listView  = (ListView) view.findViewById(R.id.purchases_list);
        this.mDatasource = mDatasource;

        poundExpenses = (TextView) view.findViewById(R.id.pound_expenses_view);
        dollarExpenses = (TextView) view.findViewById(R.id.dollar_expenses_view);
        euroExpenses = (TextView) view.findViewById(R.id.euro_expenses_view);


        //user can long click the particular item in the list, which allows him to claim it back

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Reclaim Expense")
                        .setMessage("Are you sure you want to reclaim this expense?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //remove from database
                                mDatasource.deleteExpense(((Expense) expensesAdapter.getItem(position)).getDescription());
                                //remove from listView
                                values.remove(position);
                                expensesAdapter.notifyDataSetChanged();
                                //update total expenses
                                updateFooter();

                                Toast.makeText(view.getContext(), "Expense reclaimed",
                                        Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(view.getContext(), "Reclaim canceled",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });
        initSummary();
    }

    private void initSummary(){

        values = mDatasource.getAllTasks();
        expensesAdapter = new ExpenseListAdapter(activity.getBaseContext(),values);
        listView.setAdapter(expensesAdapter);
        updateFooter();

    }


    // calculates sum of expenses for each particular currency
    private void updateFooter(){
        float pounds = 0;
        float dollars = 0;
        float euros = 0;

        for (int i = 0; i < values.size(); i++){
            if(values.get(i).getCurrency().contains("£"))
                pounds += (float)(values.get(i).getWhole_currency() + (values.get(i).getSmall_currency() / 100.0));

            else if (values.get(i).getCurrency().contains("$"))
                dollars += (float)(values.get(i).getWhole_currency() + (values.get(i).getSmall_currency() / 100.0));
            else
                euros += (float)(values.get(i).getWhole_currency() + (values.get(i).getSmall_currency() / 100.0));
        }

        poundExpenses.setText("£" + String.format("%.2f", pounds));
        dollarExpenses.setText("$" + String.format("%.2f", dollars));
        euroExpenses.setText("€" + String.format("%.2f", euros));
    }
}
