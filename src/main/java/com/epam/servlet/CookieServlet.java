package com.epam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = "<html><h3>Cookies!</h3></html>";
        final Cookie[] cookies = req.getCookies();
        if (null == cookies) {
            resp.addCookie(new Cookie("Pechenka", "Vkusnaia"));
        } else {
            html = "<html><h3>" + Arrays.toString(cookies) + "</h3></html>";
        }
        try(final PrintWriter writer = resp.getWriter()) {
            writer.write(html);
            writer.flush();
        }
    }
}
