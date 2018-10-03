package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import oslomet.no.s309898_s309854.ListeAdapter.VennListeAdapter;
import oslomet.no.s309898_s309854.modeller.Venn;


public class VennAktivitet extends AppCompatActivity {


    Button addFriendBtn;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_venn);

        addFriendBtn = findViewById(R.id.addFriendBtn);

        listView = findViewById(R.id.restListView);
        ArrayList<Venn> venner = new ArrayList<>();
        venner.add(new Venn("Monica", "Galer ", "45454545"));
        venner.add(new Venn("Chandler", "Bing ", "45454545"));
        venner.add(new Venn("Ross", "Galer ", "45454545"));


        VennListeAdapter adapter = new VennListeAdapter(this, R.layout.venn_liste_view, venner);
        listView.setAdapter(adapter);
    }

    public void onFriendAdd(View view ){
        Intent intent = new Intent(this, NyVennAktivitet.class);
        startActivity(intent);

    }
}

