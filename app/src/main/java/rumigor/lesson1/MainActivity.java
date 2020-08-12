package rumigor.lesson1;

import androidx.appcompat.app.AppCompatActivity;

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
        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton)v;
                switch (rb.getId()){
                    case R.id.StPetersburg:
                        city.setText(getResources().getText(R.string.city));
                        temp.setText(getResources().getText(R.string.cityTemp));
                        humid.setText(getResources().getText(R.string.cityHumid));
                        press.setText(getResources().getText(R.string.cityPressure));
                        precip.setText(getResources().getText(R.string.cityPrecep));
                        wCond.setText(getResources().getText(R.string.cityCond));
                        mainImage.setImageResource(R.drawable.rainy);
                        frDTemp.setText(getResources().getText(R.string.fdt)+" +20");
                        frNTemp.setText(getResources().getText(R.string.fnt)+" +11");
                        stDTemp.setText(getResources().getText(R.string.fdt)+" +21");
                        stNTemp.setText(getResources().getText(R.string.fnt) + " +15");
                        sunDTemp.setText(getResources().getText(R.string.fdt)+ " +22");
                        sunNTemp.setText(getResources().getText(R.string.fnt)+ " +16");
                        fw.setImageResource(R.drawable.cloudy);
                        stw.setImageResource(R.drawable.rainy);
                        sunW.setImageResource(R.drawable.party_cloudy);
                        break;
                    case R.id.Vilnius:
                        city.setText(getResources().getText(R.string.cityV));
                        temp.setText(getResources().getText(R.string.cityTempV));
                        humid.setText(getResources().getText(R.string.cityHumidV));
                        press.setText(getResources().getText(R.string.cityPressureV));
                        precip.setText(getResources().getText(R.string.cityPrecepV));
                        wCond.setText(getResources().getText(R.string.cityCondV));
                        mainImage.setImageResource(R.drawable.cloudy);
                        frDTemp.setText(getResources().getText(R.string.fdt)+" +23");
                        frNTemp.setText(getResources().getText(R.string.fnt)+" +16");
                        stDTemp.setText(getResources().getText(R.string.fdt)+" +26");
                        stNTemp.setText(getResources().getText(R.string.fnt) + " +18");
                        sunDTemp.setText(getResources().getText(R.string.fdt)+ " +28");
                        sunNTemp.setText(getResources().getText(R.string.fnt)+ " +25");
                        fw.setImageResource(R.drawable.cloudy);
                        stw.setImageResource(R.drawable.party_cloudy);
                        sunW.setImageResource(R.drawable.sun2);
                        break;
                    case R.id.Tenerife:
                        city.setText(getResources().getText(R.string.cityT));
                        temp.setText(getResources().getText(R.string.cityTempT));
                        humid.setText(getResources().getText(R.string.cityHumidT));
                        press.setText(getResources().getText(R.string.cityPressureT));
                        precip.setText(getResources().getText(R.string.cityPrecepT));
                        wCond.setText(getResources().getText(R.string.cityCondT));
                        mainImage.setImageResource(R.drawable.sun2);
                        frDTemp.setText(getResources().getText(R.string.fdt)+" +27");
                        frNTemp.setText(getResources().getText(R.string.fnt)+" +21");
                        stDTemp.setText(getResources().getText(R.string.fdt)+" +27");
                        stNTemp.setText(getResources().getText(R.string.fnt) + " +22");
                        sunDTemp.setText(getResources().getText(R.string.fdt)+ " +27");
                        sunNTemp.setText(getResources().getText(R.string.fnt)+ " +24");
                        fw.setImageResource(R.drawable.party_cloudy);
                        stw.setImageResource(R.drawable.sun2);
                        sunW.setImageResource(R.drawable.sun2);
                        break;
                }
            }

        };
        spb.setOnClickListener(radioButtonClickListener);
        vln.setOnClickListener(radioButtonClickListener);
        tfn.setOnClickListener(radioButtonClickListener);
    }
}