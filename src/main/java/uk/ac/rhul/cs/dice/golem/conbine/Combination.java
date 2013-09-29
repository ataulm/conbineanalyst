package uk.ac.rhul.cs.dice.golem.conbine;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Run> runs;
    private final int LABEL;


    public Combination(ResultsController context, int label, int numberOfRuns) {
        runs = new ArrayList<Run>(numberOfRuns);
        this.LABEL = label;
    }

    public int size() {
        return runs.size();
    }

    public void add(Run run) {
        runs.add(run);
    }

    public int getLabel() {
        return LABEL;
    }

    public Run get(int runLabel) {
        for (Run run : runs) {
            if (run.getLabel() == runLabel) {
                return run;
            }
        }
        return null;
    }

    public void initialiseRuns(int numberOfRuns) {
        for (int i = 0; i < numberOfRuns; i++) {
            runs.add(new Run(this, i));
        }
    }

    public void parseRuns() {
        for (Run run : runs) {
            run.parseRun();
        }
    }
}
