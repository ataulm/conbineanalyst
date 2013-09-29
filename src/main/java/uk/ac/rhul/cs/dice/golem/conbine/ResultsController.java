package uk.ac.rhul.cs.dice.golem.conbine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ResultsController {
    public static final boolean DEBUG = true;
    private static final int DEFAULT_NUM_COMBINATIONS = 27;
    private static int DEFAULT_NUM_RUNS_PER_COMBINATION = 100;

    private static final List<String> DEFAULT_SELECTED_BUYERS = new ArrayList<>(Arrays.asList("buyer_1", "buyer_2"));

    private final String[] BUYERS;
    private final List<Combination> combinations;

    private ResultsController(int combo, int runs, String[] buyers) {
        combinations = new ArrayList<>(combo);
        BUYERS = buyers;
        initialiseCombinations(combo, runs);
        parse();
    }

    private void initialiseCombinations(int combo, int runs) {
        for (int i = 0; i < combo; i++) {
            combinations.add(new Combination(this, i + 1, runs));
        }
    }

    public void parse() {
        for (int i = 0; i < combinations.size(); i++) {
            combinations.get(i).parseRuns();
        }
    }

    public int getNumberOfCombinations() {
        return combinations.size();
    }

    public String[] getBUYERS() {
        return BUYERS;
    }

    /**
     * **************************************************************************************************************
     */

    private static ResultsController controller;
    private static final boolean GET_USER_INPUT = true;

    public static void main(String[] args) {
        if (GET_USER_INPUT) {
            onCreateWithUserInput();
        } else {
            onCreateWithDefaults();
        }

        controller.parse();
    }

    private static void onCreateWithUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int combo = queryUserForNumberOfCombos(br);
        int runs = queryUserForNumberOfRuns(br);
        String[] buyers = queryUserForSelectedBuyers(br);

        controller = new ResultsController(combo, runs, buyers);
    }

    private static void onCreateWithDefaults() {
        controller = new ResultsController(DEFAULT_NUM_COMBINATIONS,
                DEFAULT_NUM_RUNS_PER_COMBINATION,
                DEFAULT_SELECTED_BUYERS.toArray(new String[DEFAULT_SELECTED_BUYERS.size()]));
    }

    private static String[] queryUserForSelectedBuyers(BufferedReader br) {
        System.out.println("\nEnter the IDs of the buyer agents for which you want to collect metrics, pressing \"return\" after each ID");
        System.out.println("(type 0 to start processing, or -1 to quit)");
        List<String> buyers = new ArrayList<>();
        try {
            String input = null;
            do {
                input = br.readLine();
                if (input.equals("0")) {
                    break;
                } else if (input.equals("-1")) {
                    System.exit(0);
                }
                buyers.add(input);
            } while (input != null);
        } catch (IOException e) {
            onUnexpectedInputForSelectedBuyers();
            return DEFAULT_SELECTED_BUYERS.toArray(new String[DEFAULT_SELECTED_BUYERS.size()]);
        }

        return buyers.toArray(new String[buyers.size()]);
    }

    private static void onUnexpectedInputForSelectedBuyers() {
        System.out.print("Didn't catch that, going to look for: ");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < DEFAULT_SELECTED_BUYERS.size(); i++) {
            s.append(DEFAULT_SELECTED_BUYERS.get(i) + ", ");
        }
        s.deleteCharAt(s.length());
        System.out.println(s.toString());
    }

    private static int queryUserForNumberOfRuns(BufferedReader br) {
        System.out.println("\nHow many runs per combination? ");
        int runs;
        try {
            runs = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Didn't catch that, setting runs per combination to " + DEFAULT_NUM_RUNS_PER_COMBINATION);
            runs = DEFAULT_NUM_RUNS_PER_COMBINATION;
        }
        return runs;
    }

    private static int queryUserForNumberOfCombos(BufferedReader br) {
        System.out.println("How many combinations are there in total? ");
        int combos = 0;
        try {
            combos = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Didn't catch that, setting number of combinations to " + DEFAULT_NUM_COMBINATIONS);
            combos = DEFAULT_NUM_COMBINATIONS;
        }
        return combos;
    }
}
