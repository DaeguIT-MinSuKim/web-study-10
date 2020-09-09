package web_study_10.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.service.EmpService;

@WebServlet("/EmpManagerListHandler")
public class EmpManagerListHandler extends HttpServlet {
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
            int deptNo = Integer.parseInt(request.getParameter("deptNo"));
            System.out.println("deptNo" + deptNo);
            List<Employee> list = service.getManagerListByDno(new Department(deptNo));
            
            Gson gson = new Gson();
            String result = gson.toJson(list, new TypeToken<List<Employee>>(){}.getType());
            System.out.println(result);
                    
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            
            PrintWriter pw = response.getWriter();
            pw.print(result);
            pw.flush();
        } else {
            System.out.println("POST");
            int deptNo = Integer.parseInt(request.getParameter("deptNo"));
            System.out.println("deptNo" + deptNo);
            List<Employee> list = service.getManagerListByDno(new Department(deptNo));
            
            Gson gson = new Gson();
            String result = gson.toJson(list, new TypeToken<List<Employee>>(){}.getType());
            System.out.println(result);
                    
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            
            PrintWriter pw = response.getWriter();
            pw.print(result);
            pw.flush();
        }
    }
}
