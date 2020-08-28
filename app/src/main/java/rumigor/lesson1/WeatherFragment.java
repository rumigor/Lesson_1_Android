package rumigor.lesson1;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class WeatherFragment extends Fragment implements Constants {
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

    public static WeatherFragment create(WeatherParameters weatherParameters) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(WEATHER, weatherParameters);
        fragment.setArguments(args);
        return fragment;
    }

    public WeatherParameters getWeatherParameters(){
        if (getArguments() != null) {
            weatherParameters = (WeatherParameters) getArguments().getSerializable(WEATHER);
        }
        return weatherParameters;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherParameters = (WeatherParameters) getArguments().getSerializable(WEATHER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city = view.findViewById(R.id.textView2);
        mainImage = view.findViewById(R.id.imageView2);
        temp = view.findViewById(R.id.temp);
        humid = view.findViewById(R.id.humid);
        press = view.findViewById(R.id.pressure);
        precip = view.findViewById(R.id.precip);
        frDTemp = view.findViewById(R.id.firstdt);
        frNTemp = view.findViewById(R.id.firstnt);
        stDTemp = view.findViewById(R.id.secdt);
        stNTemp = view.findViewById(R.id.secnt);
        sunDTemp = view.findViewById(R.id.thirddt);
        sunNTemp = view.findViewById(R.id.thirdnt);
        fw = view.findViewById(R.id.frImg);
        stw = view.findViewById(R.id.satImg);
        sunW = view.findViewById(R.id.sunImg);
        wind = view.findViewById(R.id.wind);
        sunrise = view.findViewById(R.id.sunrise);
        sunset = view.findViewById(R.id.sunset);
        prPob = view.findViewById(R.id.precipProb);
        uv = view.findViewById(R.id.uv);
        windP = view.findViewById(R.id.windP);
        sunriseT = view.findViewById(R.id.sunriseTime);
        sunsetT = view.findViewById(R.id.sunsetTime);
        uvL = view.findViewById(R.id.uvL);
        prProbP = view.findViewById(R.id.pP);
        weatherParameters = getWeatherParameters();
        if (weatherParameters != null){
            loadingData(getWeatherParameters());
        }
        if (savedInstanceState != null) {
            weatherParameters = (WeatherParameters) savedInstanceState.getSerializable(STATE);
            loadingData(weatherParameters);
            if (savedInstanceState.getBoolean(WIND)) {
                wind.setVisibility(View.VISIBLE);
                windP.setVisibility(View.VISIBLE);
            } else {
                wind.setVisibility(View.INVISIBLE);
                windP.setVisibility(View.INVISIBLE);
            }
            if (savedInstanceState.getBoolean(SUNRISE_SUNSET)) {
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
            if (savedInstanceState.getBoolean(PRECEP_PROB)) {
                prPob.setVisibility(View.VISIBLE);
                prProbP.setVisibility(View.VISIBLE);
            } else {
                prPob.setVisibility(View.INVISIBLE);
                prProbP.setVisibility(View.INVISIBLE);
            }
            if (savedInstanceState.getBoolean(UV_LEVEL)) {
                uv.setVisibility(View.VISIBLE);
                uvL.setVisibility(View.VISIBLE);
            } else {
                uv.setVisibility(View.INVISIBLE);
                uvL.setVisibility(View.INVISIBLE);
            }
        }
        Button chgCity = view.findViewById(R.id.chgCity);

        chgCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(getActivity(), CityActivity.class);
                cityIntent.putExtra(WIND, isWindCheck).putExtra(SUNRISE_SUNSET, isSunriseSunsetCheck).putExtra(PRECEP_PROB, isPrecProbCheck).putExtra(UV_LEVEL, isUVcheck);
                cityIntent.putExtra(CHECKED_CITY_NAME, city.getText().toString());
                startActivityForResult(cityIntent, REQUEST_CODE);
            }
        });
        Button settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
                settingsIntent.putExtra(NIGHT_THEME, nightTheme).putExtra(CELSIUS, celsius);
                startActivityForResult(settingsIntent, REQUEST_CODE_SET);
            }
        });

        Button goToWeb = view.findViewById(R.id.gismeteo);
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



    private void setPic(ImageView view, int cond){
        switch (cond){
            case 1:
                view.setImageResource(R.drawable.sun2);
                break;
            case 2:
                view.setImageResource(R.drawable.party_cloudy);
                break;
            case 3:
                view.setImageResource(R.drawable.cloudy);
                break;
            case 4:
                view.setImageResource(R.drawable.rainy);
                break;
            case 5:
                view.setImageResource(R.drawable.thunderstorm);
                break;
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
            ConstraintLayout layout = getView().findViewById(R.id.mainBackgroundLayout);
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
        if (mainImage.getDrawable().getConstantState() == getActivity()
                .getResources().getDrawable(R.drawable.sun2)
                .getConstantState()) {
            return 1;
        } else if (mainImage.getDrawable().getConstantState() == getActivity()
                .getResources().getDrawable(R.drawable.party_cloudy)
                .getConstantState()) {
            return 2;
        } else if ((mainImage.getDrawable().getConstantState() == getActivity()
                .getResources().getDrawable(R.drawable.cloudy)
                .getConstantState())) {
            return 3;
        } else if (mainImage.getDrawable().getConstantState() == getActivity()
                .getResources().getDrawable(R.drawable.rainy)
                .getConstantState()) {
            return 4;
        } else if (mainImage.getDrawable().getConstantState() == getActivity()
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