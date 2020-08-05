package ru.yurima.oldgoodforumback.servlets;

import ru.yurima.oldgoodforumback.services.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentServlet extends HttpServlet {

    private DataService service;

    public ContentServlet(DataService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topic");

    }
}
