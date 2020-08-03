package ru.yurima.oldgoodforumback.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.repositories.TopicRepository;
import ru.yurima.oldgoodforumback.services.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MainServlet extends HttpServlet {
    DataService dataService;
    ObjectMapper mapper;

    public MainServlet(DataService dataService) {
        this.dataService = dataService;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        List<Topic> topics = dataService.getAllTopics();
        writer.write(mapper.writeValueAsString(topics));
        writer.flush();
    }
}
