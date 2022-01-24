package ru.ibs.planeta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.ibs.planeta.logic.Model;
import ru.ibs.planeta.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    private final AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer bf = new StringBuffer();
        String line;

        try{
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null){
                bf.append(line);
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        JsonObject jObject = gson.fromJson(String.valueOf(bf), JsonObject.class);

        req.setCharacterEncoding("UTF-8");

        String name = jObject.get("name").getAsString();
        String surname = jObject.get("name").getAsString();
        double salary = jObject.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.addUser(user, counter.getAndIncrement());

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson(model.getUserList()));
    }

    protected void doPostOld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double salary = Double.parseDouble(request.getParameter("salary"));
        User user = new User(name, surname, salary);
        model.addUser(user, counter.getAndIncrement());

        pw.println("<html>" +
                "<h3>Пользователь " + name + " " + surname + " с зарплатой=" + salary + " успешно создан</h3>" +
                "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
                "<a href=\"index.jsp\"> Домой </a>" +
                "</html>");
    }
}
