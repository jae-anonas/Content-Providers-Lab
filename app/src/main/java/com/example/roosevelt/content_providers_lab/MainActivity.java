package com.example.roosevelt.content_providers_lab;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.roosevelt.content_providers_lab.content_provider.StocksContract;
import com.example.roosevelt.content_providers_lab.cursor_helper.StockCursorAdapter;
import com.example.roosevelt.content_providers_lab.recycler.StockCardRecyclerViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContentResolver mContentResolver;
    private RecyclerView mRecyclerView;
    private ListView mListView;
//    private StockCardRecyclerViewAdapter adapter;
    private StockCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContentResolver = getContentResolver();
        mListView = (ListView) findViewById(R.id.listView);

        adapter = new StockCursorAdapter(this, fetchStocks(), 0);
        mListView.setAdapter(adapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeStock(l);
                adapter.swapCursor(fetchStocks());
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                showAddDialog();
            }
        });
    }

    public void addStock(ContentValues values){
        mContentResolver.insert(StocksContract.Stocks.CONTENT_URI, values);
    }

    public void removeStock(long id){
        mContentResolver.delete(StocksContract.Stocks.CONTENT_URI,
                StocksContract.Stocks._ID + "=?",
                new String[]{String.valueOf(id)});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAddDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_alert, null);
        dialogBuilder.setView(dialogView);

        final EditText txtStockSymbol = (EditText) dialogView.findViewById(R.id.txtStockSymbol);
        final EditText txtStockQty = (EditText) dialogView.findViewById(R.id.txtStockQuantity);

        dialogBuilder.setTitle("Add New Stock")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String stockSymbol = txtStockSymbol.getText().toString();
                String stockQty = txtStockQty.getText().toString();

                //TODO check symbol through API call

                //add stock here
                ContentValues values = new ContentValues();
                values.put(StocksContract.Stocks.COL_SYMBOL, stockSymbol);
                values.put(StocksContract.Stocks.COL_QUANTITY, stockQty);
                values.put(StocksContract.Stocks.COL_FULL_NAME, "Stock Full Name"); //from API Call

                addStock(values);

                //TODO refresh adapter list
//                adapter.setStockEntryList(getListFromCursor(fetchStocks()));
                adapter.changeCursor(fetchStocks());
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "adding new stock!", Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private Cursor fetchStocks(){
        return mContentResolver.query(StocksContract.Stocks.CONTENT_URI, null, null, null, null);
    }

}