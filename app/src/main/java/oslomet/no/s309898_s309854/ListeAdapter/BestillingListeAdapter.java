package oslomet.no.s309898_s309854.ListeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oslomet.no.s309898_s309854.DatabaseHjelper;
import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;


public class BestillingListeAdapter extends ArrayAdapter<Bestilling> {

    private Context context;
    private int resource;
    private ArrayList<Bestilling> bestillingArrayList;
    DatabaseHjelper databaseHjelper;

    /**
     * Holder variabler i View-et
     */
    private static class ViewHolder {
        TextView restaurantNavn;
        TextView tid;
        TextView venner;

    }

    // Konstruktør
    public BestillingListeAdapter(Context context, int resource, ArrayList<Bestilling> bestillingArrayList) {

        super(context, resource, bestillingArrayList);
        databaseHjelper = new DatabaseHjelper(context);
        this.bestillingArrayList = bestillingArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int id_best = getItem(position).getId();
        Bestilling best = databaseHjelper.getBestilling(id_best);
        String tid = best.getDato() + " " + best.getKlokkeslett();
        Restaurant restaurant = databaseHjelper.hentRestaurant(best.getRestaurant_id());
        String restaurantNavn = restaurant.getNavn();

        List<Venn> venner = databaseHjelper.hentVenner_Bestilling(best);
        List<String> vennNavn = new ArrayList<>();
        for (int i = 0; i < venner.size(); i++) {
            vennNavn.add(venner.get(i).getFornavn() + " " + venner.get(i).getEtternavn());
        }
        //ViewHolder object
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.restaurantNavn = convertView.findViewById(R.id.r_navn);
            holder.tid = convertView.findViewById(R.id.bestilling_dato);
            holder.venner = convertView.findViewById(R.id.b_venner);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.restaurantNavn.setText("Restaurant: " + restaurantNavn);
        holder.tid.setText("Tid: " + tid);
        holder.venner.setText("Venner: \n" + toString(vennNavn));
        return convertView;
        }

    // Hjelp metoder
    public String toString(List<String> venner) {
        StringBuilder builder = new StringBuilder();
        for (String v : venner) {
            builder.append(v).append("\n");
        }
        return builder.toString();
    }

}
