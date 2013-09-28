package uk.ac.rhul.cs.dice.golem.conbine;

import org.javatuples.Quartet;
import org.javatuples.Tuple;
import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunParser {

    public static final String RUN_HISTORY_EXT = ".runhistory";

    public static ContainerHistory parseRunHistoryFileToContainerHistory(Path path) {
        ContainerHistory history;
        List<Tuple> lines = new ArrayList<>();

        if (!fileHasCorrectExtension(path)) {
            return null;
        }

        File file = path.toFile();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int linesProcessed = 0;

        if (scanner.hasNext()) {
            StringBuilder startProcessingString = new StringBuilder();
            startProcessingString.append("Starting processing of run (").append(path.getFileName()).append("). ")
                    .append("Skipping column headings: ").append(scanner.nextLine());
            System.out.println(startProcessingString.toString());
            linesProcessed++;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.trim().length() == 0) {
                continue;
            }

            linesProcessed++;

            String[] splitLine = line.split(":::");

            if (splitLine.length == 3) {
                lines.add(new Quartet<>(splitLine[0], splitLine[1], null, splitLine[2]));
            } else if (splitLine.length == 4) {
                lines.add(new Quartet<>(splitLine[0], splitLine[1], splitLine[2], splitLine[3]));
            } else {
                System.err.println("Malformed run history.");
                return null;
            }
        }
        System.out.println("Processed " + linesProcessed + " lines (inc. headers).");
        return null;
    }

    private static boolean fileHasCorrectExtension(Path path) {
        return path.getFileName().endsWith(RUN_HISTORY_EXT);
    }

}
