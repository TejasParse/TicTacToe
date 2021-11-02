package com.kridacreations.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class krida_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krida_screen);

        getSupportActionBar().hide();

        Handler handler = new Handler();
        final Intent[] i = new Intent[1];
        Runnable myRunnable = new Runnable() {
            public void run() {
                // Things to be done

                i[0] = new Intent(krida_screen.this, HomeActivity.class);
                startActivity(i[0]);
                finish();
            }
        };

        handler.postDelayed(myRunnable, 1000);
    }
}