package com.example.myapplication;

import android.location.Geocoder;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ForAll {
    static List<Place> places;
    static Map<LatLng, List<Place>> cords;
    static Map<LatLng, List<Place>> best;
    static LatLng index;
    static Integer rad;
}
