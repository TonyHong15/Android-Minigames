package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.R;

public class CustomizeActivity extends AppCompatActivity {
    public final static String PASS_TIME = "pass time";
    public final static String PASS_FRIENDLY = "pass friendly";
    public final static String PASS_EVIL = "pass evil";
    private View fifteen, thirty, red, black, blue, yellow, proceed;
    private View time, friendly, evil;
    private boolean choseEvil, choseFriendly, choseTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        fifteen = findViewById(R.id.FIFTEEN);
        setListenerFifteen();
        thirty = findViewById(R.id.THIRTY);
        setListenerThirty();
        red = findViewById(R.id.RED);
        setListenerRed();
        black = findViewById(R.id.BLACK);
        setListenerBlack();
        blue = findViewById(R.id.BLUE);
        setListenerBlue();
        yellow = findViewById(R.id.YELLOW);
        setListenerYellow();
        proceed = findViewById(R.id.proceed);
//        setListenerProceed();
        choseEvil = false;
        choseFriendly = false;
        choseTime = false;
    }

//    private void setListenerProceed() {
//        proceed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (choseEvil && choseFriendly && choseTime) {
//                    proceedToGame();
//                }
//            }
//        });
//    }
//
//    private void proceedToGame() {
//
//    }

    private void setListenerFifteen() {
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseTime) {
                    time = fifteen;
                    thirty.setVisibility(View.INVISIBLE);
                    choseTime = true;
                }
            }
        });
    }

    private void setListenerThirty() {
        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseTime) {
                    time = thirty;
                    fifteen.setVisibility(View.INVISIBLE);
                    choseTime = true;
                }
            }
        });
    }

    private void setListenerBlue() {
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseFriendly) {
                    friendly = blue;
                    yellow.setVisibility(View.INVISIBLE);
                    choseFriendly = true;
                }
            }
        });
    }

    private void setListenerYellow() {
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseFriendly) {
                    friendly = yellow;
                    blue.setVisibility(View.INVISIBLE);
                    choseFriendly = true;
                }
            }
        });
    }

    private void setListenerRed() {
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseEvil) {
                    evil = red;
                    black.setVisibility(View.INVISIBLE);
                    choseEvil = true;
                }
            }
        });
    }

    private void setListenerBlack() {
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseEvil) {
                    evil = black;
                    red.setVisibility(View.INVISIBLE);
                    choseEvil = true;
                }
            }
        });
    }

}
