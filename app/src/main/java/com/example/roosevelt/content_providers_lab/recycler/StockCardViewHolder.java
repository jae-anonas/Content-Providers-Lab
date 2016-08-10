package com.example.roosevelt.content_providers_lab.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.roosevelt.content_providers_lab.R;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StockCardViewHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtQty;

    public StockCardViewHolder(View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtQty = (TextView) itemView.findViewById(R.id.txtQty);
    }

    public void setName(String name){
        txtName.setText(name);
    }

    public void setQty(String qty){
        txtQty.setText(qty);
    }
}
