package com.example.roosevelt.content_providers_lab;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.roosevelt.content_providers_lab.gson_stuff.Stock;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by roosevelt on 8/10/16.
 */
public class MarkItAsyncTask extends AsyncTask<String, Void, Stock[]> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public MarkItAsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected Stock[] doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Stock[] stockObject = null;

        Request request = new Request.Builder()
                .url(strings[0])
                .build();

        try{
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            stockObject = gson.fromJson(response.body().string(),
                    Stock[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockObject != null ? stockObject : null;

    }

    @Override
    protected void onPostExecute(Stock[] stocksRootObject) {
        super.onPostExecute(stocksRootObject);
//        List mStockList = new LinkedList<>(Arrays.asList(stocksRootObject));
        if (stocksRootObject != null)
            delegate.processFinish(stocksRootObject[0].getName());
        else
            delegate.processFinish("");


//        Toast.makeText(, "Got here! Add here, i guess size = " + mStockList.size(), Toast.LENGTH_SHORT).show();

    }
}