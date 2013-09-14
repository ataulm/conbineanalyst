package uk.ac.rhul.cs.dice.golem.conbine;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Run> runs;
    private final int label;

    public Combination(int label) {
        runs = new ArrayList<Run>();
        this.label = label;
    }

    public int size() {
        return runs.size();
    }

    public void add(Run run) {
        runs.add(run);
    }

    public int getLabel() {
        return label;
    }

    public Run get(int runLabel) {
        for (Run run:runs) {
            if (run.getLabel() == runLabel) {
                return run;
            }
        }
        return null;
    }
}
