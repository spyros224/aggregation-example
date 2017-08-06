package org.experiment.service;

/**
 * Describes the functionality for aggregating Transaction Data within a 60-second time frame.
 */
public interface StatisticsService {

    /**
     * Decides whether a transaction timestamp belongs to the 60-second frame.
     *
     * @param timestamp The timestamp in millis
     *
     * @return True if the timestamp is within the time frame. False otherwise.
     */
    boolean isTransactionInTimeFrame(long timestamp);

    /**
     * Processes the transaction amount according to the specific metrics
     *
     * @param amount The transaction amount.
     */
    void aggregate(double amount);
}
