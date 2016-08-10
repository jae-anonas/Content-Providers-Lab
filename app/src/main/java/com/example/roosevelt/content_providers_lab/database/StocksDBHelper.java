package com.example.roosevelt.content_providers_lab.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.roosevelt.content_providers_lab.content_provider.StocksContract;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StocksDBHelper extends SQLiteOpenHelper {

    public static final String STOCKS_DB_NAME = "stocks.db";
    public static final int STOCKS_DB_VERSION = 1;

    public static final String STOCKS_TABLE_NAME = StocksContract.Stocks.TABLE_STOCKS;
    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_FULL_NAME = StocksContract.Stocks.COL_FULL_NAME;
    public static final String COL_SYMBOL = StocksContract.Stocks.COL_SYMBOL;
    public static final String COL_EXCHANGE = StocksContract.Stocks.COL_EXCHANGE;
    public static final String COL_QUANTITY = StocksContract.Stocks.COL_QUANTITY;

    public static final String CREATE_STOCKS_TABLE = "CREATE TABLE " +
            STOCKS_TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY, " +
            COL_FULL_NAME + " TEXT, " +
            COL_SYMBOL + " TEXT, " +
            COL_EXCHANGE + " TEXT, " +
            COL_QUANTITY + " INTEGER " + ")";

    public static final String DROP_STOCKS_TABLE = "DROP TABLE IF EXISTS " +
            STOCKS_TABLE_NAME;

    public static StocksDBHelper sInstance = null;

    private StocksDBHelper(Context context) {
        super(context.getApplicationContext(), STOCKS_DB_NAME, null, STOCKS_DB_VERSION);
    }

    public static StocksDBHelper getsInstance(Context context){
        if (sInstance == null)
            sInstance = new StocksDBHelper(context);
        return sInstance;
    }

    /*

*/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STOCKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_STOCKS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addStock(ContentValues values) {

        SQLiteDatabase db = getWritableDatabase();
        long insertedRow = db.insert(STOCKS_TABLE_NAME, null, values);
        db.close();
        return insertedRow;
    }

    public Cursor findStock(String selection, String[] selectionArgs,
                            String sortOrder, String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        if(id == null)
            cursor = db.query(STOCKS_TABLE_NAME, null, selection,
                    selectionArgs, null, null, sortOrder);
        else
            cursor = db.query(STOCKS_TABLE_NAME, null, COL_ID + " = ?",
                    new String[]{id}, null, null, sortOrder);

        return cursor;
    }

    public int deleteStock(String selection, String[] selectionArgs, String id) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsDeleted = 0;

        if(id == null)
            rowsDeleted = db.delete(STOCKS_TABLE_NAME,selection,selectionArgs);
        else
            rowsDeleted = db.delete(STOCKS_TABLE_NAME,COL_ID+" = ?",new String[]{id});

        db.close();
        return rowsDeleted;
    }

    public int updateStock(ContentValues values, String selection, String[] selectionArgs, String id){
        SQLiteDatabase db = getWritableDatabase();
        int updatedRows = 0;

        if(id == null)
            updatedRows = db.update(STOCKS_TABLE_NAME,values,selection,selectionArgs);
        else
            updatedRows = db.update(STOCKS_TABLE_NAME,values,COL_ID+" = ?",new String[]{id});

        db.close();
        return updatedRows;
    }
}
