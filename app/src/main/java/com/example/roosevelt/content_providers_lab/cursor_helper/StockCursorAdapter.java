package com.example.roosevelt.content_providers_lab.cursor_helper;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roosevelt.content_providers_lab.R;
import com.example.roosevelt.content_providers_lab.content_provider.StocksContract;
import com.example.roosevelt.content_providers_lab.recycler.StockCardViewHolder;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StockCursorAdapter extends CursorAdapter {
    private Context context;

    public StockCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.card_stock_layout, parent, false);

    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtQty = (TextView) view.findViewById(R.id.txtQty);

        final long id = cursor.getLong(cursor.getColumnIndex(StocksContract.Stocks._ID));
        String name = cursor.getString(cursor.getColumnIndex(StocksContract.Stocks.COL_FULL_NAME));
        final String symbol = cursor.getString(cursor.getColumnIndex(StocksContract.Stocks.COL_SYMBOL));
        int qty = cursor.getInt(cursor.getColumnIndex(StocksContract.Stocks.COL_QUANTITY));

        txtName.setText(name);
        txtQty.setText(String.valueOf(qty));

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, symbol + " " + String.valueOf(id), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
