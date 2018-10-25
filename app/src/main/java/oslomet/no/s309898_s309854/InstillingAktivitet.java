package oslomet.no.s309898_s309854;

import android.content.Intent;
import android.graphics.Color;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class InstillingAktivitet extends AppCompatActivity {
    EditText timeEdit;
    private int year, hour, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitet_instilling);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragment()).commit();


    }

    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferenser);

            EditTextPreference prefMessage = (EditTextPreference) findPreference("endre_melding");
            prefMessage.setDialogTitle(R.string.dialog_endre_melding);
            prefMessage.setSummary(prefMessage.getText());
            prefMessage.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newObject) {
                    Intent i = new Intent(getActivity(), InstillingAktivitet.class);
                    startActivity(i);
                    getActivity().finish();
                    return true;
                }
            });

            final Preference onOffSwitch = findPreference("switch_on_off");
            onOffSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newObject) {
                    Intent i = new Intent();
                    i.setAction("com.example.s309854_s309898.myBroadcastReceiver");
                    getActivity().sendBroadcast(i);
                    return true;
                }
            });

            Preference setTime = findPreference("set_time");
            setTime.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newObject) {
                    ((SwitchPreference) onOffSwitch).setChecked(false);

                    return true;
                }
            });
        }


    }


}
