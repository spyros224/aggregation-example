package org.experiment.service.domain;

/**
 * Presents the Aggregated Statistics of a 60-second session.
 */
public class StatisticsContainer {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private int count;

    StatisticsContainer(double sum, double avg, double max, double min, int count) {
        this.sum = sum;
        this.avg = avg;
        this.max = (max == Double.NEGATIVE_INFINITY) ? 0 : max;
        this.min = (min == Double.POSITIVE_INFINITY) ? 0 : min;
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public int getCount() {
        return count;
    }
}
