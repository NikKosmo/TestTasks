package ru.ccsss.servlet;


import ru.ccsss.dao.CounterDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CounterServlet extends HttpServlet {
    private final CounterDAO counterDAO;

    public CounterServlet(CounterDAO counterDAO) {
        this.counterDAO = counterDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html> <body>" +
                "<form method=\"post\"><input type=\"submit\" value=\"Increment\" /></form>" +
                "<form method=\"post\"><input type=\"submit\" value=\"Decrement\" /><input type=\"hidden\" name=\"method\"  value=\"Delete\" /></form>" +
                "<p> value = " + counterDAO.getCounter() +
                "</p></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String method = request.getParameter("method");
        if(method == null || !method.equals("Delete")) {
            counterDAO.incrementCounter();
        } else {
            doDelete(request, response);
            return;
        }

        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        counterDAO.decrementCounter();
        doGet(request, response);
    }

}
