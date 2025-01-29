package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;

public class Maze {
    // Attributes
    private Tile[][] grid;

    private int[] endLeft;
    private int[] endRight;

    private Player mazePlayer;

    // Contructor
    public Maze(Tile[][] grid, Player mazePlayer) {
        this.grid = grid; // set grid
        this.endLeft = new int[2];
        this.endRight = new int[2];

        // Find left and right endpoints
        findEndLeft(); // Make sure both ends are found
        findEndRight();
        this.mazePlayer = mazePlayer; // set Player
        // set postion of player
        mazePlayer.setX(endLeft[1]);
        mazePlayer.setY(endLeft[0]);
    }

    public Maze(Tile[][] grid) {
        this.grid = grid; // set grid
        this.endLeft = new int[2];
        this.endRight = new int[2];

        // Find left and right endpoints
        findEndLeft(); // Make sure both ends are found
        findEndRight();
        mazePlayer = new Player(endLeft[1], endLeft[0]); // set player if one is not passed
    }

    // Getters and Setters
    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public Player getPlayer() {
        return mazePlayer;
    }

    public void setPlayer(Player mazePlayer) {
        this.mazePlayer = mazePlayer;
    }

    // Methods

    // diplay the maze for human chekcks
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

    // determine if given row col is end tile
    public boolean isEnd(int row, int col) {
        if (col == 0 || col == grid[0].length - 1) { // check if we are at left or right most bound
            return !grid[row][col].isWall();
        }

        return false; // not at an end postion
    }

    // get the left end tile
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

    // get the right end tile
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

    // loop through and find path for maze
    public void traverseMaze() {

        // Loop while the player is not at the right end
        while ((mazePlayer.getX() != endRight[1]) || (mazePlayer.getY() != endRight[0])) {
            // pass the adjancent tiles and let player make move decision
            mazePlayer.exploreMaze(adjacentTiles(mazePlayer.getY(), mazePlayer.getX()));
        }

        // display the factorized path
        mazePlayer.factorizedPath();
    }

    // check if given path is valid
    public void validPath(String givenPath) {
        // check if the path works for at least one of the ends
        if (startingEast(givenPath) || startingWest(givenPath)) {
            System.out.println("The path: " + givenPath + " is a valid path");
        } else {
            System.out.println("The path: " + givenPath + " is NOT a valid path");
        }

    }

    // Helper Methods

    // check path starting at west end
    private boolean startingWest(String givenPath) {
        Player player = new Player(endLeft[1], endLeft[0]); // set player starting position to Left end

        // Traverse the path
        for (int i = 0; i < givenPath.length(); i++) {
            char move = givenPath.charAt(i);

            // move the palyer according ot the given path
            if (move == 'F') {
                player.moveForward();
            } else if (move == 'R') {
                player.turnRight();
            } else if (move == 'L') {
                player.turnLeft();
            } else {
                // not a valid move
                return false;
            }
        }

        // after the last moves, check if player is at left WEST end
        if (player.getX() == endRight[1] && player.getY() == endRight[0]) {
            return true; // player reached other end
        } else {
            return false; // player is not at other end
        }
    }

    // check path starting at east end
    private boolean startingEast(String givenPath) {
        Player player = new Player(endRight[1], endRight[0]); // set player starting position to Right end
        player.setDirection(Direction.WEST); // set starting postiont to WEST

        // Traverse the path
        for (int i = 0; i < givenPath.length(); i++) {
            char move = givenPath.charAt(i);

            // move the palyer according ot the given path
            if (move == 'F') {
                player.moveForward();
            } else if (move == 'R') {
                player.turnRight();
            } else if (move == 'L') {
                player.turnLeft();
            } else {
                // not a valid move
                return false;
            }
        }

        // after the last moves, check if player is at left WEST end
        if (player.getX() == endLeft[1] && player.getY() == endLeft[0]) {
            return true; // player reached other end
        } else {
            return false; // player is not at other end
        }
    }

    // get the adjancet tiles of the player
    private HashMap<Direction, Tile> adjacentTiles(int row, int col) {
        HashMap<Direction, Tile> options = new HashMap<>();
        // get the NORTH, EAST, SOUTH, WEST tiles

        // NORTH
        if (row > 0) {
            options.put(Direction.NORTH, grid[row - 1][col]);
        }
        // EAST
        if (col < grid[0].length - 1) {
            options.put(Direction.EAST, grid[row][col + 1]);
        }
        // SOUTH
        if (row < grid.length - 1) {
            options.put(Direction.SOUTH, grid[row + 1][col]);
        }
        // WEST
        if (col > 0) {
            options.put(Direction.WEST, grid[row][col - 1]);
        }

        return options; // Only returns valid tiles

    }
}