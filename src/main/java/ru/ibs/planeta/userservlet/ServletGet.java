package ru.ibs.planeta.userservlet;

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

@WebServlet(urlPatterns = "/get")
public class ServletGet extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Model model = Model.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        int id = jObject.get("id").getAsInt();

        if(id == 0){
            pw.print(gson.toJson(model.getUserList()));
        }else if(id > 0){
            if(id > model.getUserList().size()){
                pw.print(gson.toJson("Такого пользователя нет"));
            }else{
                User userById = model.getUser(id);
                pw.print(gson.toJson(userById));
            }
        } else{
            pw.print(gson.toJson("ID Пользователя должно быть больше нуля"));
        }
    }
}
