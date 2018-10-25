package oslomet.no.s309898_s309854;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import oslomet.no.s309898_s309854.modeller.Restaurant;

public class MainActivity extends AppCompatActivity {

    public final static String PROVIDER = "oslomet.no.s309898_s309854";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/restaurant");

    DatabaseHjelper databaseHjelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sjekker SEND_SMS tillatelse
        // Spør tillatelse hvis vi ikke har
        if (checkPermission(android.Manifest.permission.SEND_SMS)) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        }
    }

    public void byttTilRestaurantAktivitet(View view) {
        Intent intent = new Intent(this, RestaurantAktivitet.class);
        startActivity(intent);


    }

    public void byttTilVennAktivitet(View view) {
        Intent intent = new Intent(this, VennAktivitet.class);
        startActivity(intent);


    }

    public void byttTilBestillingAktivitet(View view) {
        Intent intent = new Intent(this, BestillingAktivitet.class);
        startActivity(intent);


    }

    public void byttTilInstillingAktivitet(View view) {
        Intent intent = new Intent(this, InstillingAktivitet.class);
        startActivity(intent);

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
