package oslomet.no.s309898_s309854;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import oslomet.no.s309898_s309854.modeller.Restaurant;

public class MainActivity extends AppCompatActivity {

    public final static String PROVIDER = "oslomet.no.s309898_s309854";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/venn");

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*ContentValues values = new ContentValues();
        //values.put(ID_RESTAURANT, restaurant.getID());
        values.put("navn", "Bla");
        values.put("adresse", "Oslocity");
        values.put("telefon", "12121212");
        values.put("type", "Food");
        getContentResolver().insert(CONTENT_URI,values);*/

     /*  ContentValues values = new ContentValues();
        //values.put(ID_RESTAURANT, restaurant.getID());
        values.put("fornavn", "Siham");
        values.put("etternavn", "Sidali");
        values.put("telefon", "98616338");


         getContentResolver().insert(CONTENT_URI,values);*/

    }

    public void byttTilRestaurantAktivitet(View view ){
        Intent intent = new Intent(this, RestaurantAktivitet.class);
        startActivity(intent);


    }

    public void byttTilVennAktivitet (View view ){
        Intent intent = new Intent(this, VennAktivitet.class);
        startActivity(intent);


    }

    public void byttTilBestillingAktivitet (View view ){
        Intent intent = new Intent(this, BestillingAktivitet.class);
        startActivity(intent);


    }

    public void byttTilInstillingAktivitet (View view ){
        Intent intent = new Intent(this, InstillingAktivitet.class);
        startActivity(intent);

    }
}
