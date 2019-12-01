package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button toMaps;
    private Button toCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ForAll.places == null) {
            ForAll.places = new ArrayList<>();
            ForAll.cords = new HashMap<>();
            ForAll.best = new HashMap<>();
        }
        setContentView(R.layout.activity_main);
        toCreate = (Button) findViewById(R.id.present);
        toCreate.setOnClickListener(this);
        toMaps = (Button) findViewById(R.id.search);
        toMaps.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.present:
                intent = new Intent(this, PresentActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                intent = new Intent(this, Filters.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
