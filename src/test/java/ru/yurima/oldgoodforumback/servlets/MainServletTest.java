package ru.yurima.oldgoodforumback.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;
import ru.yurima.oldgoodforumback.services.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MainServletTest {

    @Mock
    DataService service;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    List<Topic> topics;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        topics = new ArrayList<>();
        User author = new User("name", "login", "password");
        topics.add(new Topic("Title1", author));
        topics.add(new Topic("Title2", author));
        topics.add(new Topic("Title3", author));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testResponceTopicsAsJson () throws ServletException, IOException {
        when(service.getAllTopics()).thenReturn(topics);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        new MainServlet(service).doGet(request, response);

        String result = stringWriter.getBuffer().toString();
        assertTrue(result.contains("Title1"));
        assertTrue(result.contains("name"));
    }
}