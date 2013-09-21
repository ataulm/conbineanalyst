package uk.ac.rhul.cs.dice.golem.conbine;

import java.nio.file.Files;
import java.nio.file.Path;

public class Run {
    private final int runLabel;
    private Boolean parseStatus;

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
            succeed();
        } else {
            fail();
        }
    }

    private void iterateHistory() {

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
