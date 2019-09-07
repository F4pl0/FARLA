package com.f4pl0.farla;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FARLA {

    public static int
            MALFORMED_URL = 1,
            NETWORK_ERROR = 2;

    public interface onResult{
        void resultSuccess(String response);
        void resultFailure(int reason);
    }

    public void GET(String URL, String body, onResult onResult){
        try{
            java.net.URL url = new URL(URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            onResult.resultSuccess(readStream(in));
            urlConnection.disconnect();
        }
        catch (MalformedURLException e){
            onResult.resultFailure(MALFORMED_URL);
        }catch (IOException e){
            onResult.resultFailure(NETWORK_ERROR);
        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
