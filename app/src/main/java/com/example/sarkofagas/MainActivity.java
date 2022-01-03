package com.example.sarkofagas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.sarkofagas.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);


        new Thread(new Runnable(){

          @Override
            public void run() {
                ArrayList<String> urls=new ArrayList<String>();

                try {
                    // Create a URL for the desired page
                    URL url = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/nauczyciel.txt"); //My text file location
                    //First open the connection
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000); // timing out in a minute

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    //t=(TextView)findViewById(R.id.TextView1); // ideally do this in onCreate()
                    String str;
                    while ((str = in.readLine()) != null) {
                        urls.add(str);
                    }
                    in.close();

            } catch (Exception e) {
                    e.printStackTrace();
                }

                MainActivity.this.runOnUiThread(new Runnable(){
                    public void run(){
                        binding.wyswietl.setText(urls.get(0)); // My TextFile has 3 lines
                    }
                });

            }


        }).start();
            };

    }