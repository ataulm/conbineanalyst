package uk.ac.rhul.cs.dice.golem.conbine;


import org.junit.Test;

public class TestCombinationShould {
    private final int label = 1;
    private final Combination combination = new Combination(label);

    @Test
    public void contain_zero_runs_when_it_is_initialised() throws Exception {
        assert (combination.size() == 0);
    }

    @Test
    public void increment_combination_size_by_one_when_a_run_is_added() throws Exception {
        int sizeBefore = combination.size();
        combination.add(new Run(label));
        assert (combination.size() - sizeBefore == 1);
    }

    @Test
    public void be_initialised_with_a_non_zero_label() throws Exception {
        assert (combination.getLabel() > 0);
    }

    @Test
    public void return_the_correct_run_when_a_run_is_requested_by_its_label() throws Exception {
        Run run = new Run(label);
        combination.add(run);
        assert (combination.get(label).equals(run));
    }
}
