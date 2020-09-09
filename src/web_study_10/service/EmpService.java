package web_study_10.service;

import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.dao.impl.EmployeeDaoImpl;
import web_study_10.dto.Department;
import web_study_10.dto.Employee;

public class EmpService {
    private EmployeeDao dao = EmployeeDaoImpl.getInstance();

    public List<Employee> getEmpList(){
        return dao.selectEmployeeByAll();
    }
    
    public int addEmployee(Employee emp) {
        return dao.insertEmployee(emp);
    }
    
    public List<Employee> getManagerListByDno(Department dept){
        return dao.selectManagerByDno(dept);
    }
    
    public int duplicateEmpNo(int empNo) {
        return dao.idDupCheck(empNo);
    }
    
    public Employee getEmployee(Employee emp) {
        return dao.selectEmployeeByNo(emp);
    }
}
