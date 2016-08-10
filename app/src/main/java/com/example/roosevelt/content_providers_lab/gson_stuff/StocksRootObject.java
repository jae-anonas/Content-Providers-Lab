package com.example.roosevelt.content_providers_lab.gson_stuff;

import java.util.Arrays;

/**
 * Created by roosevelt on 8/10/16.
 */
public class StocksRootObject {
    private Stock[] items;

    public Stock[] getItems() {
        return items;
    }

    public void setItems(Stock[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "StocksRootObject{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
/**
 *
 public class WalmartRootObject {
 private WalmartItem[] items;

 public void setItems(WalmartItem[] items){
 this.items = items;
 }

 public WalmartItem[] getItems(){return items;}

 @Override
 public String toString() {
 return items.length+" item(s) in the search result";
 }
 }

 *
 */