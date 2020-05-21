package com.example.trading_simulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText username;
    EditText password;
    Button create_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        create_account = findViewById(R.id.create_account);

        create_account.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkDataEntered();
                System.out.println("------------------User info: " + username.getText() + " " + password.getText() + "------------");
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered(){
        if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(password)) {
            Toast t = Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }
}
