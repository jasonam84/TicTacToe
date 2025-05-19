/**
 * @author Jason Amo-Mensah (modified)
 * @date 20250409
 */

public class Board {
    private char[][] board;
    private char currentPlayer;

    /**
     * This is the constructor for the class.
     * It initializes the board so all cells in the board are equal to '-'.
     * The currentPlayer is initialized to 'X'.
     */
    public Board() {
        board = new char[3][3]; // Create a 3x3 board
        currentPlayer = 'X';     // Initialize current player to 'X'

        // Fill the board with '-' characters
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    /**
     * Displays the current board with position numbers for reference
     */
    public void print() {
        System.out.println("Current board:");
        System.out.println("-------------");

        // Show position reference
        System.out.println("Positions (row,col):");
        System.out.println("(0,0)|(0,1)|(0,2)");
        System.out.println("-----+-----+-----");
        System.out.println("(1,0)|(1,1)|(1,2)");
        System.out.println("-----+-----+-----");
        System.out.println("(2,0)|(2,1)|(2,2)");
        System.out.println();

        // Print the board as a 3x3 matrix
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println(); // Extra line
    }

    /**
     * Checks if the board is full
     *
     * @return true if all cells are occupied, false otherwise
     */
    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false; // Empty cell found
                }
            }
        }
        return true; // No empty cells
    }

    /**
     * Checks if there is a winner
     *
     * @return true if a winning condition is met, false otherwise
     */
    public boolean isWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != '-' &&
                    board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != '-' &&
                    board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != '-' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != '-' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    /**
     * Changes the current player
     */
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Sets a mark at the specified row and column for the current player
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @return true if the move was successful, false otherwise
     */
    public boolean setRowCol(int row, int col) {
        // Check if row and column are valid
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false; // Invalid coordinates
        }

        // Check if the cell is empty
        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true; // Successful move
        } else {
            return false; // Cell already occupied
        }
    }

    /**
     * Alternative method to set a mark using a position number (1-9)
     *
     * @param position The position (1-9)
     * @return true if the move was successful, false otherwise
     */
    public boolean setPosition(int position) {
        if (position < 1 || position > 9) {
            return false; // Invalid position
        }

        // Convert position to row and column
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        return setRowCol(row, col);
    }

    /**
     * Gets the current player
     *
     * @return the current player ('X' or 'O')
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }
}