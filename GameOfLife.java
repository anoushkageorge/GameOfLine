import java.util.Arrays;

public class GameOfLife implements Board {

    private int[][] board;
    private int[][] tempBoard; // Temporary board for updating the next generation

    public GameOfLife(int x, int y) {
        board = new int[x][y];
        tempBoard = new int[x][y]; // Initialize the temporary board
    }

    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    public void step() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);
                if (board[x][y] == 1) {
                    tempBoard[x][y] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    tempBoard[x][y] = (neighbors == 3) ? 1 : 0;
                }
            }
        }

        // Swap tempBoard with board
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(tempBoard[i], tempBoard[i].length);
        }

        print();
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

    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    public int[][] get() {
        return board;
    }

    public void print() {
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y] == 1 ? "⬛" : "⬜");
            }
        }
        System.out.println();
    }
}
