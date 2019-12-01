package com.example.myapplication;
import android.content.Intent;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.support.v4.app.INotificationSideChannel;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MapsClickActivity;
import com.example.myapplication.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PresentActivity extends AppCompatActivity {
    private LatLng latLng = null;

    private String [] sp_arr = {"Аренда", "Инвестиции"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        ForAll.index = null;

    }
    public void Map_Click(View v){
        Intent new_class = new Intent(this, MapsClickActivity.class);
        startActivity(new_class);
        latLng = ForAll.index;
        return;
    }


    public void Click(View V){
        EditText _name= findViewById(R.id.activity_to_write4);
        EditText _area = findViewById(R.id.activity_to_write5);
        EditText _floor = findViewById(R.id.activity_to_write6);
        EditText _price = findViewById(R.id.activity_to_write7);
        EditText _phone = findViewById(R.id.activity_to_write8);
        EditText _comment = findViewById(R.id.activity_to_write9);
        String phone = _phone.getText().toString();
        String comment = _comment.getText().toString();
        String name = _name.getText().toString();
        Spinner _spinner = (Spinner) findViewById(R.id.spinner);
        String spinner = _spinner.getSelectedItem().toString();
        int area;
        int floor;
        int price;
        try{
            area = Integer.parseInt(_area.getText().toString());
            floor = Integer.parseInt(_floor.getText().toString());
            price = Integer.parseInt(_price.getText().toString());

        } catch(NumberFormatException e) {
            Toast.makeText(
                    PresentActivity.this, "Поля заполнены некорректно!",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        String[] array = {phone.trim(),comment.trim(),name.trim()};
        int i;
        for (i = 0; i < 3; i++){
            if (array[i].isEmpty()){
                break;
            }
        }
        if (i < 3){
            Toast.makeText(
                    PresentActivity.this, "Заполните все поля!",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
        if (ForAll.index == null){
            Toast.makeText(
                    PresentActivity.this, "Укажите место на карте!",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
        Place my = new Place(name, phone, ForAll.index,
                floor, area, spinner, price, comment, spinner.charAt(0) == 'A');
        insert(my);
        finish();
    }

    public void insert(Place place) {
        onPause();
        ForAll.places.add(place);
        List<Place> cordPlace = ForAll.best.get(place.getCords());
        if (cordPlace == null) {
            cordPlace = new ArrayList<>();
        }
        cordPlace.add(place);
        ForAll.best.put(place.getCords(), cordPlace);
    }

}
