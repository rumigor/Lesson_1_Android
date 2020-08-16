package rumigor.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton spb = findViewById(R.id.StPetersburg);
        RadioButton vln = findViewById(R.id.Vilnius);
        RadioButton tfn = findViewById(R.id.Tenerife);
        final TextView city = findViewById(R.id.textView2);
        final ImageView mainImage = findViewById(R.id.imageView2);
        final TextView temp = findViewById(R.id.temp);
        final TextView humid = findViewById(R.id.humid);
        final TextView press = findViewById(R.id.pressure);
        final TextView precip = findViewById(R.id.precip);
        final TextView wCond = findViewById(R.id.wCond);
        final TextView frDTemp = findViewById(R.id.fridayDt);
        final TextView frNTemp = findViewById(R.id.fridayNt);
        final TextView stDTemp = findViewById(R.id.satDt);
        final TextView stNTemp = findViewById(R.id.satNt);
        final TextView sunDTemp = findViewById(R.id.sunDt);
        final TextView sunNTemp = findViewById(R.id.sunNt);
        final ImageView fw = findViewById(R.id.frImg);
        final ImageView stw = findViewById(R.id.satImg);
        final ImageView sunW = findViewById(R.id.sunImg);
        frDTemp.setText(getResources().getText(R.string.fdt)+" +20");
        frNTemp.setText(getResources().getText(R.string.fnt)+" 11");
        stDTemp.setText(getResources().getText(R.string.fdt)+" +21");
        stNTemp.setText(getResources().getText(R.string.fnt)+" 15");
        sunDTemp.setText(getResources().getText(R.string.fdt)+" +22");
        sunNTemp.setText(getResources().getText(R.string.fnt)+" 16");

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
}