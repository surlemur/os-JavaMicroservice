package com.zuehlke.doa;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyMain {

    private static Logger LOG = LoggerFactory.getLogger(MyMain.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        initLogging();

        LOG.info("Starting server...");
        LOG.info("DB_URL=" + System.getenv("DB_URL"));

        startServer();

        LOG.info("Server started");
        LOG.debug("Internal URI is " + getBaseURI() + "myResource");

        Thread.currentThread().join();
    }

    private static void initLogging() {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        java.util.logging.Logger.getLogger("global").setLevel(Level.FINEST);
    }

    private static void startServer() throws IOException {
        HttpServer server = createServer(getBaseURI().getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop(0);
        }));

        // create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);

        // map JAX-RS handler to the server root
        server.createContext(getBaseURI().getPath(), handler);

        // start the server
        server.start();
    }

    private static HttpServer createServer(int port) throws IOException {
        return HttpServer.create(new InetSocketAddress(port), 0);
        // return GrizzlyHttpServerFactory.createHttpServer(getBaseURI(),
        // false);
    }

    public static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(8080).build();
    }

}
