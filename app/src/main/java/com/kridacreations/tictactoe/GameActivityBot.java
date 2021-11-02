package com.kridacreations.tictactoe;

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

public class GameActivityBot extends AppCompatActivity implements View.OnClickListener{
    public final static String getTurn = "getTurn";
    public final static String getDifficulty = "getDifficulty";
    public final static String p1Name = "p1Name";
    public final static String p2Name = "p2Name";

    private int done = -1;
    private Button b00,b01,b02,b10,b11,b12,b20,b21,b22,restart;
    private int turn = 1;
    private int turnCurrent=1;
    private int difficulty=1;
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
        setContentView(R.layout.activity_game_bot);
        this.setTitle("Game");
        restart =findViewById(R.id.restartButtonBot);
        Intent intent =getIntent();
        name1 = intent.getStringExtra(p1Name);
        name2 = intent.getStringExtra(p2Name);
        turnCurrent = intent.getIntExtra(getTurn,1);
        difficulty = intent.getIntExtra(getDifficulty,1);
        result = findViewById(R.id.ResultBot);
        result.setVisibility(View.INVISIBLE);
        TextView p1,p2;
        p1= findViewById(R.id.p1textBot);
        p2= findViewById(R.id.p2textBot);


        p1.setText(name1+" (X)");
        p2.setText(name2+" (O)");

        turnText = findViewById(R.id.turnTextBot);
        if(turnCurrent==1)
        {
            turnText.setText(name1+"'s Turn (X)");
        }
        else
        {
            turnText.setText(name2+"'s Turn (O)");
        }

        b00 = findViewById(R.id.button00Bot);
        b00.setOnClickListener(this);
        b01 = findViewById(R.id.button01Bot);
        b01.setOnClickListener(this);
        b02 = findViewById(R.id.button02Bot);
        b02.setOnClickListener(this);
        b10 = findViewById(R.id.button10Bot);
        b10.setOnClickListener(this);
        b11 = findViewById(R.id.button11Bot);
        b11.setOnClickListener(this);
        b12 = findViewById(R.id.button12Bot);
        b12.setOnClickListener(this);
        b20 = findViewById(R.id.button20Bot);
        b20.setOnClickListener(this);
        b21 = findViewById(R.id.button21Bot);
        b21.setOnClickListener(this);
        b22 = findViewById(R.id.button22Bot);
        b22.setOnClickListener(this);
        checkBox = findViewById(R.id.checkBoxBot);

        if(turnCurrent==2)
        {
            int a = (int)(Math.random()*(2-0+1)+0);
            int b = (int)(Math.random()*(2-0+1)+0);
            setGrid(a,b);
            clicks+=1;
            turn =2;
        }

        fab = findViewById(R.id.floatingActionButtonBot);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickedFAB==0)
                {
                    Toast.makeText(GameActivityBot.this, "Restart the Game?", Toast.LENGTH_SHORT).show();
                    clickedFAB+=1;
                    return;
                }

                Intent intent1 = new Intent(v.getContext(), GameActivityBot.class);
                intent1.putExtra(p1Name,name1);
                intent1.putExtra(p2Name,name2);
                intent1.putExtra(getTurn,turnCurrent);
                intent1.putExtra(getDifficulty,difficulty);
                startActivity(intent1);
                finish();
            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox = findViewById(R.id.checkBoxBot);
                if(checkBox.isChecked())
                {
                    Intent intent1 = new Intent(v.getContext(), PlayerEntryBot.class);
                    startActivity(intent1);
                    finish();
                }
                else
                {
                    Intent intent1 = new Intent(v.getContext(), GameActivityBot.class);
                    intent1.putExtra(p1Name,name1);
                    intent1.putExtra(p2Name,name2);
                    intent1.putExtra(getTurn,turnCurrent);
                    intent1.putExtra(getDifficulty,difficulty);
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
        if(turn==turnCurrent) {
            if(turn==1) {
                button.setText("X");
                turn = 2;
            }
            else
            {
                button.setText("O");
                turn=1;
            }
        }


        switch (v.getId())
        {
            case R.id.button00Bot:
                grid[0][0] = temp;
                break;
            case R.id.button01Bot:
                grid[0][1]=temp;
                break;
            case R.id.button02Bot:
                grid[0][2]=temp;
                break;
            case R.id.button10Bot:
                grid[1][0] = temp;
                break;
            case R.id.button11Bot:
                grid[1][1]=temp;
                break;
            case R.id.button12Bot:
                grid[1][2]=temp;
                break;
            case R.id.button20Bot:
                grid[2][0] = temp;
                break;
            case R.id.button21Bot:
                grid[2][1]=temp;
                break;
            case R.id.button22Bot:
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
            else
            {
                char[][] board = { { '_', '_', '_' },
                        { '_', '_', '_' },
                        { '_', '_', '_' } };

                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        if(grid[i][j]==1)
                        {
                            board[i][j]='x';
                        }
                        else if(grid[i][j]==2)
                        {
                            board[i][j]='o';
                        }
                    }
                }
                GFG hehe = new GFG(board, turn);
                if(difficulty==1)
                {
                    int[] ans = hehe.beginnerBot();
                    setGrid(ans[0],ans[1]);
                }
                else {
                    int[] ans = hehe.findBestMove();
                    setGrid(ans[0], ans[1]);
                }
                turn = turnCurrent;
                GameCheck checker = new GameCheck(grid,temp);
                clicks+=1;
                if(checker.check()==1)
                {
                    result.setText(name1+" Wins!!🥳🎉");
                    result.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                    MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.winning_sound);
                    mp.start();
                    done=1;
                    turnText.setText("Game Done");
                    restart.setVisibility(View.VISIBLE);
                    checkBox.setVisibility(View.VISIBLE);
                }
                else if(checker.check()==2)
                {
                    result.setText(name2+" Wins!!🥳🎉");
                    result.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                    MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.winning_sound);
                    mp.start();
                    done=1;
                    turnText.setText("Game Done");
                    restart.setVisibility(View.VISIBLE);
                    checkBox.setVisibility(View.VISIBLE);
                }

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

    public void setGrid(int x,int y)
    {
        if(x==0 && y==0)
        {
            b00.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b00.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b00.setText("O");
                turn=1;
            }
        }
        else if(x==0 && y==1)
        {
            b01.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b01.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b01.setText("O");
                turn=1;
            }
        }
        else if(x==0 && y==2)
        {
            b02.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b02.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b02.setText("O");
                turn=1;
            }
        }
        else if(x==1 && y==0)
        {
            b10.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b10.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b10.setText("O");
                turn=1;
            }
        }
        else if(x==1 && y==1)
        {
            b11.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b11.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b11.setText("O");
                turn=1;
            }
        }
        else if(x==1 && y==2)
        {
            b12.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b12.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b12.setText("O");
                turn=1;
            }
        }
        else if(x==2 && y==0)
        {
            b20.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b20.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b20.setText("O");
                turn=1;
            }
        }
        else if(x==2 && y==1)
        {
            b21.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b21.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b21.setText("O");
                turn=1;
            }
        }
        else if(x==2 && y==2)
        {
            b22.setTextColor(getResources().getColor(R.color.black));
            if(turn==1)
            {
                grid[x][y]=1;
                b22.setText("X");
                turn=2;
            }
            else
            {
                grid[x][y]=2;
                b22.setText("O");
                turn=1;
            }
        }
    }

}