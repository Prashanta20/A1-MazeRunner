package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Player {
    // Attributes
    private int position_x;
    private int position_y;
    private char direction = 'E'; // Start with direction always facing East
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

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
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
        if (direction == 'N') {
            // Facing North
            position_y--; // increase y by one
        } else if (direction == 'E') {
            // Facing East
            position_x++;
        } else if (direction == 'S') {
            // Facing South
            position_y++;
        } else if (direction == 'W') {
            // Facing West
            position_x--;
        } else {
            // Error no movement
        }
        path.add('F');
    }

    public void turnRight() {
        path.add('R');
    }

    public void turnLeft() {
        path.add('L');
    }

    public void displayCanPath() {
        System.out.println("Path: ");
        if (path == null) {
            return;
        }
        for (char move : path) {
            System.out.print("" + move);
        }
        System.out.println();
    }

    public void rightHandRule() {
        // For now a simple move forward only for straight maze
        moveForward();
    }

}