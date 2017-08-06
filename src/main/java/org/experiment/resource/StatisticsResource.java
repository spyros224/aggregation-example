package org.experiment.resource;

import org.experiment.service.StatisticsPresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.requireNonNull;

/**
 * Statistics resource (exposed at "statistics" path)
 */
@Path("/")
public class StatisticsResource {

    private final StatisticsPresentation presentation;

    @Inject
    public StatisticsResource(StatisticsPresentation presentation) {
        this.presentation = requireNonNull(presentation);
    }

    @GET
    @Path("statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statisticsPresentation() {
        return Response.accepted(presentation.getStatistics()).build();
    }
}
