package oslomet.no.s309898_s309854;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Venn;


public class MyService extends Service {

    private DatabaseHjelper databaseHjelper;
    int day, month, year, hour, minute;
    DatabaseHjelper dbHjelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean serviceOn = sharedPreferences.getBoolean("switch_on_off", true);
        String melding = sharedPreferences.getString("endre_melding", "");


        if(!serviceOn){
            return super.onStartCommand(intent, flags, startId);
        }

        dbHjelper = new DatabaseHjelper(this);

        List<Bestilling> bestillinger = dbHjelper.hentAlleBestillinger();
        for (int i = 0; i <bestillinger.size() ; i++) {
            List<Venn> venner = dbHjelper.hentVenner_Bestilling(bestillinger.get(i));
            for(Venn vennn: venner){
                String telefon = vennn.getTelefon();
                if(checkPermission(Manifest.permission.SEND_SMS)){

                    Log.i("Sender sms til: " , vennn.getTelefon());
                    Log.i("MeldingInnhold:" , melding);

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(telefon, null, melding, null, null);
                }


            }
        }


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Påminnelse melding til venner  om restaurantbestillingen er sendt").build();

        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);



        return super.onStartCommand(intent, flags, startId);
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}

