package oslomet.no.s309898_s309854;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oslomet.no.s309898_s309854.ListeAdapter.RestaurantListeAdapter;
import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.modeller.Restaurant;

public class RestaurantAktivitet extends AppCompatActivity {

    ListView listView;
    Button addRestaurantBtn;
    DatabaseHelper databaseHelper;
    Context context;




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


        databaseHelper = new DatabaseHelper(this);
        addRestaurantBtn = findViewById(R.id.addBtn);

        listView = findViewById(R.id.restListView);
        ArrayList<Restaurant> res = new ArrayList<>();

        res = (ArrayList) databaseHelper.hentAlleRestauranter();

        for(Restaurant rest:res) Log.i("Ind", "Id = "+ rest.getID());
        RestaurantListeAdapter adapter = new RestaurantListeAdapter(this, R.layout.restaurant_liste_view, res);
        listView.setAdapter(adapter);
    }


    public void onRestaurantAdd(View view) {
        Intent intent = new Intent(this, NyRestaurantAktivitet.class);
        startActivity(intent);

    }

    public void slettRestaurant(int id) {

        databaseHelper.slettRestaurant(id);


        Intent i = new Intent(this, RestaurantAktivitet.class);
        startActivity(i);
        finish();

    }
}

