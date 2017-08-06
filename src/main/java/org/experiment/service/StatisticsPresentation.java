package org.experiment.service;

import org.experiment.service.domain.StatisticsContainer;

/**
 * Describes how a 60-second framework is presented through the API exposure.
 */
public interface StatisticsPresentation {

    /**
     * Gets a {@code Statistics Container} with the metrics that a 60-second frame has.
     *
     * @return A {@link StatisticsContainer} Object containing the metrics
     */
    StatisticsContainer getStatistics();
}
