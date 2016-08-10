package com.example.roosevelt.content_providers_lab.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.roosevelt.content_providers_lab.R;
import com.example.roosevelt.content_providers_lab.StockEntry;

import java.util.List;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StockCardRecyclerViewAdapter extends RecyclerView.Adapter<StockCardViewHolder>{

    List<StockEntry> mStockEntryList;

    public void setStockEntryList(List<StockEntry> stockEntryList) {
        this.mStockEntryList = stockEntryList;
    }

    public StockCardRecyclerViewAdapter(List<StockEntry> stockEntryList) {
        this.mStockEntryList = stockEntryList;
    }

    @Override
    public StockCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.card_stock_layout, parent, false);

        StockCardViewHolder viewHolder = new StockCardViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StockCardViewHolder holder, int position) {
        StockEntry stock = mStockEntryList.get(position);

        holder.setName(stock.getName());
        holder.setQty(String.valueOf(stock.getQty()));
    }

    @Override
    public int getItemCount() {
        return mStockEntryList.size();
    }

    /*
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
//        final int myPosition = position;
        CustomObject customObject = mCustomObjectList.get(position);
        final String theTitle = customObject.getTitle();

        holder.setText1Text(theTitle);
        holder.setText2Text(customObject.getDescription());
        holder.setColor(customObject.getColor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Title " + theTitle,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomObjectList.size();
    }
}



     */
}
