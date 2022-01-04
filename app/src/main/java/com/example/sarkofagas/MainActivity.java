package com.example.sarkofagas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sarkofagas.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        String zmienna = "";
        Button button = findViewById(R.id.button);
        RadioButton radioButton = findViewById(R.id.radioButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton.isChecked()) {
                    TextView tekst = (TextView) findViewById(R.id.textView2);
                    tekst.setText("Sekretariat");
                    new Thread(new Runnable(){

                        @Override
                        public void run() {
                            ArrayList<String> urls=new ArrayList<String>();

                            try {
                                // Create a URL for the desired page
                                URL url = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/nauczyciel.txt");
                                //URL urlp = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/pracownik.txt");
                                //URL urlu = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/uczen.txt");//My text file location
                                //First open the connection

                                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                                con.setConnectTimeout(60000); // timing out in a minute

                                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                                String str;
                                while ((str = in.readLine()) != null) {
                                    urls.add(str);
                                }
                                in.close();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            MainActivity.this.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    TextView cos = (TextView) findViewById(R.id.dane);
                                    cos.setText(urls.get(0));
                                }

                            });
                        }
                    }).start();
                }
            }
        });

        new Thread(new Runnable(){

          @Override
            public void run() {
                ArrayList<String> urls=new ArrayList<String>();

                try {
                    // Create a URL for the desired page
                    URL url = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/pracownik.txt");
                    //URL urlp = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/pracownik.txt");
                    //URL urlu = new URL("https://raw.githubusercontent.com/krisu12345/Sarkofag/master/uczen.txt");//My text file location
                    //First open the connection

                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    con.setConnectTimeout(60000); // timing out in a minute

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String str;
                    while ((str = in.readLine()) != null) {
                        urls.add(str);
                    }
                    in.close();

            } catch (Exception e) {
                    e.printStackTrace();
                }

                MainActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        TextView cos = (TextView) findViewById(R.id.dane);
                        cos.setText(urls.get(0));
                    }

                });
            }
        }).start();
            };


}