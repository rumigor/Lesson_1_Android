package rumigor.lesson1;

import android.app.Application;
import android.content.Context;

public final class WeatherParameters {
    private static WeatherParameters instance = null;
    private String cityName = "Saint Petersburg";
    private String temp = "+15.4";
    private String humid = "78%";
    private String pressue = "767 Hg mm";
    private String precep = "Rain";
    private int wCond = 4;
    private String firstDayTemp = " +20";
    private String firstNightTem = " +11";
    private int firstDayCond = 1;
    private String SecondDayTemp = " +21";
    private String SecondNightTem = " +15";
    private int SecondDayCond = 4;
    private String ThirdDayTemp = " +22";
    private String ThirdNightTemp = " +16";
    private int ThirdDayCond = 2;

    private WeatherParameters() {

    }

    public int getFirstDayCond() {
        return firstDayCond;
    }

    public void setFirstDayCond(int firstDayCond) {
        this.firstDayCond = firstDayCond;
    }

    public int getSecondDayCond() {
        return SecondDayCond;
    }

    public void setSecondDayCond(int secondDayCond) {
        SecondDayCond = secondDayCond;
    }

    public int getThirdDayCond() {
        return ThirdDayCond;
    }

    public void setThirdDayCond(int thirdDayCond) {
        ThirdDayCond = thirdDayCond;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    public String getPressue() {
        return pressue;
    }

    public void setPressue(String pressue) {
        this.pressue = pressue;
    }

    public String getPrecep() {
        return precep;
    }

    public void setPrecep(String precep) {
        this.precep = precep;
    }

    public int getwCond() {
        return wCond;
    }

    public void setwCond(int wCond) {
        this.wCond = wCond;
    }

    public String getFirstDayTemp() {
        return firstDayTemp;
    }

    public void setFirstDayTemp(String firstDayTemp) {
        this.firstDayTemp = firstDayTemp;
    }

    public String getFirstNightTem() {
        return firstNightTem;
    }

    public void setFirstNightTem(String firstNightTem) {
        this.firstNightTem = firstNightTem;
    }

    public String getSecondDayTemp() {
        return SecondDayTemp;
    }

    public void setSecondDayTemp(String secondDayTemp) {
        SecondDayTemp = secondDayTemp;
    }

    public String getSecondNightTem() {
        return SecondNightTem;
    }

    public void setSecondNightTem(String secondNightTem) {
        SecondNightTem = secondNightTem;
    }

    public String getThirdDayTemp() {
        return ThirdDayTemp;
    }

    public void setThirdDayTemp(String thirdDayTemp) {
        ThirdDayTemp = thirdDayTemp;
    }

    public String getThirdNightTemp() {
        return ThirdNightTemp;
    }

    public void setThirdNightTemp(String thirdNightTemp) {
        ThirdNightTemp = thirdNightTemp;
    }

    public static WeatherParameters getInstance() {
        if (instance == null) {
            instance = new WeatherParameters();
        }
        return instance;
    }
}


