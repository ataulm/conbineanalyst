package uk.ac.rhul.cs.dice.golem.conbine;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.javatuples.Tuple;

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
     * @return failed  value is true if the run failed to parseRunHistoryFileToContainerHistory, else false
     */
    public boolean hasFailed() {
        return parseStatus != null && !parseStatus;
    }

    public void parseRunHistoryFileToContainerHistory(Path path) {
        try {
            convertToHistoryAndParse(path);
        } catch (FileNotFoundException e) {
            fail();
            e.printStackTrace();
        }
    }


    private void convertToHistoryAndParse(Path path) throws FileNotFoundException {

    }

    /**
     * Naive check on validity of history file - checks extension.
     *
     * @param path the path of the file to check validity for
     * @return isValid  true if file exists and has correct file extension, else false
     */
    private boolean fileHasCorrectExtension(Path path) {
        return path.toString().endsWith(".runhistory");
    }

    private void iterateHistory(Path path) {

    }
}
