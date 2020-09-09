package web_study_10.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_10.service.EmpService;

@WebServlet("/DuplicateEmpNoCheckHandler")
public class DuplicateEmpNoCheckHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpService service;

    public void init(ServletConfig config) throws ServletException {
        service = new EmpService();
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
        } else {
            System.out.println("POST");
            int empNo = Integer.parseInt(request.getParameter("empNo").trim());
            int res = service.duplicateEmpNo(empNo);
            System.out.println("res >> " + res);
            PrintWriter pw = response.getWriter();
            pw.print(res);
            pw.flush();
        }
    }
}
