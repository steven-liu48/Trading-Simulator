package com.example.trading_simulator.stock_price;

public class RandomStockPriceGenerator implements StockPriceAccesser{
    public double getCurrentPrice(String stockName){
        return Math.random() * 1000;
    }
}
