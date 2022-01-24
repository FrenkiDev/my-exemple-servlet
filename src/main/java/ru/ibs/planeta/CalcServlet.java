package ru.ibs.planeta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.ibs.planeta.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        req.setCharacterEncoding("UTF-8");;

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        double a = jObject.get("a").getAsDouble();
        double b = jObject.get("b").getAsDouble();
        String math = jObject.get("math").getAsString();

        Calc tmp = new Calc();
        tmp.calculate(a, b, math);

        pw.print(gson.toJson(tmp));
    }
}
