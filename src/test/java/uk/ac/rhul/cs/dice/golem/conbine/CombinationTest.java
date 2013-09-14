package uk.ac.rhul.cs.dice.golem.conbine;

import org.testng.annotations.Test;

public class CombinationTest {
    private static Combination combination = new Combination();

    @Test
    public void combination_should_contain_zero_runs_when_initialised() throws Exception {
        assert(combination.size() == 0);
    }

    @Test
    public void adding_a_run_should_increment_combination_size_by_one() throws Exception {
        int sizeBefore = combination.size();
        combination.add(new Run()) ;
        assert(combination.size() - sizeBefore == 1);
    }

    @Test
    public void adding_an_object_that_is_not_a_run_should_not_be_possible() throws Exception {
        combination.add(new Object());
    }
}
