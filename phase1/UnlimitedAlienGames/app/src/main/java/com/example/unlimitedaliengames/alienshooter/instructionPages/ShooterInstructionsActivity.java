package com.example.unlimitedaliengames.alienshooter.instructionPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;

public class ShooterInstructionsActivity extends AppCompatActivity {
    private View returnToMenu;
    private View customize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooter_instructions);
        returnToMenu = findViewById(R.id.returnToMenu);
        setReturnListener();
        customize = findViewById(R.id.customize);
        setCustomizeListener();

    }

    /**
     * go to the customization activity when clicked
     */
    private void setCustomizeListener() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeActivity();
                finish();
            }
        });
    }

    /**
     * go to the menu activity when clicked
     */
    private void setReturnListener() {
        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
                finish();
            }
        });
    }

    /**
     * move on to next activity
     */
    private void customizeActivity() {
        startActivity(new Intent(this, CustomizeActivity.class));
    }

    /**
     * move on to next activity
     */
    private void menu() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
