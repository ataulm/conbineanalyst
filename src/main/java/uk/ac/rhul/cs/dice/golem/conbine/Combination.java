package uk.ac.rhul.cs.dice.golem.conbine;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Run> runs;

    public Combination() {
        runs = new ArrayList<Run>();
    }

    public int size() {
        return runs.size();
    }

    public void add(Run run) {
        runs.add(run);
    }
}
