package com.example.trading_simulator.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.trading_simulator.Login;
import com.example.trading_simulator.R;
import com.example.trading_simulator.Registration;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button login;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        // On Click: open the registration page
        login = root.findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), Login.class);
                startActivity(i);
            }
        });


        return root;
    }
}
