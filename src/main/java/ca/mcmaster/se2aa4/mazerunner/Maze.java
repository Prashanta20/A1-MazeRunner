package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    // Attributes
    private Tile[][] grid;

    private int[] endLeft;
    private int[] endRight;

    // Contructor
    public Maze(Tile[][] grid) {
        this.grid = grid; // set grid
        this.endLeft = new int[2];
        this.endRight = new int[2];

        // Find left and right endpoints
        findEndLeft(); // Make sure both ends are found
        findEndRight();
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

    public boolean isEnd(int row, int col) {
        if (col == 0 || col == grid[0].length - 1) { // check if we are at left or right most bound
            return !grid[row][col].isWall();
        }

        return false; // not at an end postion
    }

    private void findEndLeft() {
        // Loop through the left column of the grid
        for (int i = 0; i < grid.length; i++) { // Iterate over columns
            if (isEnd(i, 0)) { // Check if this tile is the end on the left
                endLeft[0] = i;
                endLeft[1] = 0;
                return; // Exit once found
            }
        }
    }

    private void findEndRight() {
        // Loop through the right column of the grid
        int len = grid[0].length; // Number of rows
        for (int i = 0; i < grid.length; i++) { // Iterate over columns
            if (isEnd(i, len - 1)) { // Check if this tile is the end on the right
                endRight[0] = i;
                endRight[1] = len - 1;
                return; // Exit once found
            }
        }
    }

    public void traverseMaze() {
        Player player = new Player(endLeft[1], endLeft[0]); // set player starting position to Left end

        // Loop while the player is not at the right end
        while ((player.getX() != endRight[1]) || (player.getY() != endRight[0])) {
            player.rightHandRule();
        }

        // For now display canonical path
        player.displayCanPath();
    }

    public void validPath(String givenPath) {
        // TODO

    }
}