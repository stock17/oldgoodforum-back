package ru.yurima.oldgoodforumback.engine;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.yurima.oldgoodforumback.servlets.MainServlet;

public class Engine {
    public static void main(String[] args) throws Exception {

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new MainServlet()),"/");
        HandlerList handlerList = new HandlerList(contextHandler);

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
