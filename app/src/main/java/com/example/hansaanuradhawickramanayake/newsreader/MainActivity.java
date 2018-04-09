package com.example.hansaanuradhawickramanayake.newsreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView titleListView;

    ArrayList<String> titles;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleListView = findViewById(R.id.titleListView);

        titles = new ArrayList<>();
        titles.add("first news");
        titles.add("first news");
        titles.add("first news");


        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
        titleListView.setAdapter(arrayAdapter);


        titleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);

            }
        });

    }

    public class DownloadTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {


            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                char current;

                while(data != -1){

                    current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (Exception e){

                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{

                JSONObject jsonObject = new JSONObject(s);



            } catch (Exception e){

                e.printStackTrace();
            }
        }
    }

}
