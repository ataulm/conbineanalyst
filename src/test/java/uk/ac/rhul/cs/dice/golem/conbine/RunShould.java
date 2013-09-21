package uk.ac.rhul.cs.dice.golem.conbine;

import org.junit.Test;

public class RunShould {
    private static final int label = 1;
    private final Run run = new Run(label);

    @Test
    public void be_initialised_with_a_non_zero_label() throws Exception {
        assert (run.getLabel() > 0);
    }

    @Test
    public void not_be_in_a_failed_state_when_it_is_initialised() throws Exception {
        assert (!run.hasFailed());
    }

    @Test
    public void not_be_in_a_successful_state_when_it_is_initialised() throws Exception {
        assert (!run.hasSucceeded());
    }

    @Test
    public void change_its_parse_state_to_failed_when_fail_is_called() throws Exception {
        run.fail();
        assert (run.hasFailed());
    }

    @Test
    public void change_its_parse_state_to_successful_when_succeed_is_called() throws Exception {
        run.succeed();
        assert (run.hasSucceeded());
    }
}
