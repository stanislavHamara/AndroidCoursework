package com.stanislav.hamara.expensesmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stan on 07/04/15.
 */
public class ExpenseDataSource {

    private SQLiteDatabase mDatabase;
    private MySQLiteHelper mDbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CATEORY,
            MySQLiteHelper.COLUMN_SUBCATEGORY,
            MySQLiteHelper.COLUMN_DESC,
            MySQLiteHelper.COLUMN_CURRENCY,
            MySQLiteHelper.COLUMN_WHOLE,
            MySQLiteHelper.COLUMN_SMALL,
            MySQLiteHelper.COLUMN_RECLAIMED};
            Context context;


    public ExpenseDataSource(Context c){
        mDbHelper = new MySQLiteHelper(c);
        context = c;
    }

    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public void deleteDatabase(){
        context.deleteDatabase("expenses.db");
    }

    public Expense createExpense(String c, String sc, String desc, String curr, int w, int s, boolean r){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CATEORY, c);
        values.put(MySQLiteHelper.COLUMN_SUBCATEGORY, sc);
        values.put(MySQLiteHelper.COLUMN_DESC, desc);
        values.put(MySQLiteHelper.COLUMN_CURRENCY, curr);
        values.put(MySQLiteHelper.COLUMN_WHOLE, w);
        values.put(MySQLiteHelper.COLUMN_SMALL, s);
        values.put(MySQLiteHelper.COLUMN_RECLAIMED, r);

        mDatabase.insert(MySQLiteHelper.TABLE_NAME, null, values);
        Expense expense = new Expense(c, sc, desc, curr, w, s, r);
        return expense;
    }

    public List<Expense> getAllTasks() {
        List<Expense> expenses = new ArrayList<Expense>();
        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Expense expense = cursorToTask(cursor);
            expenses.add(expense);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return expenses;
    }

    public void deleteExpense(String desc){

        /*this should have been done with ID instead,
        but I forgot to add ID to Expenses class and
        assuming that each description is unique, this will work too

        I could not make it work with IDs*/

        System.out.println("Comment deleted with id: " + desc);
        mDatabase.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_DESC
                + " like  '%" + desc + "'", null);
    }

    private Expense cursorToTask(Cursor cursor) {
        Expense task = new Expense(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getInt(6),
                false);

        return task;
    }
}
