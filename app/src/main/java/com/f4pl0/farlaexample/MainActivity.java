package com.f4pl0.farlaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.f4pl0.farla.FARLA;
import com.f4pl0.farla.FarlaGetRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FarlaGetRequest(this).
    }
}
