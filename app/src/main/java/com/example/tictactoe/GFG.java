package com.example.tictactoe;

// Java program to find the
// next optimal move for a player
class GFG {

    public int ansx, ansy;

    char board[][] = {{'x', 'o', 'x'},
            {'o', 'o', 'x'},
            {'_', '_', '_'}};


    char player = 'x', opponent = 'o';

    public GFG(char[][] bo, int currentTurn) {
        board = bo;
        if (currentTurn == 2) {
            player = 'o';
            opponent = 'x';
        }
    }

    // This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.
    public Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    // This is the evaluation function as discussed
// in the previous article ( http://goo.gl/sJgv68 )
    public int evaluate(char b[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    public int minimax(char board[][], int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft(board) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    public int[] findBestMove() {
        int bestVal = -1000;
        int ans[] = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == '_') {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        ans[0] = i;
                        ans[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return ans;
    }

    public int[] beginnerBot()
    {
        int[] ans = new int[2];
        ans[0]=-1;
        ans[1]=-1;

        int[][] grid={
                {-1,-1,-1},
                {-1,-1,-1},
                {-1,-1,-1}
        };

        int empty=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='x')
                {
                    grid[i][j]=1;
                }
                else if(board[i][j]=='o')
                {
                    grid[i][j]=2;
                }
                else
                {
                    empty+=1;
                }
            }
        }
        if(empty==0)
        {
            return ans;
        }

        int turn=1;
        if(player=='o')
        {
            turn=2;
        }

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(grid[i][j]==-1)
                {
                    grid[i][j]=turn;
                    GameCheck obj = new GameCheck(grid,turn);
                    if(obj.check()==turn)
                    {
                        ans[0]=i;
                        ans[1]=j;
                        return ans;
                    }
                    else
                    {
                        grid[i][j]=-1;
                    }
                }
            }
        }

        if(turn==1)
        {
            turn=2;
        }
        else
        {
            turn=1;
        }
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(grid[i][j]==-1)
                {
                    grid[i][j]=turn;
                    GameCheck obj = new GameCheck(grid,turn);
                    if(obj.check()==turn)
                    {
                        ans[0]=i;
                        ans[1]=j;
                        return ans;
                    }
                    else
                    {
                        grid[i][j]=-1;
                    }
                }
            }
        }

        int a = (int)(Math.random()*(2-0+1)+0);
        int b = (int)(Math.random()*(2-0+1)+0);
        while(grid[a][b]!=-1)
        {
            a = (int)(Math.random()*(2-0+1)+0);
            b = (int)(Math.random()*(2-0+1)+0);
        }
        ans[0]=a;
        ans[1]=b;

        return ans;
    }


}
