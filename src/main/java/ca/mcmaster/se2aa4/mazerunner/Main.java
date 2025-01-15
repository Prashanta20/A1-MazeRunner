package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // create Options object
        Options options = new Options();
        // add t option
        options.addOption("i", "flag", false, "load maze");

        try {
            // create parser for command line arguments
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("i")) { // if it has the -i flag
                // Conntinue

                logger.trace("**** Reading the maze from file " + args[1]);
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.debug("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.debug("PASS ");
                        }
                    }
                    logger.debug(System.lineSeparator());
                }
            } else {
                // it does not have the -i flag
                throw new Exception("No Maze Detected");
            }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.trace("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
