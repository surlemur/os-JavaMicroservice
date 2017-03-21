package com.zuehlke.doa;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting server...");
        System.out.println("DB_URL=" + System.getenv("DB_URL"));

        startServer();

        System.out.println("Server started");
        System.out.println("Internal URI is " + getBaseURI() + "myResource");
        System.out.println("To find out exposed port, do a 'docker ps'");

        Thread.currentThread().join();
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
