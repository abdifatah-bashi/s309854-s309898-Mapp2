package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import oslomet.no.s309898_s309854.modeller.Restaurant;


public class RedigerRestaurantAktivitet extends AppCompatActivity {

    EditText navn;
    EditText adress;
    EditText telefon;
    EditText type;
   // EditText i;
   // int id;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_rediger_restaurant);

        databaseHelper = new DatabaseHelper(this);

        navn = (EditText)findViewById(R.id.rest_navn_edit);
        adress = (EditText)findViewById(R.id.rest_adresse_edit);
        telefon = (EditText)findViewById(R.id.rest_telefon_edit);
        type = (EditText)findViewById(R.id.rest_type_edit);

        navn.setText(getIntent().getStringExtra("navn"));
        adress.setText(getIntent().getStringExtra("adress"));
        telefon.setText(getIntent().getStringExtra("telefon"));
        type.setText(getIntent().getStringExtra("type"));

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Oppdater Restaurant");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lagre_toolbar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.save:

                String navn_res_Edit = navn.getText().toString();
                String adress_res_Edit = adress.getText().toString();
                String telefon_res_Edit = telefon.getText().toString();
                String type_res_Edit = type.getText().toString();


                int ID;
                Restaurant restaurant;
                    ID = getIntent().getIntExtra("Id", -1);
                    restaurant = databaseHelper.getRestaurant(ID);
                    restaurant.setNavn(navn_res_Edit);
                    restaurant.setAdresse(adress_res_Edit);
                    restaurant.setTelefon(telefon_res_Edit);
                    restaurant.setType(type_res_Edit);
                    databaseHelper.updateRestaurant(restaurant);


                Intent i = new Intent(this, RestaurantAktivitet.class);
                startActivity(i);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }



}
