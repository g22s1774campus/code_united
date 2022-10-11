import java.util.Scanner;

public class Game {
	public static void main (String[] args) {
		Sudoku game = new Sudoku();

        // add initial positions
        int[] firstRow = {1, 2, 3, 4, 9, 7, 8, 6, 5};
        for (int i = 0; i < 9; i++)
            game.addInit(0, i, firstRow[i]);

        int[] firstColumn = {1, 4, 6, 3, 2, 9, 8, 7, 5};
        for (int i = 0; i < 9; i++)
            game.addInit(i, 0, firstColumn[i]);

        game.addInit(1, 1, 5);
        game.addInit(1, 2, 9);
        game.addInit(2, 1, 7);
        game.addInit(2, 2, 8);
        game.addInit(3, 4, 1);
        game.addInit(5, 5, 5);
        game.addInit(8, 3, 9);
		
		// start game
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            while (!game.checkPuzzle() && !game.isFull())
            {
                System.out.println(game + "\n");

                System.out.print("Enter <row number, column number, value> to play: ");
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                int v = scanner.nextInt();

                game.addNum(r, c, v);
            }

            if (game.checkPuzzle())
                System.out.println("You win");
            else
                System.out.println("Try again");

            System.out.print("you want to quit? ");
            String answer = scanner.next().trim().toLowerCase();

            if (answer.equals("yes"))
                game.reset();
            else
                break;
        }
	}
}