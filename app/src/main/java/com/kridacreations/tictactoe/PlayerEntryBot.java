package com.kridacreations.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerEntryBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_entry_bot);
        this.setTitle("Enter Details");
        Button submit = findViewById(R.id.submitNamesBot);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView p1 = findViewById(R.id.p1NameWithBot);
                String s1 = p1.getText().toString();

                if (s1.isEmpty())
                {
                    Toast.makeText(PlayerEntryBot.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                } else {
                    int choiceTurn=1;
                    RadioGroup turn = findViewById(R.id.TurnSelection);
                    switch (turn.getCheckedRadioButtonId())
                    {
                        case R.id.PlayX:
                            choiceTurn=1;
                            break;
                        case R.id.PlayO:
                            choiceTurn=2;
                            break;
                    }
                    int difficulty=1;
                    RadioGroup difficult = findViewById(R.id.DifficultySelection);
                    switch (difficult.getCheckedRadioButtonId())
                    {
                        case R.id.BeginnerButton:
                            difficulty=1;
                            break;
                        case R.id.ExpertButton:
                            difficulty=2;
                            break;
                    }
                    Intent intent = new Intent(v.getContext(),GameActivityBot.class);
                    if(choiceTurn==1) {
                        intent.putExtra(GameActivityBot.p1Name, s1);
                        intent.putExtra(GameActivityBot.p2Name, "Bot");
                    }
                    else
                    {
                        intent.putExtra(GameActivityBot.p1Name, "Bot");
                        intent.putExtra(GameActivityBot.p2Name, s1);
                    }
                    intent.putExtra(GameActivityBot.getTurn,choiceTurn);
                    intent.putExtra(GameActivityBot.getDifficulty,difficulty);
                    startActivity(intent);


                }
            }
        });

    }
}