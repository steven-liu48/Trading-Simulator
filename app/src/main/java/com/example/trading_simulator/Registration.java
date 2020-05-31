package com.example.trading_simulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Registration extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirm_password;
    Button create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        create_account = findViewById(R.id.create_account);

        create_account.setOnClickListener(new View.OnClickListener(){
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
                            Toast.makeText(getApplicationContext(), "The username already exists. Please input again", Toast.LENGTH_SHORT).show();
                        } else if (str.equals(0)){ // The user name does not exist
                            str = "ADDUSR\n" + username + password;
                            System.out.println(str);
                            osw.write(str, 0, str.length()); // Send the message and add the user to the SQL server
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            // Go to login
                            Intent i = new Intent(view.getContext(), Login.class);
                            startActivity(i);
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

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered(){
        if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(password)) {
            Toast t = Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(confirm_password)) {
            Toast t = Toast.makeText(this, "Please enter your password again", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (!password.getText().toString().equals(confirm_password.getText().toString())){
            Toast t = Toast.makeText(this, "Your two attempts don't match", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        Toast t = Toast.makeText(this, "Succeeded! You can login now.", Toast.LENGTH_SHORT);
        t.show();
        return true;
    }
}
