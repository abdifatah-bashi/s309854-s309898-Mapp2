package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

public class RedigerVennAktivitet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_rediger_venn);

        EditText name = findViewById(R.id.first_name_edit);
        EditText address = findViewById(R.id.last_name_edit);
        EditText phone = findViewById(R.id.phone_edit);

        name.setText(getIntent().getStringExtra("firstName"));
        address.setText(getIntent().getStringExtra("lastName"));
        phone.setText(getIntent().getStringExtra("phone"));


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Oppdater Profil");
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
}
