package com.f4pl0.farlaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.f4pl0.farla.FarlaGetRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FarlaGetRequest(this)
                .setURL("https://google.com")
                .setListener(new FarlaGetRequest.onGetRequestListener() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int error) {

                    }
                }).execute();
    }
}
