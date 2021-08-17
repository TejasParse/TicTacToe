package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerEntryNoBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_entry);
        this.setTitle("Enter Details");
        Button submit = findViewById(R.id.submitNames);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView p1 = findViewById(R.id.p1NameWithBot);
                TextView p2 = findViewById(R.id.p2Name);
                String s1 = p1.getText().toString();
                String s2 = p2.getText().toString();

                if (s1.isEmpty() || s2.isEmpty())
                {
                    Toast.makeText(PlayerEntryNoBot.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                } else {

                    if(s1.equalsIgnoreCase(s2))
                    {
                        Toast.makeText(PlayerEntryNoBot.this, "2 Players cannot have the same name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(v.getContext(), GameActivityNoBot.class);
                    intent.putExtra(GameActivityNoBot.p1Name, s1);
                    intent.putExtra(GameActivityNoBot.p2Name, s2);
                    startActivity(intent);
                }
            }
        });

    }


}