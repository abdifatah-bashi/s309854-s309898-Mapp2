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

import oslomet.no.s309898_s309854.DatabaseHjelper;
import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.RedigerVennAktivitet;
import oslomet.no.s309898_s309854.RestaurantAktivitet;
import oslomet.no.s309898_s309854.VennAktivitet;
import oslomet.no.s309898_s309854.modeller.Venn;

public class VennListeAdapter extends ArrayAdapter<Venn> {

    private Context context;
    private int resource;
    private ArrayList<Venn> vennerArrayList;
    DatabaseHjelper databaseHjelper;
    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView vennNavn;
    }
    public VennListeAdapter(Context context, int resource, ArrayList<Venn> vennerList) {
        super(context, resource, vennerList);
        databaseHjelper =new DatabaseHjelper(context);
        this.vennerArrayList = vennerList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String fornavn = getItem(position).getFornavn();
        String etternavn =getItem(position).getEtternavn() ;
        String telefon =getItem(position).getTelefon() ;
        final Venn venn = new Venn(fornavn , etternavn, telefon );

        //ViewHolder object
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder= new ViewHolder();
            holder.vennNavn = convertView.findViewById(R.id.restaurantName);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        // Rediger
        ImageView editBtn = convertView.findViewById(R.id.edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RedigerVennAktivitet.class);
                intent.putExtra("Id", vennerArrayList.get(position).getId());
                intent.putExtra("fornavn", vennerArrayList.get(position).getFornavn());
                intent.putExtra("etternavn", vennerArrayList.get(position).getEtternavn());
                intent.putExtra("telefon", vennerArrayList.get(position).getTelefon());
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        // Slett
        ImageView deleteBtn = convertView.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RestaurantAktivitet.class);
                intent.putExtra("Id", vennerArrayList.get(position).getId());
                int id= vennerArrayList.get(position).getId();
                onBekreftSlett(id, position);
                notifyDataSetChanged();
            }
        });

        holder.vennNavn.setText(venn.getFornavn() + " " + venn.getEtternavn());
        return convertView;
    }

    public void onBekreftSlett(final int id, final int position){

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
                        databaseHjelper.slettVenn(id);
                        vennerArrayList.remove(position);
                        Intent intent=new Intent(context,VennAktivitet.class);
                        context.startActivity(intent);
                        ((VennAktivitet)context).finish();
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