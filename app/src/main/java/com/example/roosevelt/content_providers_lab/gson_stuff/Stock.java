package com.example.roosevelt.content_providers_lab.gson_stuff;

/**
 * Created by roosevelt on 8/10/16.
 */
public class Stock {
    String Symbol, Name, Exchange;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExchange() {
        return Exchange;
    }

    public void setExchange(String exchange) {
        Exchange = exchange;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "Symbol='" + Symbol + '\'' +
                ", Name='" + Name + '\'' +
                ", Exchange='" + Exchange + '\'' +
                '}';
    }
}


/*

  {
    "Symbol": "A",
    "Name": "Agilent Technologies Inc",
    "Exchange": "NYSE"
  }
 */