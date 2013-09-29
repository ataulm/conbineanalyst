package uk.ac.rhul.cs.dice.golem.conbine;

/**
 * Bedour, you can cut and paste PM2-PM4 to the correct cells :P I can't CSV to merged cells
 * <p/>
 * We want a CSV file formatted as follows:
 * _____________________________________________________________________________________________________
 * |
 * | PM1: long avgTimePerSuccessfulRun = totalTimeForSuccessfulRuns / successfulRuns;
 * |
 * | CombinationNumber,        SelBuyer1,       SelBuyer2,       SelBuyer3,       ...,         SelBuyerN
 * | 1,                        0.2,             0.4,             0.3,             ...,         0.2
 * | 2,                        0.2,             0.4,             0.3,             ...,         0.2
 * | 3,                        0.2,             0.4,             0.3,             ...,         0.2
 * | ...,                      ...,             ...,             ...,             ...,         ...,
 * | m,                        0.2,             0.4,             0.3,             ...,         0.2
 * |
 * | PM2: double successNegotiation = successfulRuns / runsPerCombo;
 * | ...
 * |
 * | PM3: double avgOverallUtility = totalUtility / runsPerCombo;
 * | ...
 * |
 * | PM4: double avgUtilityForSuccessfulRuns = totalUtility / successfulRuns;
 * | ...
 * |____________________________________________________________________________________________________
 * <p/>
 * To do this, maintain 4 List<String> (one for each PM)
 */
public class ConbineResults {
    private final PerformanceMetric AVERAGE_TIME_PER_SUCCESSFUL_RUN;
    private final PerformanceMetric RATIO_SUCCESSFUL_TO_UNSUCCESSFUL_NEGOTIATIONS;
    private final PerformanceMetric AVERAGE_OVERALL_UTILITY;
    private final PerformanceMetric AVERAGE_UTILITY_FOR_SUCCESSFUL_RUNS;

    public ConbineResults() {
        AVERAGE_TIME_PER_SUCCESSFUL_RUN = new PerformanceMetric("AVERAGE_TIME_PER_SUCCESSFUL_RUN");
        RATIO_SUCCESSFUL_TO_UNSUCCESSFUL_NEGOTIATIONS = new PerformanceMetric("RATIO_SUCCESSFUL_TO_UNSUCCESSFUL_NEGOTIATIONS");
        AVERAGE_OVERALL_UTILITY = new PerformanceMetric("AVERAGE_OVERALL_UTILITY");
        AVERAGE_UTILITY_FOR_SUCCESSFUL_RUNS = new PerformanceMetric("AVERAGE_UTILITY_FOR_SUCCESSFUL_RUNS");
    }


}
