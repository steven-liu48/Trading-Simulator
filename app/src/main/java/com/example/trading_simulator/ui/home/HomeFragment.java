package com.example.trading_simulator.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.trading_simulator.R;
import com.example.trading_simulator.Stock;
import com.example.trading_simulator.StockAdapter;
import com.example.trading_simulator.stock_price.RandomStockPriceGenerator;
import com.example.trading_simulator.stock_price.StockPriceAccesser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Stock> stockList = new ArrayList<Stock>();

    ListView simpleListView;
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Swipe refresh

        swipeRefreshLayout = root.findViewById(R.id.simpleSwipeRefreshLayout);
        final StockPriceAccesser accessor = new RandomStockPriceGenerator();
        simpleListView=(ListView)root.findViewById(R.id.simpleListView);
        initStock(accessor);
        showListView(accessor, root);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showListView(accessor, root);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // On click for each stock block
        // TODO: make detailed pages for each stock

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(),stockName[i], Toast.LENGTH_LONG).show();//show the selected image in toast according to position
            }
        });

        return root;
    }


    // TODO: rewrite refresh method
    /**
     * Display the ListView of stocks and prices when refreshes
     * @param accessor The accessor to the stock price
     */
    private void showListView(StockPriceAccesser accessor, View view) {
//        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
//        for (int i=0;i<stockName.length;i++)
//        {
//            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
//            hashMap.put("name",stockName[i]);
//            hashMap.put("price",String.format("$%.2f", accesser.getCurrentPrice(stockName[i])));
//            arrayList.add(hashMap);//add the hashmap into arrayList
//        }
//        String[] from={"name","price"};//string array
//        int[] to={R.id.name,R.id.price};//int array of views id's
//        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),arrayList,R.layout.list_view_items,from,to);//Create object and set the parameters for simpleAdapter
//        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

        // Update stock prices
        // Update all Prices
        for (Stock s: stockList){
            s.setPrice(accessor.getCurrentPrice(s.getName()));
        }
        // Show listView
        StockAdapter adapter = new StockAdapter(getContext(), R.layout.list_view_items, stockList);
        ListView listView = view.findViewById(R.id.simpleListView);
        listView.setAdapter(adapter);
    }

    private void initStock(StockPriceAccesser accessor){

        // Add a few demo stocks

        Stock APPL = new Stock("APPL", "Apple Inc.");
        stockList.add(APPL);
        Stock BA = new Stock("BA", "The Boeing Company");
        stockList.add(BA);
        Stock GOOGL = new Stock("GOOGL", "Alphabet Inc.");
        stockList.add(GOOGL);
        Stock BLK = new Stock("BLK", "BlackRock, Inc.");
        stockList.add(BLK);
        Stock GS = new Stock("GS", "The Goldman Sachs Group");
        stockList.add(GS);
        Stock MS = new Stock("MS", "Morgan Stanley");
        stockList.add(MS);
        Stock UBER = new Stock("UBER", "Uber Technologies, Inc.");
        stockList.add(UBER);
        Stock Test1 = new Stock("Test1", "Test1");
        stockList.add(Test1);
        Stock Test2 = new Stock("Test2", "Test2");
        stockList.add(Test2);
        Stock Test3 = new Stock("Test3", "Test3");
        stockList.add(Test3);
        Stock Test4 = new Stock("Test4", "Test4");
        stockList.add(Test4);
        Stock Test5 = new Stock("Test5", "Test5");
        stockList.add(Test5);

        // Update all stock prices

        for (Stock s: stockList){
            s.setPrice(accessor.getCurrentPrice(s.getName()));
        }
    }
}