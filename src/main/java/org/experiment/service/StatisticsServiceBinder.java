package org.experiment.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * Simple DI binding for the {@link ConcurrentStatisticsService} and its subsequent interfaces.
 */
public class StatisticsServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(ConcurrentStatisticsService.class).to(StatisticsPresentation.class)
                                               .to(StatisticsService.class)
                                               .in(Singleton.class);
    }
}
