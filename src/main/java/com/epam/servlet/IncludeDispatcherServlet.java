package com.epam.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = "<html><h3>Hi from include servlet</h3></html>";
        resp.getWriter().write(html + " ");

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/hi");
        requestDispatcher.include(req, resp);
    }
}
