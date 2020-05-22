package com.example.trading_simulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);


        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (checkDataEntered()){
                    System.out.println("------------------User name: " + username.getText() + " Password: " + password.getText() + "------------");
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), Registration.class);
                startActivity(i);
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered(){
        if (isEmpty(username) && isEmpty(password)) {
            Toast t = Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        else if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        else if (isEmpty(password)) {
            Toast t = Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }
}
