package rumigor.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
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
        wind = findViewById(R.id.wind);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        prPob = findViewById(R.id.precipProb);
        uv = findViewById(R.id.uv);
        windP = findViewById(R.id.windP);
        sunriseT = findViewById(R.id.sunriseTime);
        sunsetT = findViewById(R.id.sunsetTime);
        uvL = findViewById(R.id.uvL);
        prProbP = findViewById(R.id.pP);
        Button chgCity = findViewById(R.id.chgCity);

        chgCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, CityActivity.class);
                cityIntent.putExtra(WIND, isWindCheck).putExtra(SUNRISE_SUNSET, isSunriseSunsetCheck).putExtra(PRECEP_PROB, isPrecProbCheck).putExtra(UV_LEVEL, isUVcheck);
                cityIntent.putExtra(CHECKED_CITY_NAME, city.getText().toString());
                startActivityForResult(cityIntent, REQUEST_CODE);
            }
        });
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                settingsIntent.putExtra(NIGHT_THEME, nightTheme).putExtra(CELSIUS, celsius);
                startActivityForResult(settingsIntent, REQUEST_CODE_SET);
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
        if (REQUEST_CODE != requestCode && REQUEST_CODE_SET != requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK){
            weatherParameters = (WeatherParameters)data.getExtras().getSerializable(WEATHER);
            loadingData(weatherParameters);
            if (!celsius){
                temp.setText(degreeConvert(temp.getText().toString()));
                frDTemp.setText(degreeConvert(frDTemp.getText().toString()));
                frNTemp.setText(degreeConvert(frNTemp.getText().toString()));
                stDTemp.setText(degreeConvert(stDTemp.getText().toString()));
                stNTemp.setText(degreeConvert(stNTemp.getText().toString()));
                sunDTemp.setText(degreeConvert(sunDTemp.getText().toString()));
                sunNTemp.setText(degreeConvert(sunNTemp.getText().toString()));
            }
            isWindCheck = data.getExtras().getBoolean(WIND);
            isSunriseSunsetCheck = data.getExtras().getBoolean(SUNRISE_SUNSET);
            isPrecProbCheck = data.getExtras().getBoolean(PRECEP_PROB);
            isUVcheck = data.getExtras().getBoolean(UV_LEVEL);
            if (isWindCheck) {
                wind.setVisibility(View.VISIBLE);
                windP.setVisibility(View.VISIBLE);
            }
            else {
                wind.setVisibility(View.INVISIBLE);
                windP.setVisibility(View.INVISIBLE);
            }
            if (isSunriseSunsetCheck){
                sunrise.setVisibility(View.VISIBLE);
                sunset.setVisibility(View.VISIBLE);
                sunriseT.setVisibility(View.VISIBLE);
                sunsetT.setVisibility(View.VISIBLE);
            } else {
                sunrise.setVisibility(View.INVISIBLE);
                sunset.setVisibility(View.INVISIBLE);
                sunriseT.setVisibility(View.INVISIBLE);
                sunsetT.setVisibility(View.INVISIBLE);
            }
            if (isPrecProbCheck){
                prPob.setVisibility(View.VISIBLE);
                prProbP.setVisibility(View.VISIBLE);
            } else {
                prPob.setVisibility(View.INVISIBLE);
                prProbP.setVisibility(View.INVISIBLE);
            }
            if (isUVcheck){
                uv.setVisibility(View.VISIBLE);
                uvL.setVisibility(View.VISIBLE);
            }
            else {
                uv.setVisibility(View.INVISIBLE);
                uvL.setVisibility(View.INVISIBLE);
            }
        }
        if (resultCode == 100){
            nightTheme = data.getExtras().getBoolean(NIGHT_THEME);
            ConstraintLayout layout = findViewById(R.id.mainBackgroundLayout);
            if (nightTheme){
                layout.setBackgroundColor(Color.rgb(0, 85, 124));
            }
            else {
                layout.setBackgroundColor(Color.rgb(164, 221, 248));
            }
            if (celsius == data.getExtras().getBoolean(CELSIUS)) return;
            celsius = data.getExtras().getBoolean(CELSIUS);
            if(!celsius){
                temp.setText(degreeConvert(temp.getText().toString()));
                frDTemp.setText(degreeConvert(frDTemp.getText().toString()));
                frNTemp.setText(degreeConvert(frNTemp.getText().toString()));
                stDTemp.setText(degreeConvert(stDTemp.getText().toString()));
                stNTemp.setText(degreeConvert(stNTemp.getText().toString()));
                sunDTemp.setText(degreeConvert(sunDTemp.getText().toString()));
                sunNTemp.setText(degreeConvert(sunNTemp.getText().toString()));
            } else {
                temp.setText(degreeConvertFtoC(temp.getText().toString()));
                frDTemp.setText(degreeConvertFtoC(frDTemp.getText().toString()));
                frNTemp.setText(degreeConvertFtoC(frNTemp.getText().toString()));
                stDTemp.setText(degreeConvertFtoC(stDTemp.getText().toString()));
                stNTemp.setText(degreeConvertFtoC(stNTemp.getText().toString()));
                sunDTemp.setText(degreeConvertFtoC(sunDTemp.getText().toString()));
                sunNTemp.setText(degreeConvertFtoC(sunNTemp.getText().toString()));
            }

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
                stNTemp.getText().toString(), secondCond, sunDTemp.getText().toString(), sunNTemp.getText().toString(), thirdCond, windP.getText().toString(),
                sunriseT.getText().toString(), sunsetT.getText().toString(), prProbP.getText().toString(), uvL.getText().toString());
        outState.putSerializable(STATE, weatherParameters);
        outState.putBoolean(WIND, isWindCheck);
        outState.putBoolean(SUNRISE_SUNSET, isSunriseSunsetCheck);
        outState.putBoolean(PRECEP_PROB, isPrecProbCheck);
        outState.putBoolean(UV_LEVEL, isUVcheck);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherParameters = (WeatherParameters)savedInstanceState.getSerializable(STATE);
        loadingData(weatherParameters);
        if (savedInstanceState.getBoolean(WIND)) {
            wind.setVisibility(View.VISIBLE);
            windP.setVisibility(View.VISIBLE);
        }
        else {
            wind.setVisibility(View.INVISIBLE);
            windP.setVisibility(View.INVISIBLE);
        }
        if (savedInstanceState.getBoolean(SUNRISE_SUNSET)){
            sunrise.setVisibility(View.VISIBLE);
            sunset.setVisibility(View.VISIBLE);
            sunriseT.setVisibility(View.VISIBLE);
            sunsetT.setVisibility(View.VISIBLE);
        } else {
            sunrise.setVisibility(View.INVISIBLE);
            sunset.setVisibility(View.INVISIBLE);
            sunriseT.setVisibility(View.INVISIBLE);
            sunsetT.setVisibility(View.INVISIBLE);
        }
        if (savedInstanceState.getBoolean(PRECEP_PROB)){
            prPob.setVisibility(View.VISIBLE);
            prProbP.setVisibility(View.VISIBLE);
        } else {
            prPob.setVisibility(View.INVISIBLE);
            prProbP.setVisibility(View.INVISIBLE);
        }
        if (savedInstanceState.getBoolean(UV_LEVEL)){
            uv.setVisibility(View.VISIBLE);
            uvL.setVisibility(View.VISIBLE);
        }
        else {
            uv.setVisibility(View.INVISIBLE);
            uvL.setVisibility(View.INVISIBLE);
        }
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
        windP.setText(weatherParameters.getWind());
        sunriseT.setText(weatherParameters.getSunrise());
        sunsetT.setText(weatherParameters.getSunset());
        prProbP.setText(weatherParameters.getPrecProb());
        uvL.setText(weatherParameters.getUvLevel());
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
    private String degreeConvert(String text){
        StringBuilder s = new StringBuilder(text);
        int degree = s.indexOf("°");
        s.delete(degree, s.length());
        double tF = Double.parseDouble(s.toString())*9/5 + 32;
        if (s.charAt(0) == '+') return  "+"+tF+"°F";
        else return tF+"°F";
    }
    private String degreeConvertFtoC(String text){
        StringBuilder s = new StringBuilder(text);
        int degree = s.indexOf("°");
        s.delete(degree, s.length());
        double tC = (Double.parseDouble(s.toString())-32)*5/9;
        if (s.charAt(0) == '+') {
            s = new StringBuilder(""+tC);
            if (s.indexOf(".") != -1) {
                s.delete(s.indexOf(".")+2, s.length());
            }
            return  "+"+s.toString()+"°С";
        }
        else {
            s = new StringBuilder(""+tC);
            if (s.indexOf(".") != -1) {
                s.delete(s.indexOf(".")+2, s.length());
            }
            return s.toString()+"°С";
        }
    }
}