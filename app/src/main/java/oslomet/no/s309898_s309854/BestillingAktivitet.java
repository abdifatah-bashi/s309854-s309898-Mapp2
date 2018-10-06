package oslomet.no.s309898_s309854;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import oslomet.no.s309898_s309854.ListeAdapter.BestillingListeAdapter;
import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class BestillingAktivitet extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_bestilling);

        listView = findViewById(R.id.bestilling_list_view);

        // Restaurant
        Restaurant restaurant1= new Restaurant("Fridays");
        Restaurant restaurant2 = new Restaurant("Vulkan");
        // Venner
        ArrayList<Venn> liste = new ArrayList<>();
        liste.add(new Venn("Ross", "Galer ", "45454567"));
        liste.add(new Venn("Monica", "Galer ", "45454567"));
        liste.add(new Venn("Joey", "Tribbiani ", "45454567"));
        liste.add(new Venn("Phoebe ", "buffay ", "45454567"));
        // Dato
        String dato = "10-10-2018";
        // tid
        String tid = "18:00";
        // Opprett list av bestillinger
        ArrayList<Bestilling> bestillinger = new ArrayList<>();
        bestillinger.add(new Bestilling(restaurant1, liste, dato, tid) );
        bestillinger.add(new Bestilling(restaurant2, liste, dato, tid) );
        bestillinger.add(new Bestilling(restaurant1, liste, dato, tid) );


        BestillingListeAdapter adapter = new BestillingListeAdapter(this, R.layout.bestilling_list_view, bestillinger);
        listView.setAdapter(adapter);


    }


    public void onNyBestilling(View view ){
        Intent intent = new Intent(this, NyBestillingAktivitet.class);
        startActivity(intent);

    }



}