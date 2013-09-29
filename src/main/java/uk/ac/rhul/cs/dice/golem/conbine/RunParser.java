package uk.ac.rhul.cs.dice.golem.conbine;

import uk.ac.rhul.cs.dice.golem.action.Event;
import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * RunParser takes a file as input, and returns a ContainerHistory (or null if not successful).
 */
public class RunParser {

    public static final String RUN_HISTORY_EXT = ".runhistory";

    /**
     * Given the path to a runhistory file, it returns a ContainerHistory.
     * <p/>
     * A forgiving parser - it will skip lines that don't yield a valid Event, rather than
     * condemn the entire file.
     * <p/>
     * This does mean that (carelessly) edited or corrupt files may result in a crash though.
     *
     * @param path the path to the runhistory file
     * @return history  the ContainerHistory resulting from parsing the file
     */
    public static ContainerHistory parseRunHistoryFileToContainerHistory(Path path) {
        ContainerHistory history = new ContainerHistory();
        Scanner scanner = getScannerWithPath(path);

        if (scanner == null) {
            return null;
        }

        // skip the first line (column headings)
        scanner.next();

        while (scanner.hasNextLine()) {
            Event event = parseLine(scanner.nextLine());
            if (event != null) {
                history.assertEvent(event);
            }
        }

        return history;
    }

    private static Scanner getScannerWithPath(Path path) {
        File file = path.toFile();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Not found: " + path.toString());
            return null;
        }
        return scanner;
    }

    private static Event parseLine(String line) {
        if (line == null || line.trim().length() == 0) {
            return null;
        }
        return EventParser.parse(line);
    }
}
