package com.example.wilbur.mini_project_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<CarCard> carCardArray = new ArrayList<>();

        CarCard toyota = new CarCard("Toyota Supra", "This is a nice 2015 car", false, R.drawable.toyota_supra);
        carCardArray.add(toyota);

        CarCard audi = new CarCard("Audi R8", "This is a nice 2016 car", false, R.drawable.audi_r8);
        carCardArray.add(audi);

        CarCard ferrari = new CarCard("Ferrari 458", "This is a nice race car", false, R.drawable.ferrari_458);
        carCardArray.add(ferrari);

        CarCard honda = new CarCard("Honda Odyssey", "This is a nice van", false, R.drawable.honda_odyssey);
        carCardArray.add(honda);

        CarCardAdapter carCardAdapter = new CarCardAdapter(getApplicationContext(), carCardArray);

        recyclerView.setAdapter(carCardAdapter);
    }
}
