package rumigor.lesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onCreate#1", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        weatherParameters = WeatherParameters.getInstance();
        city = findViewById(R.id.textView2);
        mainImage = findViewById(R.id.imageView2);
        temp = findViewById(R.id.temp);
        humid = findViewById(R.id.humid);
        press = findViewById(R.id.pressure);
        precip = findViewById(R.id.precip);
        frDTemp = findViewById(R.id.fridayDt);
        frNTemp = findViewById(R.id.fridayNt);
        stDTemp = findViewById(R.id.satDt);
        stNTemp = findViewById(R.id.satNt);
        sunDTemp = findViewById(R.id.sunDt);
        sunNTemp = findViewById(R.id.sunNt);
        fw = findViewById(R.id.frImg);
        stw = findViewById(R.id.satImg);
        sunW = findViewById(R.id.sunImg);



        Button chgCity = findViewById(R.id.chgCity);

        chgCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CityActivity.class));
            }
        });
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        city.setText(weatherParameters.getCityName());
        temp.setText(weatherParameters.getTemp());
        humid.setText(weatherParameters.getHumid());
        press.setText(weatherParameters.getPressue());
        precip.setText(weatherParameters.getPrecep());
        frDTemp.setText(getResources().getText(R.string.fdt)+weatherParameters.getFirstDayTemp());
        frNTemp.setText(getResources().getText(R.string.fnt)+weatherParameters.getFirstNightTem());
        stDTemp.setText(getResources().getText(R.string.fdt)+weatherParameters.getSecondDayTemp());
        stNTemp.setText(getResources().getText(R.string.fnt)+weatherParameters.getSecondNightTem());
        sunDTemp.setText(getResources().getText(R.string.fdt)+weatherParameters.getThirdDayTemp());
        sunNTemp.setText(getResources().getText(R.string.fnt)+weatherParameters.getThirdNightTemp());
        Toast.makeText(getApplicationContext(), "onCreate#1", Toast.LENGTH_SHORT).show();
        setPic(mainImage);
        setPic(fw);
        setPic(stw);
        setPic(sunW);
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

    private void setPic(ImageView view){
        int cond = 4;
        if (view == mainImage) cond = weatherParameters.getwCond();
        else if (view == fw) cond = weatherParameters.getFirstDayCond();
        else if (view == stw) cond = weatherParameters.getSecondDayCond();
        else if (view == sunW) cond = weatherParameters.getThirdDayCond();
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
}