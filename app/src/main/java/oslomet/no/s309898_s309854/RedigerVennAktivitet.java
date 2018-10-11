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

import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class RedigerVennAktivitet extends AppCompatActivity {

    EditText fornavn;
    EditText etternavn ;
    EditText telefon ;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_rediger_venn);

        databaseHelper=new DatabaseHelper(this);

        fornavn = (EditText)findViewById(R.id.fornavn_edit);
        etternavn = (EditText)findViewById(R.id.etternavn_edit);
        telefon = (EditText)findViewById(R.id.telefon_edit);

        fornavn.setText(getIntent().getStringExtra("fornavn"));
        etternavn.setText(getIntent().getStringExtra("etternavn"));
        telefon.setText(getIntent().getStringExtra("telefon"));


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Oppdater Venn");
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

                String fornavn_Edit = fornavn.getText().toString();
                String etternavn_Edit = etternavn.getText().toString();
                String telefon_Edit = telefon.getText().toString();



                int ID;
                Venn venn;

                ID = getIntent().getIntExtra("Id", -1);
                venn = databaseHelper.getVenn(ID);
                venn.setFornavn(fornavn_Edit);
                venn.setEtternavn(etternavn_Edit);
                venn.setTelefon(telefon_Edit);

                databaseHelper.updateVenn(venn);


                Intent i = new Intent(this, VennAktivitet.class);
                startActivity(i);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
