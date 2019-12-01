package com.example.myapplication;

import android.content.Intent;
import android.location.Location;
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

    Button ok;
    Button off;
    CheckBox rent;
    CheckBox invest;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allOff:
                ForAll.cords = ForAll.best;
                Intent intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                break;
            case R.id.Ok:
                EditText _radix = findViewById(R.id.activity_to_write_1);
                EditText _priceFrom = findViewById(R.id.activity_to_write_2);
                EditText _priceTo = findViewById(R.id.activity_to_write_3);
                int priceFrom = (int)-1e9;
                int priceTo = (int)-1e9;
                int radix = (int) 1e9;
                try {
                    priceFrom = Integer.parseInt(_priceFrom.getText().toString());
                } catch (NumberFormatException e) {
                    if (_priceFrom.getText().toString().length() != 0) {
                        Toast.makeText(
                                Filters.this, "Поля заполнены некорректно!",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }
                }
                try {
                    radix = Integer.parseInt(_priceFrom.getText().toString());
                } catch (NumberFormatException e) {
                    if (_radix.getText().toString().length() != 0) {
                        Toast.makeText(
                                Filters.this, "Поля заполнены некорректно!",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }
                }
                try {
                    priceTo = Integer.parseInt(_priceFrom.getText().toString());
                } catch (NumberFormatException e) {
                    if (_priceTo.getText().toString().length() != 0) {
                        Toast.makeText(
                                Filters.this, "Поля заполнены некорректно!",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }
                }
                Intent intent;
                intent = new Intent(this, MapsClickActivity.class);
                startActivity(intent);
                ForAll.cords = new HashMap<>();
                for (Map.Entry<LatLng, List<Place>> placeList : ForAll.best.entrySet()) {
                    for (Place place : placeList.getValue()) {
                        if ((place.getInvest() && invest.isChecked())
                                || (!place.getInvest() && rent.isChecked())) {
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
                Intent intent1 = new Intent(this, MapsActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
