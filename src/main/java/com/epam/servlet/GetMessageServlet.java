package com.epam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMessageServlet extends HttpServlet {

    private final Map<Integer, String> messages = new HashMap<>();

    public GetMessageServlet() {
        super();
        messages.put(0, "Why are we still here?");
        messages.put(1, "Just to suffer?");
        messages.put(2, "Every night, I can feel my leg…");
        messages.put(3, "and my arm…");
        messages.put(4, "even my fingers.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String messageNumber = req.getParameter("message");
        final String htmlString = getHtmlString("src/main/webapp/message.html", messageNumber);
        try (final PrintWriter writer = resp.getWriter()) {
            writer.write(htmlString);
            writer.flush();
        }
    }

    private String getHtmlString(String path, String messageNumber) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfMessage = 0;
        try {
            numberOfMessage = Integer.parseInt(messageNumber);
        } catch (NumberFormatException e) {
        }
        final int size = messages.size();

        try (final Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(stringBuilder::append);
        }

        String result = "";
        if (numberOfMessage < size) {
            result = String.format(stringBuilder.toString(), size, messages.get(numberOfMessage));
        } else {
            result = String.format(stringBuilder.toString(), size, "No message with this number");
        }
        return result;
    }
}
