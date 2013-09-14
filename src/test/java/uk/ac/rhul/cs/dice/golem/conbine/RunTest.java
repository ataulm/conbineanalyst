package uk.ac.rhul.cs.dice.golem.conbine;

import org.testng.annotations.Test;

public class RunTest {
    private static final int label = 1;
    private static final Run run  = new Run(label);

    @Test
    public void it_should_not_be_possible_to_create_a_combination_without_setting_its_label() throws Exception {
        assert(run.getLabel() > 0);
    }

    @Test
    public void when_parsing_fails_the_run_should_change_its_state_to_failed() throws Exception {
        run.fail();
        assert(run.hasFailed());
    }

    @Test
    public void when_a_run_is_initialised_its_state_should_not_be_failed() throws Exception {
        assert(!run.hasFailed());
    }

    @Test
    public void when_a_run_is_initialised_its_state_should_not_be_successful() throws Exception {
        assert(!run.hasSucceeded());
    }

}
