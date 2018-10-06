package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void byyTilRestaurantAktivitet(View view ){
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
