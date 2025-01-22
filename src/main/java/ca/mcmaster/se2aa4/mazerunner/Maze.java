package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    // Attributes
    private Tile[][] grid;

    // Contructor
    public Maze() {

    }

    // Getters and Setters
    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    // Methods
    public void displayMaze() {
        // Loop through the 2D grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isWall()) {
                    System.out.print("#"); // print "#" for wall
                } else {
                    System.out.print(" "); // print " " for pass
                }
            }
            System.out.print(System.lineSeparator());
        }
    }

    public boolean isEnd(int x, int y) {
        try {

            // Try and index into the left and right postions next to player
            // if it is out of bounds, then player is at the start or end tile
            Tile tile_Left = grid[x - 1][y];
            Tile tile_Right = grid[x + 1][y];

            // if we get value for both tiles, we are somewhere not in the middle of board
            return false;
        } catch (IndexOutOfBoundsException e) {
            // Error
            return true;
        }
    }
}