package uk.ac.rhul.cs.dice.golem.conbine;

public class Run {
    private final int runLabel;
    private Boolean parseStatus;

    public Run(int runLabel) {
        this.runLabel = runLabel;
    }

    public int getLabel() {
        return runLabel;
    }

    public void succeed() {
        parseStatus = true;
    }

    public void fail() {
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
}
