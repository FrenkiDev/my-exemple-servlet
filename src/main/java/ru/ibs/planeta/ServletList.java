package ru.ibs.planeta;

import ru.ibs.planeta.logic.Model;
import ru.ibs.planeta.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    private final String htmlId = "<html>" +
            "<h3>Доступные пользователи:</h3><br/>" +
            "ID пользользователя: " +
            "<ul>";
    private String htmlList = "<li>%s</li>" +
            "<ul>" +
            "<li> Имя: %s</li>" +
            "<li> Фамилия: %s</li>" +
            "<li> Зарплата: %s</li>" +
            "</ul>";
    private final String htmlGoHome = "</ul><a href=\"index.jsp\">Домой</a></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        if(id == 0){
            pw.print(htmlId);

            for(Map.Entry<Integer, User> entry : model.getUserList().entrySet()){
                pw.println(String.format(htmlList, entry.getKey()
                        , entry.getValue().getName()
                        , entry.getValue().getSurname()
                        , entry.getValue().getSalary())
                );
            }

            pw.print(htmlGoHome);
        }
    }
}
