package org.experiment;

import org.experiment.service.StatisticsServiceBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static org.glassfish.jersey.logging.LoggingFeature.DEFAULT_MAX_ENTITY_SIZE;
import static org.glassfish.jersey.logging.LoggingFeature.Verbosity.PAYLOAD_ANY;

class AppResourceConfig extends ResourceConfig {

    static ResourceConfig createApp() {

        return new ResourceConfig().packages("org.experiment")
                                   .register(JacksonFeature.class)
                                   .register(new StatisticsServiceBinder())
                                   .register(new LoggingFeature(Logger.getLogger(Main.class.getName()),
                                           INFO, PAYLOAD_ANY, DEFAULT_MAX_ENTITY_SIZE));
    }
}
