package oslomet.no.s309898_s309854;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.oppdater), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(context, PeriodiskService.class);
        context.startService(i);
    }
}
