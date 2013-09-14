package uk.ac.rhul.cs.dice.golem.conbine;

import org.testng.annotations.Test;

public class CombinationTest {
    private static final int label = 1;
    private static final Combination combination = new Combination(label);

    @Test
    public void combination_should_contain_zero_runs_when_initialised() throws Exception {
        assert(combination.size() == 0);
    }

    @Test
    public void adding_a_run_should_increment_combination_size_by_one() throws Exception {
        int sizeBefore = combination.size();
        combination.add(new Run(label)) ;
        assert(combination.size() - sizeBefore == 1);
    }

    @Test
    public void it_should_not_be_possible_to_create_a_combination_without_setting_its_label() throws Exception {
        assert(combination.getLabel() > 0);
    }

    @Test
    public void it_should_be_possible_to_retrieve_a_run_by_its_label() throws Exception {
        Run run = new Run(label);
        combination.add(run);
        assert(combination.get(label).equals(run));
    }
}
