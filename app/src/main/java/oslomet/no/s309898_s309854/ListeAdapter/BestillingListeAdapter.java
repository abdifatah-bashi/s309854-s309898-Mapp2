package oslomet.no.s309898_s309854.ListeAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Venn;


public class BestillingListeAdapter extends ArrayAdapter<Bestilling> {

    private Context context;
    private int resource;
    private ArrayList<Bestilling> bestillingArrayList;




    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView restaurantName;
        TextView  tid;
        TextView venner;

    }


    public BestillingListeAdapter(Context context, int resource, ArrayList<Bestilling> bestillingArrayList) {

        super(context, resource, bestillingArrayList);


        this.bestillingArrayList = bestillingArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String tid = getItem(position).getDato() + " " + getItem(position).getKlokkeslett();
        String restaurantNavn = getItem(position).getRestaurant().getNavn();
        List<Venn> venner  = getItem(position).getVenner();
        List<String> vennNavn = new ArrayList<>();
        for (int i = 0; i <venner.size() ; i++) {
            vennNavn.add(venner.get(i).getFornavn() + " " + venner.get(i).getEtternavn() );
        }


        //Create bestilling object
        final Bestilling bestilling = new Bestilling(getItem(position).getRestaurant(), venner,
                getItem(position).getDato(), getItem(position).getKlokkeslett());



        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder= new ViewHolder();

            holder.restaurantName = convertView.findViewById(R.id.r_navn);
            holder.tid = convertView.findViewById(R.id.bestilling_dato);
            holder.venner = convertView.findViewById(R.id.b_venner);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.restaurantName.setText("Restaurant: "+ restaurantNavn);
        holder.tid.setText("Tid: " + tid);
        holder.venner.setText("Venner: \n" + toString(vennNavn));
        return convertView;
    }

    // Hjelp metoder
    public String toString(List<String> venner){
        StringBuilder builder = new StringBuilder();
        for(String v : venner) {
            builder.append(v).append("\n");

        }
        return builder.toString();
    }


}
