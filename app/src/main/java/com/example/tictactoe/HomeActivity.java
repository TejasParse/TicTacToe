package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button start = findViewById(R.id.startButton);

        Button rules = findViewById(R.id.startButton2);

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RulesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View view)
    {
        Intent intent =  new Intent(this,PlayerEntry.class);
        startActivity(intent);
    }
}