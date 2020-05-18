package com.example.trading_simulator.stock_price_test;

import com.example.trading_simulator.stock_price.RandomStockPriceGenerator;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class RandomStockPriceGeneratorTest {
    @Test
    public void getCurrentPriceTest(){
        RandomStockPriceGenerator rsp = new RandomStockPriceGenerator();
        for (int i = 0; i < 100; i++){
            assertTrue(rsp.getCurrentPrice(i+"") >= 0);
            assertTrue(rsp.getCurrentPrice(i+"") <= 1000);
        }
    }
}
