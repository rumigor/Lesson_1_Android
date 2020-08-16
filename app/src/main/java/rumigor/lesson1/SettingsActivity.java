package rumigor.lesson1;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button cancel = findViewById(R.id.resetButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
        final Switch theme = findViewById(R.id.nightTheme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout layout = findViewById(R.id.backgroundLayout);
                if (theme.isChecked()) {
                    layout.setBackgroundColor(Color.BLUE);
                }
                else {
                    layout.setBackgroundColor(Color.argb(100, 164, 221, 248));
                }
            }
        });
    }
}