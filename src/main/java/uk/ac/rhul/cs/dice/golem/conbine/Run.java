package uk.ac.rhul.cs.dice.golem.conbine;

import org.javatuples.Quartet;
import org.javatuples.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    private final int runLabel;
    private Boolean parseStatus;
    private ArrayList<Tuple> lines = new ArrayList<>();

    public Run(int runLabel) {
        this.runLabel = runLabel;
    }

    public int getLabel() {
        return runLabel;
    }

    private void succeed() {
        parseStatus = true;
    }

    private void fail() {
        System.err.println("Failed to parse run: " + getLabel());
        parseStatus = false;
    }

    /**
     * Indicates whether the container history for this run has successfully
     * been parsed.
     *
     * @return success  value is true if the run was successfully parsed, else false
     */
    public boolean hasSucceeded() {
        return parseStatus != null && parseStatus;
    }

    /**
     * Indicates whether the container history for this run was unable to
     * successfully complete.
     *
     * @return failed  value is true if the run failed to parse, else false
     */
    public boolean hasFailed() {
        return parseStatus != null && !parseStatus;
    }

    public void parse(Path path) {
        if (isValidRunHistoryFile(path)) {
            try {
                convertToHistoryAndParse(path);
            } catch (FileNotFoundException e) {
                // checked during isValidRunHistoryFile(Path)
            }
        } else {
            fail();
        }
    }


    private void convertToHistoryAndParse(Path path) throws FileNotFoundException {
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
                fail();
                return;
            }
        }
        succeed();
        System.out.println("Processed " + linesProcessed + " lines (inc. headers).");
    }

    private void iterateHistory(Path path) {

    }

    /**
     * Naive check on validity of history file - checks extension and existence.
     *
     * @param path the path of the file to check validity for
     * @return isValid  true if file exists and has correct file extension, else false
     */
    private boolean isValidRunHistoryFile(Path path) {
        return path.toString().endsWith(".runhistory") && Files.exists(path);
    }
}
