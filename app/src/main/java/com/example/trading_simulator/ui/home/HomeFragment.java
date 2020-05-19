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
import com.example.trading_simulator.stock_price.RandomStockPriceGenerator;
import com.example.trading_simulator.stock_price.StockPriceAccesser;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {


    String[] stockName = {"APPL", "BA", "BLK", "TSLA", "GOOGL","GS", "MS", "JPM", "AAL", "CVX","XOM", "NFLX", "ZM", "DAL", "UBER"};//fruit names array
    ListView simpleListView;
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        swipeRefreshLayout = root.findViewById(R.id.simpleSwipeRefreshLayout);

        final StockPriceAccesser accesser = new RandomStockPriceGenerator();
        simpleListView=(ListView)root.findViewById(R.id.simpleListView);


        showListView(accesser);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh

                showListView(accesser);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //perform listView item click event

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),stockName[i], Toast.LENGTH_LONG).show();//show the selected image in toast according to position
            }
        });



        return root;
    }

    /**
     * Display the ListView of stocks and prices when refreshes
     * @param accesser The accesser to the stock price
     */
    private void showListView(StockPriceAccesser accesser) {
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        for (int i=0;i<stockName.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("name",stockName[i]);
            hashMap.put("price",String.format("$%.2f", accesser.getCurrentPrice(stockName[i])));
            arrayList.add(hashMap);//add the hashmap into arrayList
        }
        String[] from={"name","price"};//string array
        int[] to={R.id.name,R.id.price};//int array of views id's
        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),arrayList,R.layout.list_view_items,from,to);//Create object and set the parameters for simpleAdapter
        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

    }
}
