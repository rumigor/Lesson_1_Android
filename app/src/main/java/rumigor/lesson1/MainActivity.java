package rumigor.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
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
    ImageView fw;
    ImageView stw;
    ImageView sunW;
    WeatherParameters weatherParameters;
    private final static int REQUEST_CODE = 0x1FAB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onCreate#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        city = findViewById(R.id.textView2);
        mainImage = findViewById(R.id.imageView2);
        temp = findViewById(R.id.temp);
        humid = findViewById(R.id.humid);
        press = findViewById(R.id.pressure);
        precip = findViewById(R.id.precip);
        frDTemp = findViewById(R.id.firstdt);
        frNTemp = findViewById(R.id.firstnt);
        stDTemp = findViewById(R.id.secdt);
        stNTemp = findViewById(R.id.secnt);
        sunDTemp = findViewById(R.id.thirddt);
        sunNTemp = findViewById(R.id.thirdnt);
        fw = findViewById(R.id.frImg);
        stw = findViewById(R.id.satImg);
        sunW = findViewById(R.id.sunImg);



        Button chgCity = findViewById(R.id.chgCity);

        chgCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, CityActivity.class);
                startActivityForResult(cityIntent, REQUEST_CODE);
            }
        });
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        Button goToWeb = findViewById(R.id.openInternet);
        goToWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="";
                if (city.getText().toString().equals(getString(R.string.city))){
                    url = "https://www.gismeteo.ru/weather-sankt-peterburg-4079/";
                }
                else if (city.getText().toString().equals(getString(R.string.cityV))){
                    url = "https://www.gismeteo.ru/weather-vilnius-4230/";
                }
                else if (city.getText().toString().equals(getString(R.string.cityT))){
                    url = "https://www.gismeteo.ru/weather-adeje-50201/";
                }
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (REQUEST_CODE != requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK){
            weatherParameters = (WeatherParameters)data.getExtras().getSerializable(WEATHER);
            loadingData(weatherParameters);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onCreate#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onResume");
    }

    private void setPic(ImageView view, int cond){
        switch (cond){
            case 1:
                view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.sun2));
                break;
            case 2:
                view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.party_cloudy));
                break;
            case 3:
                view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cloudy));
                break;
            case 4:
                view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rainy));
                break;
            case 5:
                view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.thunderstorm));
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int wCond = picEquals(mainImage);
        int fdCond = picEquals(fw);
        int secondCond = picEquals(stw);
        int thirdCond = picEquals(sunW);
        weatherParameters = new WeatherParameters(city.getText().toString(), temp.getText().toString(), humid.getText().toString(), press.getText().toString(),
                precip.getText().toString(), wCond, frDTemp.getText().toString(), frNTemp.getText().toString(), fdCond, stDTemp.getText().toString(),
                stNTemp.getText().toString(), secondCond, sunDTemp.getText().toString(), sunNTemp.getText().toString(), thirdCond);
        outState.putSerializable(STATE, weatherParameters);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherParameters = (WeatherParameters)savedInstanceState.getSerializable(STATE);
        loadingData(weatherParameters);
    }

    private void loadingData(WeatherParameters weatherParameters){
        city.setText(weatherParameters.getCityName());
        temp.setText(weatherParameters.getTemp());
        humid.setText(weatherParameters.getHumid());
        press.setText(weatherParameters.getPressure());
        precip.setText(weatherParameters.getPrecep());
        frDTemp.setText(weatherParameters.getFirstDayTemp());
        frNTemp.setText(weatherParameters.getFirstNightTemp());
        stDTemp.setText(weatherParameters.getSecondDayTemp());
        stNTemp.setText(weatherParameters.getSecondNightTemp());
        sunDTemp.setText(weatherParameters.getThirdDayTemp());
        sunNTemp.setText(weatherParameters.getThirdNightTemp());
        setPic(mainImage, weatherParameters.getwCond());
        setPic(fw, weatherParameters.getFirstDayCond());
        setPic(stw, weatherParameters.getSecondDayCond());
        setPic(sunW, weatherParameters.getThirdDayCond());
    }

    private int picEquals(ImageView mainImage) {
        if (mainImage.getDrawable().getConstantState() == MainActivity.this
                .getResources().getDrawable(R.drawable.sun2)
                .getConstantState()) {
            return 1;
        } else if (mainImage.getDrawable().getConstantState() == MainActivity.this
                .getResources().getDrawable(R.drawable.party_cloudy)
                .getConstantState()) {
            return 2;
        } else if ((mainImage.getDrawable().getConstantState() == MainActivity.this
                .getResources().getDrawable(R.drawable.cloudy)
                .getConstantState())) {
            return 3;
        } else if (mainImage.getDrawable().getConstantState() == MainActivity.this
                .getResources().getDrawable(R.drawable.rainy)
                .getConstantState()) {
            return 4;
        } else if (mainImage.getDrawable().getConstantState() == MainActivity.this
                .getResources().getDrawable(R.drawable.thunderstorm)
                .getConstantState()) {
            return 5;
        } else return 0;
    }
}