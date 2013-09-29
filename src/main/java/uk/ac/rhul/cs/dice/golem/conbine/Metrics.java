package uk.ac.rhul.cs.dice.golem.conbine;

public class Metrics {
    private final long startTime;
    private final long acceptTime;
    private final double utility;
    private final boolean successful;

    private Metrics(long startTime, long acceptTime, double utility, boolean successful) {
        this.startTime = startTime;
        this.acceptTime = acceptTime;
        this.utility = utility;
        this.successful = successful;
    }

    static class Builder {
        private long startTime = -1;
        private long acceptTime = -1;
        private double utility = -1;
        private Boolean successful;

        public void startTime(long startTime) {
            this.startTime = startTime;
        }

        public void acceptTime(long acceptTime) {
            this.acceptTime = acceptTime;
        }

        public void utility(double utility) {
            this.utility = utility;
        }

        public void successful(boolean successful) {
            this.successful = successful;
        }

        public Metrics build() {
            if (startTime < 0 || acceptTime < 0 || utility < 0 || successful == null) {
                throw new IllegalStateException("All properties must be set.");
            }
            return new Metrics(startTime, acceptTime, utility, successful);
        }
    }
}