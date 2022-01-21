package ru.ibs.planeta;

import java.io.*;
import java.rmi.ServerException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/servlet")
public class Servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.println("Hello, World! !!!!");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {

    }
}