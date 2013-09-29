package uk.ac.rhul.cs.dice.golem.conbine;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around a list of Strings - it's intended that this class is used
 * to store all information for the PM it represents (all combinations, all selected agents)
 * like in ConbineResults.
 */
public class PerformanceMetric {
    private final List<String> LINES;
    private final String NAME;

    public PerformanceMetric(String name) {
        NAME = name;
        LINES = new ArrayList<String>();
    }

    public void addLine(Combination combination) {
        /**
         * combination.getLabel();
         * combination.getMetricForAgent(name, "buyer_1");
         * combination.getMetricForAgent(name, "buyer_2");
         * combination.getMetricForAgent(name, "...");
         * combination.getMetricForAgent(name, "buyer_n");
         */
    }

}
