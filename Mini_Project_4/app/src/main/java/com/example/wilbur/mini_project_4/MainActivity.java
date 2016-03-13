package com.example.wilbur.mini_project_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask timerTask;
    public ArrayList<Location> locationArrayList;
    public static LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Parse.initialize(getApplicationContext(), "IU2D1ej4N8QcYVLhzYLU5Kpmbz7j0N9KBauQ8a1A", "w6j6KHFyEi1LLCymib2XcKXr9D2LmsZ7qFVUhE6P");

        // TODO: PARSE STUFF, QUERY IN THE ENTERDETAILSACTIVITY CLASS?

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        locationArrayList = new ArrayList<>();
//        Location loc = new Location("hi", "hi2", R.drawable.toyota_supra);
//        locationArrayList.add(loc);
//        Location loc2 = new Location("test", "test2", R.drawable.toyota_supra);
//        locationArrayList.add(loc2);
//        Location loc3 = new Location("test2", "test5", R.drawable.toyota_supra);
//        locationArrayList.add(loc3);
//        Location loc4 = new Location("test3", "test4", R.drawable.toyota_supra);
//        locationArrayList.add(loc4);
        locationAdapter = new LocationAdapter(this, locationArrayList);
        recyclerView.setAdapter(locationAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnterDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
//        try {
//            timer = new Timer();
//            timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    // TODO: REFRESH STUFF
//                }
//            };
//            timer.schedule(timerTask, 15000, 15000);
//        } catch (IllegalStateException e) {
//            android.util.Log.i("Error", "Error with resume");
//        }
    }

    public void refresh() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Img");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    ArrayList<Location> locationArrayList = new ArrayList<Location>();
                    for (ParseObject parseObject : objects) {
                        Log.d("Parse", "starting parse stuff");
                        String name = parseObject.getString(EnterDetailsActivity.NAME_KEY);
                        String description = parseObject.getString(EnterDetailsActivity.DESCRIPTION_KEY);
                        // TODO: image stuff
                        byte[] uploadedImage = parseObject.getBytes(EnterDetailsActivity.IMAGE_KEY);
                        Location loc = new Location(name, description, uploadedImage);
                        locationArrayList.add(loc);
                    }
                    locationAdapter.setList(locationArrayList);
                    locationAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Error", "Could not do parse stuff because of " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        //timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
