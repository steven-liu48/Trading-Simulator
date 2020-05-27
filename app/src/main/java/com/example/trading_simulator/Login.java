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
                //if (checkDataEntered()){
                    MyClientTask myClientTask = new MyClientTask("10.0.2.2", 12345);
                    myClientTask.execute();

                    System.out.println("------------------User name: " + username.getText() + " Password: " + password.getText() + "------------");
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);
                //}
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

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "";

        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                //OutputStreamWriter osw;
                //osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                //String str = "OK";
                //osw.write(str, 0, str.length());

//                ByteArrayOutputStream byteArrayOutputStream =
//                        new ByteArrayOutputStream(1024);
//                byte[] buffer = new byte[1024];
//
//                int bytesRead;
//                InputStream inputStream = socket.getInputStream();
//
//                /*
//                 * notice:
//                 * inputStream.read() will block if no data return
//                 */
//                while ((bytesRead = inputStream.read(buffer)) != -1){
//                    byteArrayOutputStream.write(buffer, 0, bytesRead);
//                    response += byteArrayOutputStream.toString("UTF-8");
//                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            textResponse.setText(response);
//            super.onPostExecute(result);
//        }

    }
}
