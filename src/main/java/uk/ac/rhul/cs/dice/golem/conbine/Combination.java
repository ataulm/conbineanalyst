package uk.ac.rhul.cs.dice.golem.conbine;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Run> runs;
    private final int LABEL;
    private final ResultsController context;

    public Combination(ResultsController context, int label, int numberOfRuns) {
        this.context = context;
        this.LABEL = label;
        runs = new ArrayList<>(numberOfRuns);
        initialiseRuns(numberOfRuns);
    }

    public int getLabel() {
        return LABEL;
    }

    private void initialiseRuns(int numberOfRuns) {
        for (int i = 0; i < numberOfRuns; i++) {
            runs.add(new Run(this, i + 1));
        }
    }

    public void parseRuns() {
        for (Run run : runs) {
            run.parseRun();
        }
    }
}
