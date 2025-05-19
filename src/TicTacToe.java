import java.util.Scanner;

/**
 * This class contains the main game logic for Tic-Tac-Toe.
 * It handles player turns, input, and game flow.
 */

public class TicTacToe {
    private Board board;
    private Scanner scanner;
    private String playerXName;
    private String playerOName;

    /**
     * Constructor: Sets up a new game
     */

    public TicTacToe() {
        board = new Board();
        scanner = new Scanner(System.in);

        // Get player names
        System.out.println("===================================");
        System.out.println("       Welcome to Tic-Tac-Toe!     ");
        System.out.println("===================================");

        System.out.print("Enter Player X's name: ");
        playerXName = scanner.nextLine();

        System.out.print("Enter Player O's name: ");
        playerOName = scanner.nextLine();

        System.out.println("===================================");
    }

    /**
     * Returns current player's name based on mark
     */
    private String getCurrentPlayerName() {
        return (board.getCurrentPlayer() == 'X') ? playerXName : playerOName;
    }

    /**
     * Runs the main game loop
     */

    public void play() {
        // Show welcome message
        System.out.println("How to play:");
        System.out.println("1. Enter row and column numbers (0-2)");
        System.out.println("2. Players take turns placing X and O");
        System.out.println("3. First player to get 3 in a row wins!");
        System.out.println("===================================");
        System.out.println(playerXName + " is X and " + playerOName + " is O");
        System.out.println(playerXName + " goes first!");
        System.out.println("===================================");

        boolean gameEnded = false;

        // Show initial board
        board.print();

        while (!gameEnded) {
            String currentPlayerName = getCurrentPlayerName();
            System.out.println(currentPlayerName + "'s turn (" + board.getCurrentPlayer() + ")");

            boolean validMove = false;
            while (!validMove) {
                try {
                    // Ask for row and column
                    System.out.print("Enter row (0-2): ");
                    int row = scanner.nextInt();

                    System.out.print("Enter column (0-2): ");
                    int col = scanner.nextInt();

                    // Try to make the move
                    validMove = board.setRowCol(row, col);

                    if (!validMove) {
                        System.out.println("That spot is taken or invalid. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter numbers between 0 and 2 only.");
                    scanner.nextLine(); // Clear input buffer
                }
            }

            board.print(); // Show updated board

            if (board.isWin()) {
                System.out.println(currentPlayerName + " wins! Congratulations!");
                gameEnded = true;
            } else if (board.isFull()) {
                System.out.println("It's a tie! The board is full.");
                gameEnded = true;
            } else {
                board.changePlayer(); // Switch players
            }
        }

        // Ask to play again
        System.out.print("Would you like to play again? (y/n): ");
        scanner.nextLine(); // Clear buffer
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            // Reset the game manually (since no reset() method exists)
            board = new Board(); // Create a new board
            play(); // Restart game
        } else {
            System.out.println("Thanks for playing, " + playerXName + " and " + playerOName + "!");
        }
    }

    /**
     * Main method: Entry point of the program
     */

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}