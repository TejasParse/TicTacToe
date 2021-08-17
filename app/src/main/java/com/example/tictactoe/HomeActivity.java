package com.example.tictactoe;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Home");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button start = findViewById(R.id.startButton);

        Button rules = findViewById(R.id.startButton2);

        radioGroup = findViewById(R.id.PlayerSelection);

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
        switch (radioGroup.getCheckedRadioButtonId())
        {
            case R.id.radioButton:
                Intent intent = new Intent(view.getContext(),PlayerEntryNoBot.class);
                startActivity(intent);
                break;
            case R.id.radioButton2:
                Intent intent1 = new Intent(view.getContext(),PlayerEntryBot.class);
                startActivity(intent1);
                break;

        }
    }
}