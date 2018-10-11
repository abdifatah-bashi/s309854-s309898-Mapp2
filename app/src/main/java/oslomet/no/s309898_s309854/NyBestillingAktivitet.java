package oslomet.no.s309898_s309854;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Bestilling_Venner;
import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class NyBestillingAktivitet extends AppCompatActivity {

    private Button tidBtn;
    private Spinner spinner;
    private Button datoBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
DatabaseHelper databaseHelper;

    Button leggVennBtn;
    Button bestill;
    TextView valgtVenner;
    boolean[] checkedItems;
    ArrayList<Integer> venner_pos = new ArrayList<>();
    ArrayList<String> venner;
    ArrayList<Integer> f_e= new ArrayList<>();
    String v_pos="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_ny_bestilling);
        databaseHelper=new DatabaseHelper(this);
        datoBtn =  findViewById(R.id.velg_dato);
        tidBtn = findViewById(R.id.velg_tid);
        spinner= findViewById(R.id.spinner_restaurant);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        bestill =findViewById(R.id.bestillingBtn);


        velgDato(datoBtn);
        visRestauranterDropDown(spinner);

        // Venn liste
        final ArrayList<Venn> liste_venner = databaseHelper.hentAlleVenner();



        venner = new ArrayList<>();
        for (int i = 0; i <liste_venner.size() ; i++) {
            f_e.add(liste_venner.get(i).getId());
            venner.add(liste_venner.get(i).getFornavn() + " " + liste_venner.get(i).getEtternavn());
        }
        Log.i("Test", Arrays.toString(f_e.toArray()));
        CharSequence[] cs = venner.toArray(new CharSequence[venner.size()]);
         visVennerDropDown(cs);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Legg til ny bestilling");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    public void visVennerDropDown(final CharSequence[] vennListe){
        leggVennBtn = (Button) findViewById(R.id.venner_label);
        valgtVenner = (TextView) findViewById(R.id.tvItemSelected);







        checkedItems = new boolean[vennListe.length];

        leggVennBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NyBestillingAktivitet.this, R.style.AlertDialog);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(vennListe , checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            venner_pos.add(position);
                            Log.i("pos",String.valueOf(position));
                        }else{
                            venner_pos.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);

                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //String s = vennListe.toString();

                        String item = "";
                        for (int i = 0; i < venner_pos.size(); i++) {
                            item = item + vennListe[venner_pos.get(i)];
                            v_pos += f_e.get(venner_pos.get(i));
                            if (i != venner_pos.size() - 1) {
                                item = item + ", ";
                            }
                        }


                        valgtVenner.setText("Valgt venner: " + item);
                    }
                });


                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });



                AlertDialog mDialog = mBuilder.create();

                mDialog.show();
            }
        });


    }

    public void velgDato(final Button datoBtn){

        datoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NyBestillingAktivitet.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                ;

                String date = month + "/" + day + "/" + year;

                datoBtn.setText(date);
            }
        };


    }

    public void velgTid(View view) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(NyBestillingAktivitet.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                tidBtn.setText(hourOfDay + ":" + minute);

            }
        }, hour, minute, false);
        timePickerDialog.show();


    }


    public void visRestauranterDropDown(final Spinner spinner){
       // String[] restaurantList = new String[]{"Fridays ", "Der Peppern Gror","Gamle Rådhuset","Vulkan Fisk", "Fiskeriet Youngstorget"};
        ArrayList<Restaurant> list_restauranter=databaseHelper.hentAlleRestauranter();
        List<String> restauranter = new ArrayList<>();

        for(Restaurant res:list_restauranter){
            restauranter.add(res.getNavn());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, restauranter);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try{

                }catch (Exception e){
                    e.printStackTrace();

                }
              //  Log.i("NR", spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void addBestilling(View v) {

        int restaurant_id = databaseHelper.hentRestaurant(spinner.getSelectedItem().toString());

        Bestilling best = new Bestilling();

        best.setDato(datoBtn.getText().toString());
        best.setKlokkeslett(tidBtn.getText().toString());
        best.setRestaurant_id(restaurant_id);
        databaseHelper.addBestilling(best);

        int id = databaseHelper.getLastBestilling();
       // Log.i("BVID", String.valueOf(id));
            Bestilling bestilling = databaseHelper.getBestilling(id);
            //Log.i("BBB", String.valueOf(bestilling.getId()));
            String[] v_positions = v_pos.split("");

            for (int i = 1; i < v_positions.length; i++) {
                Venn venn = databaseHelper.getVenn(Integer.valueOf(v_positions[i]));
                databaseHelper.addVenn_Bestilling(bestilling, venn);
            }
            Intent i = new Intent(this, BestillingAktivitet.class);
            startActivity(i);
            finish();

        }


}
