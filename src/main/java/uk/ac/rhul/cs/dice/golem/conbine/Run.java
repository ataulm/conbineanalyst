package uk.ac.rhul.cs.dice.golem.conbine;

import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Run {
    private final int runLabel;
    private ContainerHistory history;
    private Combination context;

    public Run(Combination context, int runLabel) {
        this.runLabel = runLabel;
        this.context = context;
    }

    public int getLabel() {
        return runLabel;
    }

    public void parseRun() {
        Path path = Paths.get(getExpectedPathToRunHistoryFile());
        history = RunParser.parseRunHistoryFileToContainerHistory(path);
    }

    private String getExpectedPathToRunHistoryFile() {
        return context.getLabel() + "_" + runLabel + RunParser.RUN_HISTORY_EXT;
    }
}
