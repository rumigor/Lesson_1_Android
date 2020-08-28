package rumigor.lesson1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Switch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements Constants {

    private Switch theme;
    private final String NIGHT_MODE = "Night_Mode";
    private final static int REQUEST_CODE = 0x1FAB;
    boolean nightMode;
    boolean celsius = true;
    FrameLayout layout;
    WeatherFragment weatherFragment;

    public static SettingsFragment newInstance(boolean nightMode, boolean celsius) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putBoolean(NIGHT_THEME, nightMode);
        args.putBoolean(CELSIUS, celsius);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nightMode = getArguments().getBoolean(NIGHT_THEME);
            celsius = getArguments().getBoolean(CELSIUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        theme = view.findViewById(R.id.nightTheme);
        layout = view.findViewById(R.id.settingsFragment);
        if (savedInstanceState != null){
            nightMode = getActivity().getIntent().getExtras().getBoolean(NIGHT_THEME);
            celsius = getActivity().getIntent().getExtras().getBoolean(CELSIUS);
            boolean status = savedInstanceState.getBoolean(NIGHT_MODE);
            if (status) {
                theme.setChecked(true);
                layout.setBackgroundColor(Color.BLUE);
            }
            else {
                theme.setChecked(false);
                layout.setBackgroundColor(Color.argb(100, 164, 221, 248));
            }
        }
        RadioButton c = view.findViewById(R.id.celcius);
        RadioButton f = view.findViewById(R.id.fahrenheit);
        if (nightMode){
            theme.setChecked(true);
            layout.setBackgroundColor(Color.rgb(0, 85, 124));
        }

        if (celsius) {
            c.setChecked(true);
        }
        else f.setChecked(true);
        Button cancel = view.findViewById(R.id.resetButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        Button saveSet = view.findViewById(R.id.saveButton);
        saveSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                nightMode = theme.isChecked();
                celsius = c.isChecked();
                intent.putExtra(NIGHT_THEME, nightMode).putExtra(CELSIUS, celsius);
                getActivity().setResult(100, intent);
                getActivity().finish();
            }
        });
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (theme.isChecked()) {
                    layout.setBackgroundColor(Color.rgb(0, 85, 124));
                }
                else {
                    layout.setBackgroundColor(Color.argb(100, 164, 221, 248));
                }
            }
        });
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(NIGHT_MODE, theme.isChecked());
    }


}