import java.util.Arrays;

public class Sudoku
{
    private int[][] board = new int[9][9];
    private boolean[][] start = new boolean[9][9];

    public Sudoku () { }
	/*
	public void setBoard () {
		this.board = board;
	}
	public void setStart () {
		this.start = start;
	}
	public boolean[][] getBoard () {
		return this.board;
	}
	public int[][] getStart () {
		return this.start;
	}*/
		
    public void addInit(int r, int c, int v)
    {
        if (r < 0 || r > 8 || c < 0 || c > 8)
            return;
        if (v < 1 || v > 9)
            return;
        board[r][c] = v;
        start[r][c] = true;
    }
	public void addNum(int r, int c, int v)  // Guess to Num
    {
        if (r < 0 || r > 8 || c < 0 || c > 8)
        {
            System.out.println("Enter a valid position!!");
            return;
        }

        if (v < 1 || v > 9)
        {
            System.out.println("Enter a valid value!!");
            return;
        }


        if (!start[r][c])
        {
            board[r][c] = v;
            return;
        }

        System.out.println("you can`t change the initial values!!");
    }
    public boolean checkPuzzle() {
        // check rows
        for (int i = 0; i < 9; i++) {
            boolean[] checker = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    return false;
                checker[board[i][j] - 1] = true;
            }

            for (int j = 0; j < 9; j++)
                if (!checker[j])
                    return false;
        }

        // check columns
        for (int i = 0; i < 9; i++) {
            boolean[] checker = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == 0)
                    return false;
                checker[board[j][i] - 1] = true;
            }

            for (int j = 0; j < 9; j++)
                if (!checker[j])
                    return false;
        }

        // check sub squares
        for (int i = 0; i < 7; i += 3)
        {
            for (int j = 0; j < 7; j += 3)
            {
                boolean[] checker = new boolean[9];

                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        int x = i + k;

                        int y = j + l;

                        if (board[x][y] == 0)
                            return false;
                        checker[board[x][y] - 1] = true;
                    }
                }

                for (int z = 0; z < 9; z++)
                    if (!checker[z])
                        return false;
            }
        }

        // valid solution
        return true;
    }

    public int getV(int r, int c)
    {
        if (r < 0 || r > 8 || c < 0 || c > 8)
            return -1;
        return board[r][c];
    }

    public boolean[] getValidV(int r, int c)          //  getAllowedValues   TO   getValidV
    {
        if (r < 0 || r > 8 || c < 0 || c > 8)
            return null;

        boolean[] validSub = new boolean[9]; //validReplacement TO validSub
        Arrays.fill(validSub, true);

        for (int i = 0; i < 9; i++)
        {
            if (board[r][i] != 0)
                validSub[board[r][i]-1] = false;
            if (board[i][c] != 0)
                validSub[board[i][c]-1] = false;
        }

        int initR;   // startRow to initialR   
        if (r < 3)
            initR = 0;
        else if (r < 6)
            initR = 3;
        else
            initR = 6;

        int initC;  //  startColumn to initialC
        if (c < 3)
            initC = 0;
        else if (c < 6)
            initC = 3;
        else
            initC = 6;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int x = initR + i;

                int y = initC + j;

                if (board[x][y] != 0)
                    validSub[board[x][y]-1] = false;
            }
        }

        return validSub;
    }

    public boolean isFull()
    {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == 0)
                    return false;
        return true;
    }


    public void reset()
    {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (!start[i][j])
                    board[i][j] = 0;
    }


    public String toString()
    {
        String output = "";
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
                output += board[i][j] + "|";
            output += "\n";
        }
        return output;
    }
}
