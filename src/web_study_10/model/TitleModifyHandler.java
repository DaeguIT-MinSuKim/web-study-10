package web_study_10.model;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web_study_10.dto.Title;
import web_study_10.service.TitleService;

@WebServlet("/TitleModifyHandler")
public class TitleModifyHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TitleService service;

    public void init(ServletConfig config) throws ServletException {
        service = new TitleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("GET");

        }else {
            System.out.println("POST");
            Gson gson = new Gson();
            Title newTitle = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Title.class);
            System.out.println(newTitle);
            
            int res = service.modifyTitle(newTitle);
            response.getWriter().print(res);
        }
    }
}






