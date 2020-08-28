package rumigor.lesson1;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import static rumigor.lesson1.Constants.CELSIUS;
import static rumigor.lesson1.Constants.NIGHT_THEME;

public class SettingsActivity extends AppCompatActivity implements Constants {
    private Switch theme;
    private final String NIGHT_MODE = "Night_Mode";
    private final static int REQUEST_CODE = 0x1FAB;
    boolean nightMode;
    boolean celsius = true;
    ConstraintLayout layout;
    SettingsFragment settingsFragment;
    String SF = "settingsFrame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(), "onCreate#3", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", "onCreate");
        setContentView(R.layout.activity_settings);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
        if (savedInstanceState != null) { // saved instance state, fragment may exist
            // look up the instance that already exists by tag
            settingsFragment = (SettingsFragment)
                    getSupportFragmentManager().findFragmentByTag(SF);
        } else if (settingsFragment == null) {
            // only create fragment if they haven't been instantiated already
            settingsFragment = new SettingsFragment();
        }
        if (!settingsFragment.isInLayout()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settingsFragment, settingsFragment, SF)
                    .commit();
        }
    }
}