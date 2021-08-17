package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivityNoBot extends AppCompatActivity implements View.OnClickListener{

    public final static String p1Name = "p1Name";
    public final static String p2Name = "p2Name";
    public final static int getTurn=1;
    private int done = -1;
    private Button b00,b01,b02,b10,b11,b12,b20,b21,b22,restart;
    private int turn = 1;
    private TextView turnText,result;
    private String name1,name2;
    private int[][]  grid ={
            {-1,-1,-1},
            {-1,-1,-1},
            {-1,-1,-1}
    };

    private int clicks =0;

    private FloatingActionButton fab;
    private int clickedFAB = 0;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity_nobot);
        this.setTitle("Game");
        restart =findViewById(R.id.restartButton);
        Intent intent =getIntent();
        name1 = intent.getStringExtra(p1Name);
        name2 = intent.getStringExtra(p2Name);
        result = findViewById(R.id.Result);
        result.setVisibility(View.INVISIBLE);
        TextView p1,p2;
        p1= findViewById(R.id.p1text);
        p2= findViewById(R.id.p2text);

        p1.setText(name1+" (X)");

        p2.setText(name2+" (O)");

        turnText = findViewById(R.id.turnText);
        turnText.setText(name1+"'s Turn");

        b00 = findViewById(R.id.button00);
        b00.setOnClickListener(this);
        b01 = findViewById(R.id.button01);
        b01.setOnClickListener(this);
        b02 = findViewById(R.id.button02);
        b02.setOnClickListener(this);
        b10 = findViewById(R.id.button10);
        b10.setOnClickListener(this);
        b11 = findViewById(R.id.button11);
        b11.setOnClickListener(this);
        b12 = findViewById(R.id.button12);
        b12.setOnClickListener(this);
        b20 = findViewById(R.id.button20);
        b20.setOnClickListener(this);
        b21 = findViewById(R.id.button21);
        b21.setOnClickListener(this);
        b22 = findViewById(R.id.button22);
        b22.setOnClickListener(this);
        checkBox = findViewById(R.id.checkBox);

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickedFAB==0)
                {
                    Toast.makeText(GameActivityNoBot.this, "Restart the Game?", Toast.LENGTH_SHORT).show();
                    clickedFAB+=1;
                    return;
                }

                Intent intent1 = new Intent(v.getContext(), GameActivityNoBot.class);
                intent1.putExtra(p1Name,name1);
                intent1.putExtra(p2Name,name2);
                startActivity(intent1);
                finish();
            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox = findViewById(R.id.checkBox);
                if(checkBox.isChecked())
                {
                    Intent intent1 = new Intent(v.getContext(), PlayerEntryNoBot.class);
                    startActivity(intent1);
                    finish();
                }
                else
                {
                    Intent intent1 = new Intent(v.getContext(), GameActivityNoBot.class);
                    intent1.putExtra(p1Name,name1);
                    intent1.putExtra(p2Name,name2);
                    startActivity(intent1);
                    finish();
                }
            }
        });



    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {

        if(done==1)
        {
            return;
        }

        Button button =findViewById(v.getId());
        String s = button.getText().toString();

        button.setTextColor(getResources().getColor(R.color.black));

        if(s=="X" || s=="O")
        {
            Toast.makeText(this, "Please click valid box", Toast.LENGTH_SHORT).show();
            return;
        }

        clicks+=1;
        int temp =turn;
        if(turn==1)
        {
            button.setText("X");
            turn =2;
            turnText.setText(name2+"'s Turn");

        }
        else
        {
            button.setText("O");
            turn=1;
            turnText.setText(name1+"'s Turn");
        }

        switch (v.getId())
        {
            case R.id.button00:
                grid[0][0] = temp;
                break;
            case R.id.button01:
                grid[0][1]=temp;
                break;
            case R.id.button02:
                grid[0][2]=temp;
                break;
            case R.id.button10:
                grid[1][0] = temp;
                break;
            case R.id.button11:
                grid[1][1]=temp;
                break;
            case R.id.button12:
                grid[1][2]=temp;
                break;
            case R.id.button20:
                grid[2][0] = temp;
                break;
            case R.id.button21:
                grid[2][1]=temp;
                break;
            case R.id.button22:
                grid[2][2]=temp;
                break;
        }

        GameCheck obj = new GameCheck(grid,temp);

        if(obj.check()==-1)
        {
            if(clicks==9)
            {
                done=1;
                turnText.setText("Game Done");
                result.setText("Game Tied");
                result.setVisibility(View.VISIBLE);
                restart.setVisibility(View.VISIBLE);
                checkBox.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
                MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.tied_sound);
                mp.start();
            }
            return;
        }
        done=1;
        turnText.setText("Game Done");
        restart.setVisibility(View.VISIBLE);
        checkBox.setVisibility(View.VISIBLE);
        if(obj.check()==1)
        {
            result.setText(name1+" Wins!!🥳🎉");
            result.setVisibility(View.VISIBLE);
            fab.setVisibility(View.INVISIBLE);
            MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.winning_sound);
            mp.start();

        }
        else if(obj.check()==2)
        {
            result.setText(name2+" Wins!!🥳🎉");
            result.setVisibility(View.VISIBLE);
            fab.setVisibility(View.INVISIBLE);
            MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.winning_sound);
            mp.start();
        }


    }
}