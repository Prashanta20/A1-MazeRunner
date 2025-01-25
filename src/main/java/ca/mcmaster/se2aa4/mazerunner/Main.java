package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // create Options object
        Options options = new Options();

        // add i option
        options.addOption("i", "flag", false, "load maze");

        // add p option
        options.addOption("p", "flag", false, "verify path");

        // Create ArrayList for storing Maze grid
        ArrayList<Tile[]> gridList = new ArrayList<>();

        try {

            // create parser for command line arguments
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("i")) { // if it has the -i flag

                readMaze(gridList, args); // read in from file

                // Convert ArrayList to a 2D array for easy access
                Tile[][] grid = gridList.toArray(new Tile[gridList.size()][]);
                // Create Maze object
                Maze maze = new Maze(grid); // Set as grid in maze object

                maze.displayMaze(); // display maze

                if (commandLine.hasOption("p")) { // if it has the -p flag, validate path
                    maze.validPath(args[3]); // check valid path
                } else { // Does not have -p flag, find path
                    maze.traverseMaze(); // find path for maze
                }

            } else {
                // it does not have the -i flag
                throw new Exception("No Maze Detected");
            }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        // Compute path
        logger.trace("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    private static void readMaze(ArrayList<Tile[]> gridList, String[] args) {
        try {
            logger.trace("**** Reading the maze from file " + args[1]);
            BufferedReader reader = new BufferedReader(new FileReader(args[1]));
            String line;
            while ((line = reader.readLine()) != null) {
                // Creat a Tile array
                Tile[] tiles = new Tile[line.length()];

                for (int idx = 0; idx < line.length(); idx++) {
                    Tile tile = new Tile(); // create new Tile object

                    if (line.charAt(idx) == '#') {
                        tile.setIsWall(true); // set tile that is wall
                        logger.debug("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.debug("PASS ");
                        tile.setIsWall(false); // set tile that is pass
                    }

                    tiles[idx] = tile;
                }
                gridList.add(tiles); // Add the row of tiles into the grid
                logger.debug(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

    }
}
