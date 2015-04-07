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
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_CATEORY};

    public ExpenseDataSource(Context context){
        mDbHelper = new MySQLiteHelper(context);
    }

    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
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
        List<Expense> tasks = new ArrayList<Expense>();
        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Expense expense = cursorToTask(cursor);
            tasks.add(expense);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return tasks;
    }

    private Expense cursorToTask(Cursor cursor) {
        Expense task = new Expense(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                false);

        return task;
    }
}
