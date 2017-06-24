package com.lovepreetsingh.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView mNewstext;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pb = (ProgressBar) findViewById(R.id.progress_bar);
        mNewstext =(TextView)findViewById(R.id.news_text);
        // search();
    }

    private void search() {

        String key="c8ef2db562e049c799cdf9bd1e3cf15b";
        new FetchWeatherTask().execute(key);
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {


            if (params.length == 0) {
                return null;
            }

            String key = params[0];


            URL weatherRequestUrl = NetworkUtils.buildUrl(key);



            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);

//                String[] simpleJsonWeatherData = OpenWeatherJsonUtils
//                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);


                return jsonWeatherResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(String weatherData) {

            pb.setVisibility(View.INVISIBLE);
            if (weatherData != null) {

//                mNewstext.setText("love");
//                for (String weatherString : weatherData) {
                mNewstext.append((weatherData) + "\n\n\n");
                //   }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.forecast, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    // COMPLETED (7) Override onOptionsItemSelected to handle clicks on the refresh button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_Search) {
            mNewstext.setText("");
            search();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}