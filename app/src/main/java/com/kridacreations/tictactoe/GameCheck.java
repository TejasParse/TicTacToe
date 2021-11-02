package com.kridacreations.tictactoe;

public class GameCheck {

    int[][] grid = new int[3][3];
    public int turn=-1; //1 for X, 2 for O
    public GameCheck(int[][] grid,int turn)
    {
        this.grid=grid;
        this.turn=turn;
    }

    public int check()
    {
        if(grid[0][0]!=-1 && grid[0][0]==grid[0][1] && grid[0][1]==grid[0][2])
        {
            return grid[0][0];
        }
        else if(grid[1][0]!=-1 && grid[1][0]==grid[1][1] && grid[1][1]==grid[1][2])
        {
            return grid[1][0];
        }
        else if(grid[2][0]!=-1 && grid[2][0]==grid[2][1] && grid[2][1]==grid[2][2])
        {
            return grid[2][0];
        }
        else if(grid[0][0]!=-1 && grid[0][0]==grid[1][0] && grid[1][0]==grid[2][0])
        {
            return grid[0][0];
        }
        else if(grid[0][1]!=-1 && grid[0][1]==grid[1][1] && grid[1][1]==grid[2][1])
        {
            return grid[0][1];
        }
        else if(grid[0][2]!=-1 && grid[0][2]==grid[1][2] && grid[1][2]==grid[2][2])
        {
            return grid[0][2];
        }
        else if(grid[0][0]!=-1 && grid[0][0]==grid[1][1] && grid[1][1]==grid[2][2])
        {
            return grid[0][0];
        }
        else if(grid[0][2]!=-1 && grid[0][2]==grid[1][1] && grid[1][1]==grid[2][0])
        {
            return grid[0][2];
        }

        return -1;
    }
}

