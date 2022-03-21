package com.example.stargamesimulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsMenu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);


         Button audioEffects = findViewById(R.id.audio_effects);
         Button backgroundMusic = findViewById(R.id.background_music);


        audioEffects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Audio Effects Off", Toast.LENGTH_SHORT).show();
            }

        });
        backgroundMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "BackGround Music Off", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
