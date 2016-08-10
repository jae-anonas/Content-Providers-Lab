package com.example.roosevelt.content_providers_lab;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StockEntry {
    private int mId, mQty;
    private String mName, mSymbol;

    public StockEntry(int mId, int mQty, String mName, String mSymbol) {
        this.mId = mId;
        this.mQty = mQty;
        this.mName = mName;
        this.mSymbol = mSymbol;
    }

    public StockEntry(int mQty, String mName, String mSymbol) {
        this.mQty = mQty;
        this.mName = mName;
        this.mSymbol = mSymbol;
    }

    public StockEntry(int mQty, String mName) {
        this.mQty = mQty;
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getQty() {
        return mQty;
    }

    public void setQty(int mQty) {
        this.mQty = mQty;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String mSymbol) {
        this.mSymbol = mSymbol;
    }
}
