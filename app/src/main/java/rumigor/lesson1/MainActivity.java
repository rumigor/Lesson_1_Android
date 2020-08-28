package rumigor.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants {
    TextView city;
    ImageView mainImage;
    TextView temp;
    TextView humid;
    TextView press;
    TextView precip;
    TextView frDTemp;
    TextView frNTemp;
    TextView stDTemp;
    TextView stNTemp;
    TextView sunDTemp;
    TextView sunNTemp;
    TextView wind;
    TextView sunrise;
    TextView sunset;
    TextView prPob;
    TextView uv;
    TextView windP;
    TextView sunriseT;
    TextView sunsetT;
    TextView uvL;
    TextView prProbP;
    ImageView fw;
    ImageView stw;
    ImageView sunW;
    WeatherParameters weatherParameters;
    private final static int REQUEST_CODE = 0x1FAB;
    private final static int REQUEST_CODE_SET = 0xCDFE;
    boolean nightTheme = false;
    boolean celsius = true;
    boolean isWindCheck = false;
    boolean isSunriseSunsetCheck = false;
    boolean isPrecProbCheck = false;
    boolean isUVcheck = false;
    WeatherFragment weatherFragment;
    CitiesFragment citiesFragment;
    String WF = "WeatherFragment";
    String CF = "CitiesFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onCreate#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) { // saved instance state, fragment may exist
            // look up the instance that already exists by tag
            weatherFragment = (WeatherFragment)
                    getSupportFragmentManager().findFragmentById(R.id.weatherFrame);
            citiesFragment = (CitiesFragment)getSupportFragmentManager().findFragmentById(R.id.cities);
        } else if (weatherFragment == null || citiesFragment == null) {
            if (weatherFragment == null) {
                weatherFragment = new WeatherFragment();
            }
            if (citiesFragment == null) {
                citiesFragment = new CitiesFragment();
            }
        }
        if (!weatherFragment.isInLayout()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weatherFrame, weatherFragment)
                    .commit();
        }
        if (!citiesFragment.isInLayout()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cities, citiesFragment)
                    .commit();
        }

    }
}