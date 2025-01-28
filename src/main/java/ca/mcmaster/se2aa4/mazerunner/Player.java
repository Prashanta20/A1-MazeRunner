package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

public class Player {
    // Attributes
    private int position_x;
    private int position_y;
    private Direction direction = Direction.EAST; // Start with direction always facing East
    private ArrayList<Character> path = new ArrayList<>();

    // Contructor
    public Player(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

    // Getters and Setters
    public int getX() {
        return position_x;
    }

    public void setX(int position_x) {
        this.position_x = position_x;
    }

    public int getY() {
        return position_y;
    }

    public void setY(int position_y) {
        this.position_y = position_y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ArrayList<Character> getPath() {
        return path;
    }

    public void setPath(ArrayList<Character> path) {
        this.path = path;
    }

    // Methods

    public void moveForward() {
        if (direction == Direction.NORTH) {
            // Facing North
            position_y--; // increase y by one
        } else if (direction == Direction.EAST) {
            // Facing East
            position_x++;
        } else if (direction == Direction.SOUTH) {
            // Facing South
            position_y++;
        } else if (direction == Direction.WEST) {
            // Facing West
            position_x--;
        } else {
            // Error no movement
        }
        path.add('F');
    }

    public void turnRight() {
        if (direction == Direction.NORTH) {
            // Facing North
            direction = Direction.EAST; // turn to EAST
        } else if (direction == Direction.EAST) {
            // Facing East
            direction = Direction.SOUTH; // turn to SOUTH
        } else if (direction == Direction.SOUTH) {
            // Facing South
            direction = Direction.WEST; // turn to WEST
        } else if (direction == Direction.WEST) {
            // Facing West
            direction = Direction.NORTH; // turn to NORTH
        } else {
            // Error no movement
        }
        path.add('R');
    }

    public void turnLeft() {
        if (direction == Direction.NORTH) {
            // Facing North
            direction = Direction.WEST; // turn to WEST
        } else if (direction == Direction.EAST) {
            // Facing East
            direction = Direction.NORTH; // turn to NORTH
        } else if (direction == Direction.SOUTH) {
            // Facing South
            direction = Direction.EAST; // turn to EAST
        } else if (direction == Direction.WEST) {
            // Facing West
            direction = Direction.SOUTH; // turn to SOUTH
        } else {
            // Error no movement
        }
        path.add('L');
    }

    public void displayCanPath() {
        System.out.print("Path: ");
        if (path == null) {
            return;
        }
        for (char move : path) {
            System.out.print("" + move);
        }
        System.out.println();
    }

    public void factorizedPath() {
        if (path.isEmpty()) {
            return; // no path
        }

        StringBuilder factorizedPath = new StringBuilder(); // start new string
        char current = path.get(0); // start with the first move
        int count = 0; // set starting count to 0

        for (char move : path) {
            if (current == move) {
                // if the current is the same as the previous moves
                // add one to count
                count++;
            } else {
                // it is a new type of move
                if (count > 1) {
                    factorizedPath.append(count); // add the number to the string
                }
                factorizedPath.append(current).append(" "); // add the move to the string
                current = move; // update the current move
                count = 1; // reset count
            }
        }

        // Add the last set of moves
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(current);

        System.out.println("Path: " + factorizedPath.toString()); // print the factorized path
    }

    public void rightHandRule() {
        // For now a simple move forward only for straight maze
        moveForward();
    }

}