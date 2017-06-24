package com.lovepreetsingh.newsapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by lovepreetsingh on 6/21/17.
 */

public final class NetworkUtils
{
    private static final String url="https://newsapi.org/v1/articles";

    private static final String base_url_param=url;
    private static final String source=" the-next-web";
    private static final String source_param=source;
    private static final String sort_by=" latest";

    private static final String sort_param=sort_by;
    private static final String api_key=" ";


    public static URL buildUrl( String Keys)
    {
        Uri builduri=Uri.parse(base_url_param).buildUpon()
                .appendQueryParameter(source_param,source)
                .appendQueryParameter(sort_param,sort_by).build();

        URL url=null;
        try
        {
            url=new URL(builduri.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}


