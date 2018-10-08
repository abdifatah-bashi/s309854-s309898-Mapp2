package oslomet.no.s309898_s309854;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import oslomet.no.s309898_s309854.modeller.Restaurant;

public class NyRestaurantAktivitet extends AppCompatActivity {
    Button addButton;
    EditText navn;
    EditText adress;
    EditText telefon;
    EditText type;

    DatabaseHelper databaseHelper;

    public final static String PROVIDER = "oslomet.no.s309898_s309854";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/restaurant");

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_ny_restaurant);
        databaseHelper = new DatabaseHelper(this);


        navn = (EditText)findViewById(R.id.rest_navn);
        adress = (EditText)findViewById(R.id.rest_adresse);
        telefon = (EditText)findViewById(R.id.rest_telefon);
        type = (EditText)findViewById(R.id.rest_type);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lagre Restaurant");
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

                String navn_res = navn.getText().toString();
                String adress_res = adress.getText().toString();
                String telefon_res = telefon.getText().toString();
                String type_res = type.getText().toString();



                Restaurant restaurant = new Restaurant(navn_res, adress_res, telefon_res, type_res);
                databaseHelper.addRestaurant(restaurant);

               /* ContentValues values = new ContentValues();
                values.put("navn", navn_res);
                values.put("adresse", adress_res);
                values.put("telefon", telefon_res);
                values.put("type", type_res);
                getContentResolver().insert(CONTENT_URI,values);*/


                Toast.makeText(this, navn + " " + " Restaurant lagt til", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, RestaurantAktivitet.class);
                startActivity(i);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
