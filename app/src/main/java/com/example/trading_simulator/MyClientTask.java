package com.example.trading_simulator;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientTask extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String cmd;
    String user;
    String pswd;

    String response = "";

    MyClientTask(String addr, int port, String cmd, String user, String pswd){
        dstAddress = addr;
        dstPort = port;
        this.cmd = cmd;
        this.user = user;
        this.pswd = pswd;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            OutputStreamWriter osw;
            osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            String str = "OK";
            osw.write(str, 0, str.length());

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