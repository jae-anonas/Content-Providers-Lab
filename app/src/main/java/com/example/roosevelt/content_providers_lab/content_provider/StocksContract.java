package com.example.roosevelt.content_providers_lab.content_provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StocksContract {
    public static final String AUTHORITY = "com.example.roosevelt.content_providers_lab.content_provider." +
            "StocksContentProvider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Stocks implements BaseColumns {
        public static final String TABLE_STOCKS = "stock";
        public static final String COL_FULL_NAME = "stock_name";
        public static final String COL_SYMBOL = "stock_symbol";
        public static final String COL_EXCHANGE = "stock_exchange";
        public static final String COL_QUANTITY = "stock_quantity";

        public static final Uri CONTENT_URI = Uri
                .withAppendedPath(BASE_CONTENT_URI, TABLE_STOCKS);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/vnd.com.example.roosevelt.content_providers_lab.stocks";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/vnd.com.example.roosevelt.content_providers_lab.stocks";

    }
}
