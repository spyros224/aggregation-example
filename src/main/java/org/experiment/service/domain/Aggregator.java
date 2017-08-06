package org.experiment.service.domain;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Aggregates the required transaction data and produces respective results.
 */
public final class Aggregator {

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicReference<Double> sum = new AtomicReference<>(0.0);
    private AtomicReference<Double> min = new AtomicReference<>(Double.POSITIVE_INFINITY);
    private AtomicReference<Double> max = new AtomicReference<>(Double.NEGATIVE_INFINITY);
    private AtomicReference<Double> avg = new AtomicReference<>(0.0);

    public void processTransactionAmount(double amount) {
        count.getAndIncrement();
        sum.getAndUpdate(aDouble -> aDouble + amount);
        avg.getAndUpdate(avg -> avg + ((amount - avg) / count.get()));
        min.getAndUpdate(min -> (amount < min) ? amount : min);
        max.getAndUpdate(max -> (amount > max) ? amount : max);
    }

    public StatisticsContainer createContainer() {
        return new StatisticsContainer(sum.get(), avg.get(), max.get(), min.get(), count.get());
    }
}
