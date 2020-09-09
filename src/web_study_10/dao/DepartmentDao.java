package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Department;

public interface DepartmentDao {
    List<Department> selectDepartmentByAll();

    Department selectDepartmentByNo(Department dept);

    int insertDepartment(Department dept);

    int updateDepartment(Department dept);

    int deleteDepartment(Department dept);

    int getNextNo();
}
