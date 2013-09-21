package uk.ac.rhul.cs.dice.golem.conbine;

import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestRunShould {
    private static final int label = 1;
    private final Run run = new Run(label);

    private final URL url = getClass().getClassLoader().getResource(".");
    private final Path executionDir = Paths.get(String.valueOf(url.getPath()));
    private final Path sampleHistoryDir = Paths.get(
            executionDir.getParent().getParent().toString(), "src", "test", "sampleHistory");

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
    public void change_its_parse_state_to_failed_when_a_path_with_the_wrong_file_extension_is_given() throws Exception {
        Path path = Paths.get(sampleHistoryDir.toString(), "1_1.wrongextension");
        run.parse(path);
        assert (run.hasFailed());
    }

    @Test
    public void change_its_parse_state_to_failed_when_a_non_existent_path_is_given() throws Exception {
        Path path = Paths.get("non-existent-path.runhistory");
        run.parse(path);
        assert (run.hasFailed());
    }

    @Test
    public void change_its_parse_state_to_successful_when_it_parses_a_valid_file() throws Exception {
        Path path = Paths.get(sampleHistoryDir.toString(), "1_1.runhistory");
        run.parse(path);
        assert (run.hasSucceeded());
    }
}
