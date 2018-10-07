package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import oslomet.no.s309898_s309854.ListeAdapter.RestaurantListeAdapter;
import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.modeller.Restaurant;

public class RestaurantAktivitet extends AppCompatActivity {

    ListView listView;
    Button addRestaurantBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_restaurant);  //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Restauranter");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        addRestaurantBtn = findViewById(R.id.addBtn);

        listView = findViewById(R.id.restListView);
        ArrayList<Restaurant> restauranter = new ArrayList<>();
        restauranter.add(new Restaurant("Aker Brygge", "Dokkveien 12 ", "45454545", "Spesiell"));
        restauranter.add(new Restaurant("Nedre løkka", "Dokkveien 12 ", "45454545", "Dyr"));
        restauranter.add(new Restaurant("Gamle Rådhuset", "Dokkveien 12 ", "45454545", "Billig"));


        RestaurantListeAdapter adapter = new RestaurantListeAdapter(this, R.layout.restaurant_liste_view, restauranter);
        listView.setAdapter(adapter);
    }

    public void onRestaurantAdd(View view ){
        Intent intent = new Intent(this, NyRestaurantAktivitet.class);
        startActivity(intent);

    }
}
