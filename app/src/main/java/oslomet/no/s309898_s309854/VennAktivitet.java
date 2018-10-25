package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import oslomet.no.s309898_s309854.ListeAdapter.VennListeAdapter;
import oslomet.no.s309898_s309854.modeller.Venn;


public class VennAktivitet extends AppCompatActivity {

    Button addFriendBtn;
    ListView listView;
    DatabaseHjelper databaseHjelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_venn);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Venner");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseHjelper = new DatabaseHjelper(this);
        addFriendBtn = findViewById(R.id.addFriendBtn);

        listView = findViewById(R.id.restListView);
        ArrayList<Venn> venner = databaseHjelper.hentAlleVenner();

        VennListeAdapter adapter = new VennListeAdapter(this, R.layout.venn_liste_view, venner);
        listView.setAdapter(adapter);
    }

    public void onFriendAdd(View view) {
        Intent intent = new Intent(this, NyVennAktivitet.class);
        startActivity(intent);

    }
}

