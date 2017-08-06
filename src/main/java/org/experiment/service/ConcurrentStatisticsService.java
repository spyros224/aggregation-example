package org.experiment.service;

import org.experiment.service.domain.Aggregator;
import org.experiment.service.domain.StatisticsContainer;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implements a Statistic Service which needs to be accessed and aggregate data concurrently.
 *
 * Since the statistics refer to data processed in the previous 60 seconds frame and the requirement to be accessed in
 * constant time, it is important to consider an aggregator and a view of the data. Since the API must show the data
 * from the previous 60-second frame, the view must refer to the Statistics Container that is shown by the API, while
 * the Aggregator will be used to aggregate data within the current 60-second frame. This ensures the constant time and
 * space required for the data.
 */
public class ConcurrentStatisticsService implements StatisticsService, StatisticsPresentation {

    private final AtomicLong sixtySecondIndicator = new AtomicLong();

    private Aggregator inProgressAggregator = new Aggregator();
    private StatisticsContainer publicStatisticsContainer;

    public ConcurrentStatisticsService() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                publicStatisticsContainer = inProgressAggregator.createContainer();
                inProgressAggregator = new Aggregator();

                sixtySecondIndicator.getAndSet(Instant.now().toEpochMilli());
            }
        }, 0, 60000);
    }

    @Override
    public boolean isTransactionInTimeFrame(long timestamp) {
        long duration = sixtySecondIndicator.get() - timestamp;
        return duration <= 60000 && duration >= 0;
    }

    @Override
    public void aggregate(double amount) {
        inProgressAggregator.processTransactionAmount(amount);
    }

    @Override
    public StatisticsContainer getStatistics() {
        return publicStatisticsContainer;
    }
}
