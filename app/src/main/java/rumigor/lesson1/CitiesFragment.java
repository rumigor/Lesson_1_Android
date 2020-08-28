package rumigor.lesson1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class CitiesFragment extends Fragment implements Constants {
    WeatherParameters weatherParameters;
    private static final String CITY_NAME = "cityName";
    private EditText inputText;
    RadioButton spb;
    RadioButton vilnius;
    RadioButton tenerife;
    private String cityName;
    WeatherFragment weatherFragment;


    public static CitiesFragment create (String cityName) {
        CitiesFragment fragment = new CitiesFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityName = getArguments().getString(CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);
        spb = view.findViewById(R.id.city1);
        vilnius = view.findViewById(R.id.city2);
        tenerife = view.findViewById(R.id.city3);
//        if (savedInstanceState != null){
//            cityName = savedInstanceState.getString(CITY_NAME);
//        }
//        else cityName = getString(R.string.city);

        RadioGroup cityChanger = view.findViewById(R.id.citiesChoose);
        cityChanger.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.city1:
                        weatherParameters = new WeatherParameters(getString(R.string.city), getString(R.string.cityTemp), getString(R.string.cityHumid),
                                getString(R.string.cityPressure), getString(R.string.cityPrecep), 3, " +24°С", " +13°С",
                                1, "+25°С", "+15°С", 1,
                                "+24°С", "+16°С", 4, getString(R.string._4_m_s_sw), "5:35", "20:24", "22%", "1");
                        break;
                    case R.id.city2:
                        weatherParameters = new WeatherParameters(getString(R.string.cityV), getString(R.string.cityTempV), getString(R.string.cityHumidV),
                                getString(R.string.cityPressureV), getString(R.string.cityPrecepV), 2, " +25°С", " +16°С",
                                1, "+26°С", "+15°С", 1,
                                "+27°С", "+17°С", 5, getString(R.string.windV), "6:11", "20:29", "60%", "4");
                        break;
                    case R.id.city3:
                        weatherParameters = new WeatherParameters(getString(R.string.cityT), getString(R.string.cityTempT), getString(R.string.cityHumidT),
                                getString(R.string.cityPressureT), getString(R.string.cityPrecepT), 1, " +31°С", " +24°С",
                                5, "+33°С", "+27°С", 5,
                                "+31°С", "+27°С", 5, getString(R.string.windT), "7:42", "20:36", "0%", "13");
                }
                weatherFragment = (WeatherFragment)
                        getFragmentManager().findFragmentById(R.id.weatherFrame);


                    weatherFragment = WeatherFragment.create(weatherParameters);

                    // Выполняем транзакцию по замене фрагмента
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.weatherFrame, weatherFragment);  // замена фрагмента
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();

            }
        });
    }
}