package ru.ibs.planeta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.ibs.planeta.logic.Model;
import ru.ibs.planeta.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/update")
public class ServletUpDate extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Model model = Model.getInstance();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuffer bf = new StringBuffer();
        String line;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                bf.append(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        JsonObject jObject = gson.fromJson(String.valueOf(bf), JsonObject.class);

        req.setCharacterEncoding("UTF-8");

        int id = jObject.get("id").getAsInt();
        User user = model.getUser(id);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson("До обновления : "));
        pw.print(gson.toJson(user));

        if (user == null) {
            pw.print(gson.toJson("Такого пользователя нет"));
        } else {
            if (jObject.get("name") != null) {
                user.setName(jObject.get("name").getAsString());
            }
            if (jObject.get("surname") != null) {
                user.setSurname(jObject.get("surname").getAsString());
            }
            if (jObject.get("salary") != null) {
                user.setSalary(jObject.get("salary").getAsDouble());
            }
        }

        pw.print(gson.toJson("После обновления : "));
        pw.print(gson.toJson(user));
    }
}
