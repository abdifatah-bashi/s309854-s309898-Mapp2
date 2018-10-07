package oslomet.no.s309898_s309854;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import oslomet.no.s309898_s309854.modeller.Venn;

public class NyBestillingAktivitet extends AppCompatActivity {

    private Button tidBtn;
    private Spinner spinner;
    private Button datoBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    Button leggVennBtn;
    TextView valgtVenner;
    boolean[] checkedItems;
    ArrayList<Integer> venner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_ny_bestilling);

        datoBtn =  findViewById(R.id.velg_dato);
        tidBtn = findViewById(R.id.velg_tid);
        spinner= findViewById(R.id.spinner_restaurant);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

        velgDato(datoBtn);
        visRestauranterDropDown(spinner);

        // Venn liste
        final ArrayList<Venn> liste = new ArrayList<>();
        liste.add(new Venn("Ross", "Galer ", "45454567"));
        liste.add(new Venn("Monica", "Galer ", "45454567"));
        liste.add(new Venn("Joey", "Tribbiani ", "45454567"));
        liste.add(new Venn("Phoebe ", "buffay ", "45454567"));


        ArrayList<String> venner = new ArrayList<>();
        for (int i = 0; i <liste.size() ; i++) {
            venner.add(liste.get(i).getFornavn() + " " + liste.get(i).getEtterNavn());
        }
        Log.i("Test", Arrays.toString(venner.toArray()));
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

    public void visVennerDropDown(final CharSequence [] vennListe){
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
                            venner.add(position);
                        }else{
                            venner.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);

                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < venner.size(); i++) {
                            item = item + vennListe[venner.get(i)];
                            if (i != venner.size() - 1) {
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


    public void visRestauranterDropDown(Spinner spinner){
        String[] restaurantList = new String[]{"Fridays ", "Der Peppern Gror","Gamle Rådhuset","Vulkan Fisk", "Fiskeriet Youngstorget"};
        List<String> restauranter = Arrays.asList(restaurantList);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
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
                //Log.i("NR", spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




}
