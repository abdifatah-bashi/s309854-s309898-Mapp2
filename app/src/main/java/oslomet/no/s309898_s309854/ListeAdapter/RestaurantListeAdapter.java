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

import oslomet.no.s309898_s309854.R;
import oslomet.no.s309898_s309854.RedigerRestaurantAktivitet;
import oslomet.no.s309898_s309854.modeller.Restaurant;


public class RestaurantListeAdapter extends ArrayAdapter<Restaurant> {

    private Context context;
    private int resource;
    private ArrayList<Restaurant> restaurantArrayList;




    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView restaurantName;

    }


    public RestaurantListeAdapter(Context context, int resource, ArrayList<Restaurant> restaurantArrayList) {

        super(context, resource, restaurantArrayList);


        this.restaurantArrayList = restaurantArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getNavn();


        //Create statistic object
        final Restaurant restaurant = new Restaurant(name);



        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder= new ViewHolder();

            holder.restaurantName = convertView.findViewById(R.id.restaurantName);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        // On edit
        ImageView editBtn = convertView.findViewById(R.id.edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent intent = new Intent(context, RedigerRestaurantAktivitet.class);
                intent.putExtra("name", restaurantArrayList.get(position).getNavn());
                intent.putExtra("address", restaurantArrayList.get(position).getAdresse());
                intent.putExtra("phone", restaurantArrayList.get(position).getTelefon());
                intent.putExtra("type", restaurantArrayList.get(position).getType());
                context.startActivity(intent);
                notifyDataSetChanged();


            }
        });


        ImageView deleteBtn = convertView.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                onDeleteConfirm();
                notifyDataSetChanged();


            }
        });


        holder.restaurantName.setText(restaurant.getNavn());
        return convertView;
    }

    public void onDeleteConfirm(){

        AlertDialog.Builder builder;
        String message = context.getResources().getString(R.string.alert_restaurant_msg);//"Vil du virkelig avslutte spillet?";
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
                        Toast.makeText(context, "JA", Toast.LENGTH_LONG).show();


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
