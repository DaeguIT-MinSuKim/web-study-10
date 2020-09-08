package web_study_10.model;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_10.dto.Title;
import web_study_10.service.TitleService;

@WebServlet("/TitleDeleteHandler")
public class TitleDeleteHandler extends HttpServlet {
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
            int titleNo = Integer.parseInt(request.getParameter("titleNo").trim());
            int res = service.removeTitle(new Title(titleNo));
            
            response.getWriter().print(res);
        }else {
            System.out.println("POST");
           
        }
    }
}
