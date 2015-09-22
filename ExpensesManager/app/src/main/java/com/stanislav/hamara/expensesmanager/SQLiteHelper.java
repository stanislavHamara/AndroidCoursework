package com.stanislav.hamara.expensesmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Stanislav Hamara on 07/04/15.
 * MySQLHelper class
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "task";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEORY = "category";
    public static final String COLUMN_SUBCATEGORY = "subcategory";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_WHOLE = "wholeC";
    public static final String COLUMN_SMALL = "smallC";
    public static final String COLUMN_RECLAIMED = "reclaimed";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_NAME = "expenses.db";
    private static final int DATABASE_VERSION = 1;

    //creation query
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CATEORY + " text not null, "
            + COLUMN_SUBCATEGORY + " text not null,"
            + COLUMN_DESC + " text not null,"
            + COLUMN_CURRENCY + " text not null,"
            + COLUMN_WHOLE + " integer,"
            + COLUMN_SMALL + " integer,"
            + COLUMN_RECLAIMED+ " text not null,"
            + COLUMN_DATE+ " text not null"
            + ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

