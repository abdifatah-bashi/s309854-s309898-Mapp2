package oslomet.no.s309898_s309854;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;


public class SetService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean serviceOn = sharedPreferences.getBoolean("switch_on_off", true);
        String melding = sharedPreferences.getString("endre_melding", "");

        if (!serviceOn) {
            /* TODO: STOPPE SMS-UTSENDELSE TJENESTEN */
            Intent i = new Intent(this, PeriodiskService.class);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            return super.onStartCommand(intent, flags, startId);
        }

        /* TODO: HENTE UT TID FOR UTSENDELSE AV SMS */
        String time = sharedPreferences.getString("set_time", "");
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        Log.i("smsUtsTid: ", +hour + ":" + minute);
        Log.i("melding: ", melding);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Intent i = new Intent(this, PeriodiskService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);

        /* TODO: KjØRER KODE I KLASSEN MYSERVICE HVER DAG PÅ DET GITTE TIDSPUNKTET */
        Log.i("Kalanderen er nå: ", " " + calendar);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        return super.onStartCommand(intent, flags, startId);
    }
}
