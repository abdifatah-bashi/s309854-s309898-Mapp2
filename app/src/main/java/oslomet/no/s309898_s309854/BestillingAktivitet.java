package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import oslomet.no.s309898_s309854.ListeAdapter.BestillingListeAdapter;
import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class BestillingAktivitet extends AppCompatActivity {

    private ListView listView;
    DatabaseHjelper databaseHjelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_bestilling);
        databaseHjelper =new DatabaseHjelper(this);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bestillinger");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = findViewById(R.id.bestilling_list_view);

        // Hent list av bestillinger
        ArrayList<Bestilling> bestillinger = (ArrayList) databaseHjelper.hentAlleBestillinger();
        BestillingListeAdapter adapter = new BestillingListeAdapter(this, R.layout.bestilling_list_view, bestillinger);
        listView.setAdapter(adapter);
    }


    public void onNyBestilling(View view ){
        Intent intent = new Intent(this, NyBestillingAktivitet.class);
        startActivity(intent);
    }

}