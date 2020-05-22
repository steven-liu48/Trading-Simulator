package com.example.trading_simulator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StockAdapter extends ArrayAdapter {
    private final int resourceId;

    public StockAdapter(Context context, int textViewResourceId, List<Stock> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Stock s = (Stock) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView stockName = (TextView) view.findViewById(R.id.name);
        stockName.setText(s.getName());

        TextView stockFullName = (TextView) view.findViewById(R.id.fullName);
        stockFullName.setText(s.getFullName());

        TextView stockPrice = (TextView) view.findViewById(R.id.price);
        stockPrice.setText(String.format("$%.2f", s.getPrice()));

        s.setOpen(300); // For now we set the open price to 300

        TextView stockPriceChange = (TextView) view.findViewById(R.id.priceChange);
        double priceChange = (s.getPrice()-s.getOpen())/s.getOpen() * 100;
        if (priceChange > 0 ){
            stockPriceChange.setText("+" + String.format("%.2f%%%n", priceChange));
            stockPriceChange.setTextColor(Color.GREEN);
        } else if (priceChange < 0){
            stockPriceChange.setText("" + String.format("%.2f%%%n", priceChange));
            stockPriceChange.setTextColor(Color.RED);
        }

        return view;
    }
}
