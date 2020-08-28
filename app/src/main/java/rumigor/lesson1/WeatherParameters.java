package rumigor.lesson1;

import android.app.Application;
import android.content.Context;

import java.io.Serializable;

public class WeatherParameters implements Serializable {
    private String cityName;
    private String temp;
    private String humid;
    private String pressure;
    private String precep;
    private int wCond;
    private String firstDayTemp;
    private String firstNightTemp;
    private int firstDayCond;
    private String SecondDayTemp;
    private String SecondNightTemp;
    private int SecondDayCond;
    private String ThirdDayTemp;
    private String ThirdNightTemp;
    private int ThirdDayCond;
    private String wind;
    private String sunrise;
    private String sunset;
    private String precProb;
    private String uvLevel;


    public WeatherParameters(String cityName, String temp, String humid, String pressure, String precep, int wCond, String firstDayTemp, String firstNightTemp, int firstDayCond, String secondDayTemp, String secondNightTemp, int secondDayCond, String thirdDayTemp, String thirdNightTemp, int thirdDayCond, String wind, String sunrise, String sunset, String precProb, String uvLevel) {
        this.cityName = cityName;
        this.temp = temp;
        this.humid = humid;
        this.pressure = pressure;
        this.precep = precep;
        this.wCond = wCond;
        this.firstDayTemp = firstDayTemp;
        this.firstNightTemp = firstNightTemp;
        this.firstDayCond = firstDayCond;
        this.SecondDayTemp = secondDayTemp;
        this.SecondNightTemp = secondNightTemp;
        this.SecondDayCond = secondDayCond;
        this.ThirdDayTemp = thirdDayTemp;
        this.ThirdNightTemp = thirdNightTemp;
        this.ThirdDayCond = thirdDayCond;
        this.wind = wind;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.precProb = precProb;
        this.uvLevel = uvLevel;
    }

    public String getWind() {
        return wind;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getPrecProb() {
        return precProb;
    }

    public String getUvLevel() {
        return uvLevel;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemp() {
        return temp;
    }

    public String getHumid() {
        return humid;
    }

    public String getPressure() {
        return pressure;
    }

    public String getPrecep() {
        return precep;
    }

    public int getwCond() {
        return wCond;
    }

    public String getFirstDayTemp() {
        return firstDayTemp;
    }

    public String getFirstNightTemp() {
        return firstNightTemp;
    }

    public int getFirstDayCond() {
        return firstDayCond;
    }

    public String getSecondDayTemp() {
        return SecondDayTemp;
    }

    public String getSecondNightTemp() {
        return SecondNightTemp;
    }

    public int getSecondDayCond() {
        return SecondDayCond;
    }

    public String getThirdDayTemp() {
        return ThirdDayTemp;
    }

    public String getThirdNightTemp() {
        return ThirdNightTemp;
    }

    public int getThirdDayCond() {
        return ThirdDayCond;
    }
}


