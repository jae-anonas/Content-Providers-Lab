package com.example.roosevelt.content_providers_lab.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.roosevelt.content_providers_lab.database.StocksDBHelper;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StocksContentProvider extends ContentProvider {
    private StocksDBHelper mStocksDBHelper;

    public static final int STOCKS = 1;
    public static final int STOCKS_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        sUriMatcher.addURI(StocksContract.AUTHORITY,
                StocksContract.Stocks.TABLE_STOCKS,
                STOCKS);
        sUriMatcher.addURI(StocksContract.AUTHORITY,
                StocksContract.Stocks.TABLE_STOCKS + "/#",
                STOCKS_ID);
    }


    @Override
    public boolean onCreate() {
        mStocksDBHelper = StocksDBHelper.getsInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        int uriType = sUriMatcher.match(uri);
        Cursor cursor = null;

        switch(uriType){
            case STOCKS:
                cursor = mStocksDBHelper.findStock(s, strings1, s1, null);
                break;
            case STOCKS_ID:
                cursor = mStocksDBHelper.findStock(s, strings1, s1, uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case STOCKS:
                return StocksContract.Stocks.CONTENT_TYPE;
            case STOCKS_ID:
                return StocksContract.Stocks.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = sUriMatcher.match(uri);
        long id = 0;

        if (uriType == STOCKS)
            id = mStocksDBHelper.addStock(contentValues);
        else
            throw new IllegalArgumentException("Unknown URI: " + uri);

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(StocksContract.Stocks.CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int uriType = sUriMatcher.match(uri);
        int rowsDeleted = 0;

        switch(uriType){
            case STOCKS:
                rowsDeleted = mStocksDBHelper.deleteStock(s, strings, null);
                break;
            case STOCKS_ID:
                rowsDeleted = mStocksDBHelper.deleteStock(s, strings, uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return rowsDeleted;
    }

    /**
     *


     *
     */

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int uriType = sUriMatcher.match(uri);
        int rowsUpdated = 0;

        switch(uriType){
            case STOCKS:
                rowsUpdated = mStocksDBHelper.updateStock(contentValues,
                        s, strings, null);
                break;
            case STOCKS_ID:
                rowsUpdated = mStocksDBHelper.updateStock(contentValues,
                        s, strings,
                        uri.getLastPathSegment());
        }

        return rowsUpdated;
    }
}
