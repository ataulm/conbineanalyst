package uk.ac.rhul.cs.dice.golem.conbine;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

import org.javatuples.Quartet;

import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;

public class RunParser {
    public static ContainerHistory parseRunHistoryFileToContainerHistory(Path path) {
        ContainerHistory history = ContainerHistory.;

        if (!fileHasCorrectExtension(path)) {
            fail();
            return;
        }

        File file = path.toFile();
        Scanner scanner = new Scanner(file);
        int linesProcessed = 0;

        if (scanner.hasNext()) {
            StringBuilder startProcessingString = new StringBuilder();
            startProcessingString.append("Starting processing of run (").append(getLabel()).append("). ")
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
    }

}
