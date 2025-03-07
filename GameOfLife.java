import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;
    private int[][] tempboard;// making temporary board

    public GameOfLife(int x, int y)
    {
        board = new int[x][y];
        tempboard = new int[x][y]; // initializing temporary board
        // Construct a 2d array of the given x and y size.
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        for (int i=0; i<turns; i++) {
            step();
        }
        // call step the number of times requested
    }

    // Step the simulation forward one turn.
    public void step()
    {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);
                if (board[x][y] == 1) {
                    tempBoard[x][y] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    tempBoard[x][y] = (neighbors == 3) ? 1 : 0;
                }
            }

        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(tempBoard[i], tempBoard[i].length);
        }

        print(); // swap temp board with fr board
        // Update the game board, store a 1 if the cell is alive and a 0 otherwise.
    }


    public int countNeighbors(int x, int y) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            count += get(x + dx[i], y + dy[i]);
        }
        return count;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y] == 1 ? "⬛" : "⬜"); //rearranging bc its easier for me to see
            }
        System.out.println();
    }
}
