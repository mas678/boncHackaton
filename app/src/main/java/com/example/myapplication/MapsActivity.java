package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, View.OnClickListener {

    private GoogleMap mMap;
    ImageButton myBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myBut = (ImageButton) findViewById(R.id.filter);
        myBut.setOnClickListener(this);
        ForAll.cords = ForAll.best;
        ForAll.cords = ForAll.best;
    }

    public List<Place> getMyPlace(LatLng cord) {
        return ForAll.cords.get(cord);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        LatLng spb = new LatLng(59.939095, 30.315868);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spb));
        Place main = null;
        Toast.makeText(
                MapsActivity.this, "Количество предложений - " + ForAll.places.size(),
                Toast.LENGTH_LONG
        ).show();
        for (LatLng key: ForAll.cords.keySet()) {
            MarkerOptions mar = new MarkerOptions();
            mMap.addMarker(mar.position(key)
                    .title(ForAll.cords.get(key).get(0).toString()));
        }
        mMap.setOnMapClickListener(this);
    }

    public void reRoll() {
        onMapReady(mMap);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (!ForAll.cords.containsKey(latLng)) {
            Intent intent = new Intent(this, Try.class);
            startActivity(intent);
        } else {
            showInformation(ForAll.cords.get(latLng));
        }
    }

    private void showInformation(List<Place> places) {
        finish();
//        for (Place place: places) {
//
//        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.filter) {
            Intent intent = new Intent(this, Filters.class);
            startActivity(intent);
            onMapReady(mMap);
        }
    }
}
