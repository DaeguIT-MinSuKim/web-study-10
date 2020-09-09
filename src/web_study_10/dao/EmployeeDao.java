package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Department;
import web_study_10.dto.Employee;

public interface EmployeeDao {
    List<Employee> selectEmployeeByAll();
    int insertEmployee(Employee emp);
    List<Employee> selectManagerByDno(Department dept);
    Employee selectEmployeeByNo(Employee emp);
    int idDupCheck(int empNo);
    int updateEmployee(Employee emp);
    int deleteEmployee(Employee emp);
}
