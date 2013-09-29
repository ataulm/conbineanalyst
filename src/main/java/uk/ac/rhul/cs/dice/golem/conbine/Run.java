package uk.ac.rhul.cs.dice.golem.conbine;

import java.nio.file.Path;
import java.nio.file.Paths;

import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;


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
        if (history == null) {
            System.out.println("History was not parsed for run: " + constructNameOfRunHistoryFile());
        }
    }

    private String getExpectedPathToRunHistoryFile() {
        return constructNameOfRunHistoryFile();
    }

    private String constructNameOfRunHistoryFile() {
        return context.getLabel() + "_" + runLabel + RunParser.RUN_HISTORY_EXT;
    }

}
