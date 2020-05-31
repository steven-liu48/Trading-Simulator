package com.example.trading_simulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
                    // Use socket connection to check if account name has been taken, and/or create new account
                    Socket socket = null;
                    String dstAddress = "";
                    int dstPort = 0;
                    String response = "";

                    try {
                        socket = new Socket(dstAddress, dstPort);
                        // Send message to check if user exists
                        OutputStreamWriter osw;
                        osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                        String str = "CHECKUSR\n" + username; // The message to be sent
                        System.out.println(str); // Print out message
                        osw.write(str, 0, str.length()); // Send the message
                        // Get reply from server
                        InputStreamReader in = new InputStreamReader(socket.getInputStream());
                        str = in.toString();
                        System.out.println(str);
                        // ...and act accordingly
                        if (str.equals(1)){ // The user name exists
                            str = "GETPW\n" + username;
                            System.out.println(str);
                            osw.write(str, 0, str.length()); // Send the message and get the password
                            in = new InputStreamReader(socket.getInputStream()); // Get the message back
                            str = in.toString();
                            System.out.println(str);
                            if (str.equals(password.toString())){
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(view.getContext(), MainActivity.class);
                                startActivity(i);
                            }
                        } else if (str.equals(0)){ // The user name does not exist
                            Toast.makeText(getApplicationContext(), "The user name you input does not exist. Please input again", Toast.LENGTH_SHORT).show();
                        } else { // Errors occur
                            Toast.makeText(getApplicationContext(), "Error. Please input again", Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        response = "UnknownHostException: " + e.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        response = "IOException: " + e.toString();
                    }finally{
                        if(socket != null){
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
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
