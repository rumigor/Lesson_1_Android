package rumigor.lesson1;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class CityActivity extends AppCompatActivity {
    WeatherParameters weatherParameters;
    private static final String CITY_NAME = "cityName";
    private EditText inputText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherParameters = WeatherParameters.getInstance();
        Toast.makeText(getApplicationContext(), "onCreate#2", Toast.LENGTH_SHORT).show();
        Log.d("CityActivity", "onCreate");
        setContentView(R.layout.city_changer);
        RadioButton spb = findViewById(R.id.city1);
        RadioButton vilnius = findViewById(R.id.city2);
        RadioButton tenerife = findViewById(R.id.city3);
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
               if (spb.isChecked()){
                   weatherParameters.setCityName(getString(R.string.city));
                   weatherParameters.setTemp(getString(R.string.cityTemp));
                   weatherParameters.setHumid(getString(R.string.cityHumid));
                   weatherParameters.setPressue(getString(R.string.cityPressure));
                   weatherParameters.setPrecep(getString(R.string.cityPrecep));
                   weatherParameters.setwCond(Integer.parseInt(getString(R.string.cityCond)));
                   weatherParameters.setFirstDayTemp(" +24");
                   weatherParameters.setFirstNightTem(" +13");
                   weatherParameters.setFirstDayCond(1);
                   weatherParameters.setSecondDayTemp(" +25");
                   weatherParameters.setSecondNightTem(" +15");
                   weatherParameters.setSecondDayCond(1);
                   weatherParameters.setThirdDayTemp(" +24");
                   weatherParameters.setThirdNightTemp(" +16");
                   weatherParameters.setThirdDayCond(4);
               } else if (vilnius.isChecked()) {
                   weatherParameters.setCityName(getString(R.string.cityV));
                   weatherParameters.setTemp(getString(R.string.cityTempV));
                   weatherParameters.setHumid(getString(R.string.cityHumidV));
                   weatherParameters.setPressue(getString(R.string.cityPressureV));
                   weatherParameters.setPrecep(getString(R.string.cityPrecepV));
                   weatherParameters.setwCond(Integer.parseInt(getString(R.string.cityCondV)));
                   weatherParameters.setFirstDayTemp(" +25");
                   weatherParameters.setFirstNightTem(" +16");
                   weatherParameters.setFirstDayCond(1);
                   weatherParameters.setSecondDayTemp(" +26");
                   weatherParameters.setSecondNightTem(" +15");
                   weatherParameters.setSecondDayCond(1);
                   weatherParameters.setThirdDayTemp(" +27");
                   weatherParameters.setThirdNightTemp(" +17");
                   weatherParameters.setThirdDayCond(5);
               } else if (tenerife.isChecked()) {
                   weatherParameters.setCityName(getString(R.string.cityT));
                   weatherParameters.setTemp(getString(R.string.cityTempT));
                   weatherParameters.setHumid(getString(R.string.cityHumidT));
                   weatherParameters.setPressue(getString(R.string.cityPressureT));
                   weatherParameters.setPrecep(getString(R.string.cityPrecepT));
                   weatherParameters.setwCond(Integer.parseInt(getString(R.string.cityCondT)));
                   weatherParameters.setFirstDayTemp(" +31");
                   weatherParameters.setFirstNightTem(" +24");
                   weatherParameters.setFirstDayCond(5);
                   weatherParameters.setSecondDayTemp(" +33");
                   weatherParameters.setSecondNightTem(" +27");
                   weatherParameters.setSecondDayCond(5);
                   weatherParameters.setThirdDayTemp(" +31");
                   weatherParameters.setThirdNightTemp(" +27");
                   weatherParameters.setThirdDayCond(5);
               }
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
