package oslomet.no.s309898_s309854;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import oslomet.no.s309898_s309854.modeller.Venn;

public class NyVennAktivitet extends AppCompatActivity {
    Button addButton;
    EditText fornavn;
    EditText etternavn;
    EditText telefon;

    DatabaseHelper databaseHelper;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_ny_venn);

        databaseHelper=new DatabaseHelper(this);

        fornavn = (EditText)findViewById(R.id.fornavn);
        etternavn = (EditText)findViewById(R.id.etternavn);
        telefon = (EditText)findViewById(R.id.telefon);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lagre Venn");
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

                String venn_fornavn = fornavn.getText().toString();
                String venn_etternavn = etternavn.getText().toString();
                String venn_telefon = telefon.getText().toString();


                Venn venn = new Venn(venn_fornavn, venn_etternavn, venn_telefon);
                databaseHelper.addVenn(venn);


                Toast.makeText(this, fornavn + " " + etternavn + " er lagt inn", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, VennAktivitet.class);
                startActivity(i);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


}
