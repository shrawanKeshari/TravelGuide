package com.example.sonu.travelguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sonu.travelguide.Database.PlaceDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceListActivity extends AppCompatActivity {

    PlaceDataSource placeDataSource;
    private List<Place> places;
    ArrayList<Long> id_array;
    ArrayList<String> title_array, image_array, location_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeDataSource = new PlaceDataSource(this);
        placeDataSource.open();

        places = placeDataSource.findAll();
        if(places.size() == 0){
            initializeArray();
            createData();
            places = placeDataSource.findAll();
        }

        refreshDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        placeDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        placeDataSource.close();
    }

    private void initializeArray(){
        id_array = new ArrayList<Long>(Arrays.asList((long)1,(long)2,(long)3,
                (long)4,(long)5,(long)6));
        title_array = new ArrayList<String >(Arrays.asList("Akshardham","India Gate",
                "Lotus Temple","Jantar Mantar","Qutub Minar","Raj Ghat"));
        image_array = new ArrayList<String>(Arrays.asList("akshardham","india_gate",
                "lotus_temple","jantar_mantar","qutub_minar","raj_ghat"));
        location_array = new ArrayList<String >(Arrays.asList(
                "Noida Mor, Pandav Nagar, New Delhi, Delhi 110092",
                "Rajpath Marg, India Gate, New Delhi, Delhi 110001",
                "Lotus Temple Rd, Bahapur, Shambhu Dayal Bagh, Kalkaji, New Delhi, Delhi 110019",
                "Sansad Marg, Connaught Place, New Delhi, Delhi 110001",
                "Mehrauli, New Delhi, Delhi 110030",
                "Behind Red Fort, New Delhi, Delhi 110006"));
    }

    private void createData(){
        List<Place> placeList = new ArrayList<Place>();
        int length = id_array.size();
        for(int i = 0; i < length; i++){
            Place place = new Place();
            place.setId(id_array.get(i));
            place.setName(title_array.get(i));
            place.setImage(image_array.get(i));
            place.setLocation(location_array.get(i));
            placeList.add(place);
        }

        for(Place place : placeList){
            placeDataSource.create(place);
        }
    }

    public void refreshDisplay(){
        ArrayAdapter<Place> adapter = new PlaceAdapter(this, R.layout.item_list, places);
        ListView lv = (ListView) findViewById(R.id.placeListView);
        lv.setAdapter(adapter);
    }
}
