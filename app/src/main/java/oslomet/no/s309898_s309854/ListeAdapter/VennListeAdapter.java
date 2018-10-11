package oslomet.no.s309898_s309854.ListeAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import oslomet.no.s309898_s309854.DatabaseHelper;
import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.RedigerVennAktivitet;
import oslomet.no.s309898_s309854.RestaurantAktivitet;
import oslomet.no.s309898_s309854.VennAktivitet;
import oslomet.no.s309898_s309854.modeller.Venn;

public class VennListeAdapter extends ArrayAdapter<Venn> {

    private Context context;
    private int resource;
    private ArrayList<Venn> friendsArrayList;
    DatabaseHelper databaseHelper;



    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView friendName;

    }


    public VennListeAdapter(Context context, int resource, ArrayList<Venn> freindsArrayList) {

        super(context, resource, freindsArrayList);

        databaseHelper=new DatabaseHelper(context);

        this.friendsArrayList = freindsArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String fornavn = getItem(position).getFornavn();
        String etternavn =getItem(position).getEtternavn() ;
        String telefon =getItem(position).getTelefon() ;


        //Create statistic object
        final Venn freind = new Venn(fornavn , etternavn, telefon );



        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder= new ViewHolder();

            holder.friendName = convertView.findViewById(R.id.restaurantName);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        // On edit
        ImageView editBtn = convertView.findViewById(R.id.edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RedigerVennAktivitet.class);
                intent.putExtra("Id", friendsArrayList.get(position).getId());
                intent.putExtra("fornavn", friendsArrayList.get(position).getFornavn());
                intent.putExtra("etternavn", friendsArrayList.get(position).getEtternavn());
                intent.putExtra("telefon", friendsArrayList.get(position).getTelefon());
                context.startActivity(intent);
                notifyDataSetChanged();


            }
        });


        ImageView deleteBtn = convertView.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RestaurantAktivitet.class);
                intent.putExtra("Id", friendsArrayList.get(position).getId());
                int id=friendsArrayList.get(position).getId();
                onDeleteConfirm(id);
                notifyDataSetChanged();


            }
        });


        holder.friendName.setText(freind.getFornavn() + " " + freind.getEtternavn());
        return convertView;
    }

    public void onDeleteConfirm(final int id){

        AlertDialog.Builder builder;
        String message = context.getResources().getString(R.string.alert_friend_msg);//"Vil du virkelig avslutte spillet?";
        //String message = "\t" + getResources().getString(R.string.spillScore) + scoreString + getResources().getString(R.string.spillIgjen);

        builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        builder.setCancelable(false);
        TextView textView = new TextView(context);
        textView.setTextSize(18);
        textView.setText(message);
        textView.setTextColor(context.getResources().getColor(R.color.red));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(24,20,0,0);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

        builder.setView(textView);


        builder
                .setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.slettVenn(id);

                        Intent intent=new Intent(context,VennAktivitet.class);
                        context.startActivity(intent);


                    }
                })
                .setNegativeButton(R.string.nei, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "NEI", Toast.LENGTH_LONG).show();


                    }
                })
                .show();





    }





}