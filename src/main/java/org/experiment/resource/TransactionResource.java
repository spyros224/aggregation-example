package org.experiment.resource;

import org.experiment.resource.domain.Transaction;
import org.experiment.service.StatisticsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.requireNonNull;

/**
 * Transaction resource (exposed at "transactions" path)
 */
@Path("/")
public class TransactionResource {

    private final StatisticsService statisticsService;

    @Inject
    public TransactionResource(StatisticsService statisticsService) {
        this.statisticsService = requireNonNull(statisticsService);
    }

    @POST
    @Path("transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(Transaction transaction) {
        if (statisticsService.isTransactionInTimeFrame(transaction.getTimestamp())) {
            statisticsService.aggregate(transaction.getAmount());
            return Response.created(null).build();
        }

        return Response.noContent().build();
    }
}
