package com.f4pl0.farlaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.f4pl0.farla.FARLA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FARLA farla = new FARLA();
        farla.GET("http://webhook.site/7a7ca4d3-e601-4950-83bf-47e7bb769dd0", "asd", new FARLA.onResult() {
            @Override
            public void resultSuccess(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void resultFailure(int reason) {
                Toast.makeText(MainActivity.this, "FAIL:"+reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
