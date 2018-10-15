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
import oslomet.no.s309898_s309854.RedigerRestaurantAktivitet;
import oslomet.no.s309898_s309854.RestaurantAktivitet;
import oslomet.no.s309898_s309854.modeller.Restaurant;


public class RestaurantListeAdapter extends ArrayAdapter<Restaurant> {

    private Context context;
    private int resource;
    private ArrayList<Restaurant> restaurantArrayList;
    DatabaseHjelper databaseHjelper;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView restaurantNavn;

    }
    public RestaurantListeAdapter(Context context, int resource, ArrayList<Restaurant> restaurantArrayList) {
        super(context, resource, restaurantArrayList);
        databaseHjelper = new DatabaseHjelper(context);
        this.restaurantArrayList = restaurantArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getNavn();
        final Restaurant restaurant = new Restaurant(name);
        //ViewHolder object
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder= new ViewHolder();

            holder.restaurantNavn = convertView.findViewById(R.id.restaurantName);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        // Rediger
        ImageView editBtn = convertView.findViewById(R.id.edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RedigerRestaurantAktivitet.class);
                intent.putExtra("Id", restaurantArrayList.get(position).getID());
                intent.putExtra("navn", restaurantArrayList.get(position).getNavn());
                intent.putExtra("adress", restaurantArrayList.get(position).getAdresse());
                intent.putExtra("telefon", restaurantArrayList.get(position).getTelefon());
                intent.putExtra("type", restaurantArrayList.get(position).getType());
                context.startActivity(intent);
                notifyDataSetChanged();


            }
        });

        // Slett
        ImageView deleteBtn = convertView.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RestaurantAktivitet.class);
                intent.putExtra("Id", restaurantArrayList.get(position).getID());
                int id=restaurantArrayList.get(position).getID();
                onBekrefSlett(id, position);
                //restaurantArrayList.remove(position);
               notifyDataSetChanged();
                }
        });

        holder.restaurantNavn.setText(restaurant.getNavn());
        return convertView;
    }

    public void onBekrefSlett(final int id, final int position) {
        AlertDialog.Builder builder;
        String message = context.getResources().getString(R.string.alert_restaurant_msg);//"Vil du virkelig avslutte spillet?";
        builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        builder.setCancelable(false);
        TextView textView = new TextView(context);
        textView.setTextSize(18);
        textView.setText(message);
        textView.setTextColor(context.getResources().getColor(R.color.red));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(24, 20, 0, 0);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        builder.setView(textView);

        builder
                .setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHjelper.slettRestaurant(id);
                         restaurantArrayList.remove(position);
                        Intent intent = new Intent(context, RestaurantAktivitet.class);
                        context.startActivity(intent);
                        ((RestaurantAktivitet)context).finish();

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
