package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters extends AppCompatActivity implements View.OnClickListener {

    Button ok;
    Button off;
    CheckBox rent;
    CheckBox invest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        ok = (Button) findViewById(R.id.Ok);
        ok.setOnClickListener(this);
        off = (Button) findViewById(R.id.allOff);
        off.setOnClickListener(this);
        rent = (CheckBox) findViewById(R.id.isBook);
        invest = (CheckBox) findViewById(R.id.isInvest);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.allOff:
                ForAll.cords = ForAll.best;
                finish();
                break;
            case R.id.Ok:
                ForAll.cords = new HashMap<>();
                for (Map.Entry<LatLng, List<Place>> placeList : ForAll.best.entrySet()) {
                    for (Place place : placeList.getValue())
                        if (place.getInvest() && invest.isChecked()
                                || !place.getInvest() && rent.isChecked()) {
                            List<Place> filtering = ForAll.cords.get(placeList.getKey());
                            if (filtering == null) {
                                filtering = new ArrayList<>();
                            }
                            filtering.add(place);
                            ForAll.cords.put(placeList.getKey(), filtering);
                        }
                }
                finish();
                break;
            default:
                break;
        }
    }
}
