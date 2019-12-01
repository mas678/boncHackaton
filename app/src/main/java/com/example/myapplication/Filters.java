package com.example.myapplication;

import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters extends AppCompatActivity implements View.OnClickListener {

    private Button ok;
    private Button off;
    private CheckBox rent;
    private CheckBox invest;
    private String LOG_TAG = "aaaaaaaaaaaaaaaaaaaaa";

    public static double CalculationDistance(LatLng StartP, LatLng EndP) {
        return CalculationDistanceByCoord(StartP.latitude, StartP.longitude, EndP.latitude, EndP.longitude);
    }

    private static double CalculationDistanceByCoord(double startPointLat,double startPointLon,double endPointLat,double endPointLon){
        float[] results = new float[1];
        Location.distanceBetween(startPointLat, startPointLon, endPointLat, endPointLon, results);
        return results[0];
    }

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
        ForAll.index = null;
        Intent intent;
        intent = new Intent(this, MapsClickActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent, intent3;
        switch (v.getId()) {
            case R.id.allOff:
                ForAll.cords = ForAll.best;
                intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                break;
            case R.id.activity_to_write_1:
            case R.id.Ok:
                EditText _radix = findViewById(R.id.activity_to_write_1);
                EditText _priceFrom = findViewById(R.id.activity_to_write_2);
                EditText _priceTo = findViewById(R.id.activity_to_write_3);
                int priceFrom;
                int priceTo;
                int radix;
                try {
                    priceFrom = Integer.parseInt(_priceFrom.getText().toString());
                    radix = Integer.parseInt(_radix.getText().toString());
                    priceTo = Integer.parseInt(_priceTo.getText().toString());
                } catch (NumberFormatException e) {
                        Toast.makeText(
                                Filters.this, "Поля заполнены некорректно!",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                }
                ForAll.cords = new HashMap<>();
                for (Map.Entry<LatLng, List<Place>> placeList : ForAll.best.entrySet()) {
                    for (Place place : placeList.getValue()) {
                        if ((place.getInvest() && !rent.isChecked())
                                || (!place.getInvest() && !invest.isChecked())) {
                            if ((place.getPrice() > priceTo) || (place.getPrice() < priceFrom)
                                    || (radix < CalculationDistance(ForAll.index, place.getCords()))) {
                                continue;
                            }
                            List<Place> filtering = ForAll.cords.get(placeList.getKey());
                            if (filtering == null) {
                                filtering = new ArrayList<>();
                            }
                            filtering.add(place);
                            ForAll.cords.put(placeList.getKey(), filtering);
                        }
                    }
                }
                intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
