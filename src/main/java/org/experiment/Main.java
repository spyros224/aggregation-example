package org.experiment;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), AppResourceConfig.createApp(), false);
    }

    /**
     * Main method.
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            server.start();

            System.out.println(
                    String.format("Application started.\nTry out %s\nStop the application using CTRL+C", BASE_URI));

            Thread.currentThread().join();
        } catch(InterruptedException | IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

