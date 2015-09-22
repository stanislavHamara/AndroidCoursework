package com.stanislav.hamara.expensesmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav Hamara on 07/04/15.
 *
 * ExpenseDataSource is a class that defines and controlls the exchange of information between the application
 * and SQLite Database
 */
public class ExpenseDataSource {

    private SQLiteDatabase mDatabase;
    private SQLiteHelper mDbHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_CATEORY,
            SQLiteHelper.COLUMN_SUBCATEGORY,
            SQLiteHelper.COLUMN_DESC,
            SQLiteHelper.COLUMN_CURRENCY,
            SQLiteHelper.COLUMN_WHOLE,
            SQLiteHelper.COLUMN_SMALL,
            SQLiteHelper.COLUMN_RECLAIMED,
            SQLiteHelper.COLUMN_DATE};
            Context context;


    public ExpenseDataSource(Context c){
        mDbHelper = new SQLiteHelper(c);
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

    public void createExpense(String c, String sc, String desc, String curr, int w, int s, boolean r, String d){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_CATEORY, c);
        values.put(SQLiteHelper.COLUMN_SUBCATEGORY, sc);
        values.put(SQLiteHelper.COLUMN_DESC, desc);
        values.put(SQLiteHelper.COLUMN_CURRENCY, curr);
        values.put(SQLiteHelper.COLUMN_WHOLE, w);
        values.put(SQLiteHelper.COLUMN_SMALL, s);
        values.put(SQLiteHelper.COLUMN_RECLAIMED, r);
        values.put(SQLiteHelper.COLUMN_DATE, d);

        mDatabase.insert(SQLiteHelper.TABLE_NAME, null, values);
    }

    public List<Expense> getAllTasks() {
        List<Expense> expenses = new ArrayList<Expense>();
        Cursor cursor = mDatabase.query(SQLiteHelper.TABLE_NAME,
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
        mDatabase.delete(SQLiteHelper.TABLE_NAME, SQLiteHelper.COLUMN_DESC
                + " like  '%" + desc + "'", null);
    }

    private Expense cursorToTask(Cursor cursor) {
        Expense task = new Expense(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getInt(6),
                false,
                cursor.getString(8));

        return task;
    }
}
