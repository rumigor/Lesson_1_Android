package rumigor.lesson1;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class CityActivity extends AppCompatActivity implements Constants {
    private static final int REQUEST_CODE =  0x1FAB;
    WeatherParameters weatherParameters;
    private static final String CITY_NAME = "cityName";
    private EditText inputText;
    RadioButton spb;
    RadioButton vilnius;
    RadioButton tenerife;
    CheckBox wind;
    CheckBox sunriseSet;
    CheckBox precepProb;
    CheckBox uvLevel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onCreate#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onCreate");
        setContentView(R.layout.city_changer);
        wind = findViewById(R.id.wind);
        sunriseSet = findViewById(R.id.sunTime);
        precepProb = findViewById(R.id.precipProb);
        uvLevel = findViewById(R.id.uvLevel);
        spb = findViewById(R.id.city1);
        vilnius = findViewById(R.id.city2);
        tenerife = findViewById(R.id.city3);
        wind.setChecked(getIntent().getExtras().getBoolean(WIND));
        sunriseSet.setChecked(getIntent().getExtras().getBoolean(SUNRISE_SUNSET));
        precepProb.setChecked(getIntent().getExtras().getBoolean(PRECEP_PROB));
        uvLevel.setChecked(getIntent().getExtras().getBoolean(UV_LEVEL));
        String cityN = getIntent().getExtras().getString(CHECKED_CITY_NAME);
        if (cityN.equals(spb.getText().toString())) spb.setChecked(true);
        if (cityN.equals(vilnius.getText().toString())) vilnius.setChecked(true);
        if (cityN.equals(tenerife.getText().toString())) tenerife.setChecked(true);
        Button changeCity = findViewById(R.id.citychanger);
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int wc = 4;
               Intent intent = new Intent();
               if (spb.isChecked()){
                   weatherParameters = new WeatherParameters(getString(R.string.city), getString(R.string.cityTemp), getString(R.string.cityHumid),
                           getString(R.string.cityPressure), getString(R.string.cityPrecep), 3," +24°С", " +13°С",
                           1, "+25°С", "+15°С", 1,
                           "+24°С", "+16°С", 4, getString(R.string._4_m_s_sw), "5:35", "20:24", "22%", "1");

                   intent.putExtra(WEATHER, weatherParameters);

               } else if (vilnius.isChecked()) {
                   weatherParameters = new WeatherParameters(getString(R.string.cityV), getString(R.string.cityTempV), getString(R.string.cityHumidV),
                           getString(R.string.cityPressureV), getString(R.string.cityPrecepV), 2," +25°С", " +16°С",
                           1, "+26°С", "+15°С", 1,
                           "+27°С", "+17°С", 5, getString(R.string.windV), "6:11", "20:29", "60%", "4");
                   intent.putExtra(WEATHER, weatherParameters);
               } else if (tenerife.isChecked()) {
                   weatherParameters = new WeatherParameters(getString(R.string.cityT), getString(R.string.cityTempT), getString(R.string.cityHumidT),
                           getString(R.string.cityPressureT), getString(R.string.cityPrecepT), 1," +31°С", " +24°С",
                           5, "+33°С", "+27°С", 5,
                           "+31°С", "+27°С", 5, getString(R.string.windT), "7:42", "20:36", "0%", "13");
                   intent.putExtra(WEATHER, weatherParameters);
               }
               intent.putExtra(WIND, wind.isChecked()).putExtra(SUNRISE_SUNSET, sunriseSet.isChecked()).putExtra(PRECEP_PROB, precepProb.isChecked()).putExtra(UV_LEVEL, uvLevel.isChecked());
               setResult(RESULT_OK, intent);
               finish();
            }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onResume");
    }


}
